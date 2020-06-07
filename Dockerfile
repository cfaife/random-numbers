FROM jboss/wildfly:19.1.0.Final
COPY ./target/random-numbers.war /opt/wildfly-19.1.0.Final/standalone/deployments/

