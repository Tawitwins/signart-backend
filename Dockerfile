FROM maven:3.8.4-openjdk-8 as builder
COPY pom.xml /tmp/
COPY target /tmp/target
COPY src /tmp/src
#RUN mvn verify



FROM glassfish:latest
ENV ADMIN_USER admin
ENV ADMIN_PASSWORD admin
ENV ASADMINPATH /usr/local/glassfish4/bin/asadmin
# set credentials to admin/admin 

RUN echo 'AS_ADMIN_PASSWORD=\n\
AS_ADMIN_NEWPASSWORD='$ADMIN_PASSWORD'\n\
EOF\n'\
>> /opt/tmpfile

RUN echo 'AS_ADMIN_PASSWORD='$ADMIN_PASSWORD'\n\
EOF\n'\
>> /opt/pwdfile


COPY --from=builder /tmp/target/SignArt.war /
#COPY --from=builder /tmp/src/main/resources/assets/images/ /signartResources/resources/
COPY --from=builder /tmp/src/main/resources /signartResources/resources/
COPY lib/sqljdbc4-4.1.jar /usr/local/glassfish4/glassfish/lib
COPY lib/postgresql-42.5.0.jar /usr/local/glassfish4/glassfish/lib
#COPY lib/jdk-8u151-linux-x64.tar.gz /jdkVersion/
RUN rm -rf /usr/local/glassfish4/glassfish/modules/jboss-logging.jar
COPY lib/jboss-logging.jar /usr/local/glassfish4/glassfish/modules/
COPY script.sh /usr/local/bin/
WORKDIR /


EXPOSE 4848
EXPOSE 8080

RUN \
 $ASADMINPATH start-domain && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/tmpfile change-admin-password && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile enable-secure-admin && \
 #$ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile create-jdbc-connection-pool --restype=javax.sql.ConnectionPoolDataSource --datasourceclassname=org.postgresql.ds.PGConnectionPoolDataSource --property "User=signart:Password=passer123:DatebaseName=signart:ServerName=192.168.1.3:PortNumber=5432" poolSignart && \
 
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile create-jdbc-connection-pool #--restype=javax.sql.ConnectionPoolDataSource --datasourceclassname=org.postgresql.ds.PGConnectionPoolDataSource --property #"User=signart:Password=passer123:DatebaseName=signart:ServerName=10.42.1.205:PortNumber=5432" poolSignart && \
 
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid poolSignart jdbc/signart && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile deploy /SignArt.war

WORKDIR /

#ENTRYPOINT ["/start.sh"]
