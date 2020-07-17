package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.binary.Base64;

import sn.modelsis.signart.Menu;
import sn.modelsis.signart.dto.ImageProfilDto;
import sn.modelsis.signart.dto.MenuDto;
import sn.modelsis.signart.facade.MenuFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("menu")
public class MenuFacadeREST {

    @Inject
    MenuFacade menuFacade;

    public MenuFacadeREST() {
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response create(MenuDto dto) {
         Menu entity = dtoToEntity(dto);
        menuFacade.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response edit(@PathParam("id") Integer id, MenuDto dto) {
        Menu entity = dtoToEntity(dto);
        menuFacade.edit(entity);
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        Menu entity = menuFacade.find(id);
        menuFacade.remove(entity);
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public MenuDto find(@PathParam("id") Integer id) {
        Menu entity = menuFacade.find(id);
        return entityToDto(entity);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<MenuDto> findAll() {
        List<MenuDto> listDto = new ArrayList<>();
        List<Menu> listEnt = menuFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                return entityToDto(entity);
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        return listDto;
    }

    @GET
    @Path("all-menus")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<MenuDto> findAllMenusApp() {
        List<MenuDto> listDto = new ArrayList<>();
        List<Menu> listEnt = menuFacade.findAll();
        if (listEnt != null) {
            for (Menu entity : listEnt) {
                // vérifier si le menu courant est un parent ou un children
                if (entity.getIdParent() == null) {
                    if (listDto.isEmpty()) {
                        listDto.add(entityToDto(entity));
                    } else {
                        // Tester s'il est dans la liste
                        boolean isPresent = false;
                        for (MenuDto parentDto : listDto) {
                            if (parentDto.getId().equals(entity.getId())) {
                                isPresent = true;
                            }

                        }
                        if (!isPresent) {
                            listDto.add(entityToDto(entity));
                        }
                    }

                } else {// c'est un fils

                    // récupération du parent
                    Menu entityParent = menuFacade.find(entity.getIdParent());

                    MenuDto dto = new MenuDto();
                    List<MenuDto> children = new ArrayList<>();
                    // mappage du menu fils
                    dto.setId(entity.getId());
                    dto.setClasse(entity.getClasse());
                    dto.setIcon(entity.getIcon());
                    dto.setPath(entity.getPath());
                    dto.setTitle(entity.getTitle());
                    dto.setChildren(new ArrayList<>());
                    if(entity.getImage()!=null)
                    {
                        ImageProfilDto image = new ImageProfilDto();
                        image.setFilename(entity.getIcon());
                        image.setValue(Base64.encodeBase64String(entity.getImage()));
                        dto.setImage(image);
                    }
                    children.add(dto);

                    // mappage du parent en dto
                    MenuDto dtoParent = entityToDto(entityParent);
                    // vérification du parent dans la liste dejà construite
                    boolean estPresent = false;
                    for (MenuDto menuParentDto : listDto) {
                        if (menuParentDto.getId().equals(dtoParent.getId())) {
                            estPresent = true;
                            menuParentDto.getChildren().add(dto);
                        }

                    }
                    if (!estPresent) {
                        dtoParent.setChildren(children);
                        listDto.add(dtoParent);
                    }
                }

            }
        }
        return listDto;
    }

    @GET
    @Path("profil/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<MenuDto> findByProfil(@PathParam("id") Integer idProfil) {
        List<MenuDto> listDto = new ArrayList<>();
        List<Menu> listEnt = menuFacade.findByProfil(idProfil);
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                return entityToDto(entity);
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        return listDto;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Menu> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return menuFacade.findRange(new int[] { from, to });
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(menuFacade.count());
    }

    /**
     * Converts Entity to Dto
     *
     * @param entity
     * @return
     */
    private MenuDto entityToDto(Menu entity) {
        MenuDto dto = new MenuDto();
        dto.setId(entity.getId());
        dto.setClasse(entity.getClasse());
        dto.setIcon(entity.getIcon());
        dto.setPath(entity.getPath());
        dto.setTitle(entity.getTitle());
        dto.setIdParent(entity.getIdParent());
        dto.setChildren(new ArrayList<>());
        if(entity.getImage()!=null)
        {
            ImageProfilDto image = new ImageProfilDto();
            image.setFilename(entity.getIcon());
            image.setValue(Base64.encodeBase64String(entity.getImage()));
            dto.setImage(image);
        }
        return dto;
    }
    private Menu dtoToEntity(MenuDto dto){
        Menu entity = new Menu();
        entity.setId(dto.getId());
        entity.setClasse(dto.getClasse());
        //entity.setIcon(dto.getIcon());
        entity.setIdParent(dto.getIdParent());
        entity.setPath(dto.getPath());
        entity.setTitle(dto.getTitle());
        if(dto.getImage() != null)
        {
            entity.setIcon(dto.getImage().getFilename());
            String imageValue = dto.getImage().getValue();
            //System.out.println(imageValue+"+++++++++++++++++++++++++++++++++++++++++++++++imageValue++++++++++++++++++++++++++++++++++++++++++++++");
            final byte[] image = Base64.decodeBase64(imageValue.getBytes());
            entity.setImage(image);
            //System.out.println(entity.getImage()+"+++++++++++++++++++++++++++++++++++++++++++++++entity image++++++++++++++++++++++++++++++++++++++++++++++");
            //entity.setIdSousTechnique(sousTechniqueFacade.find(dto.getIdSousTechnique())); 
        }
        return entity;
    }
}
