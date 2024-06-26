CREATE USER appsaudebemestar WITH PASSWORD 'appsaudebemestar';

DROP DATABASE IF EXISTS saude_bem_estar;

CREATE DATABASE saude_bem_estar;

GRANT ALL PRIVILEGES ON DATABASE saude_bem_estar TO appsaudebemestar;