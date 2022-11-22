#!/bin/sh

/usr/local/glassfish4/bin/asadmin stop-domain
/usr/local/glassfish4/bin/asadmin start-domain --verbose
#/usr/local/glassfish4/bin/asadmin --user admin --passwordfile=/opt/pwdfile deploy /SignArt.war
#/usr/local/glassfish4/bin/asadmin create-jdbc-connection-pool --restype=javax.sql.DataSource --datasourceclassname=com.microsoft.sqlserver.jdbc.SQLServerDataSource --property "user=signart:password=signart:url=jdbc\signart" poolSignart
#/usr/local/glassfish4/bin/asadmin create-jdbc-resource --connectionpoolid poolSignart jdbc/signart
#/usr/local/glassfish4/bin/asadmin -u admin deploy /SignArt.war


#/usr/local/glassfish5/bin/asadmin start-domain
#/usr/local/glassfish4/bin/asadmin create-jdbc-connection-pool --restype=javax.sql.DataSource --datasourceclassname=com.microsoft.sqlserver.jdbc.SQLServerDataSource --property "user=signart:password=signart:url=jdbc\signart" poolSignart
#/usr/local/glassfish4/bin/asadmin create-jdbc-resource --connectionpoolid poolSignart jdbc/signart
#/usr/local/glassfish4/bin/asadmin enable-secure-admin
#/usr/local/glassfish4/bin/asadmin change-admin-password
