ROOT=$(dirname $(cd "$(dirname "$0")";pwd))
DEPLOY_PATH='/data/deploy/faceye-kindle-web'
RESIN_HOME='/data/tools/resin/resin-kindle-web'
cd $ROOT
git pull
cd $ROOT/faceye-kindle-entity
mvn clean compile package install -D maven.test.skip=true -P product
cd $ROOT/faceye-kindle-web
mvn clean compile war:war -D maven.test.skip=true  -P product
sh $RESIN_HOME/bin/resin.sh stop
sleep 5
rm -rf $DEPLOY_PATH/faceye-kindle-web.war
cp $ROOT/faceye-kindle-web/target/faceye-kindle-web.war $DEPLOY_PATH
$RESIN_HOME/bin/resin.sh start
