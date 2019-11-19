package sn.modelsis.signart.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * Account information used to exchange login/password
 *
 */
public class AccountInformation {

    String userName;
    String password;

    public AccountInformation() {
        super();
    }

    public AccountInformation(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return true if account valid
     */
    public boolean isValid() {
        return !(StringUtils.isBlank(userName) || StringUtils.isBlank(password));
    }
}
