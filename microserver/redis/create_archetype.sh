echo "step 1:"
mvn -s "D:\devtool\maven\apache-maven\conf\settings.xml" archetype:create-from-project
echo "step 2:"
cd target/generated-sources/archetype
echo "step 3:"
mvn clean install