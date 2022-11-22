FROM maven:3.6.3-openjdk-8 as builder
COPY pom.xml /tmp/
COPY src /tmp/src
WORKDIR /tmp/
RUN mvn clean verify    

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
COPY start.sh /
COPY lib/sqljdbc4-4.1.jar /usr/local/glassfish4/glassfish/lib
RUN rm -rf /usr/local/glassfish4/glassfish/modules/jboss-logging.jar
COPY lib/jboss-logging.jar /usr/local/glassfish4/glassfish/modules/

EXPOSE 8080

RUN \
 $ASADMINPATH start-domain && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/tmpfile change-admin-password && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile enable-secure-admin && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile create-jdbc-connection-pool --restype=javax.sql.DataSource --datasourceclassname=com.microsoft.sqlserver.jdbc.SQLServerDataSource --property "User=signart:Password=signart:url=jdbc\:sqlserver\://:TrustServerCertificate=false:LastUpdateCount=true:ResponseBuffering=adaptive:XopenStates=false:PacketSize=8000:ApplicationName=Microsoft JDBC Driver for SQL Server:DatebaseName=Signart:Encrypt=false:SendStringParametersAsUnicode=true:MiltiSubnetFailover=False:ApplicationIntent=readwrite:LoginTimeout=15:WorkstartionID=DESKTOP-VMR24NI:ServerName=10.42.1.204:PortNumber=1433:SelectedMethod=direct:SendTimeAsDatetime=true" poolSignart && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid poolSignart jdbc/signart && \
 $ASADMINPATH --user $ADMIN_USER --passwordfile=/opt/pwdfile deploy /SignArt.war


ENTRYPOINT ["/start.sh"]