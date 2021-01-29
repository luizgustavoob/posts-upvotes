#!/bin/bash
get_version=$(grep -E -o "[0-9*].[0-9*].[0-9*]" temp-version.txt)
app_final_name="target/posts-upvotes-$get_version.jar"
rm -rf temp-pom.xml
rm -rf temp-version.txt
exec java -jar $app_final_name