git pull
mvn clean compile jetty:run -D maven.test.skip=true -D jetty.port=8091 -P product
