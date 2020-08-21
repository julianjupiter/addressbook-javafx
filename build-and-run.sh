#!/usr/bin/env sh

./mvnw clean package && java -jar ./target/addressbook-javafx-0.0.2.jar
