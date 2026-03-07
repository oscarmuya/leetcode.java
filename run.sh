#!/usr/bin/env bash

mvn compile

mvn exec:java -Dexec.mainClass="com.oscar.app.App" 

