FROM jboss/wildfly
COPY ./target/random-numbers.war /opt/jboss/wildfly/standalone/deployments/

