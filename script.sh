#!/bin/sh

###!/usr/bin/env bash

cd /
cd  "signartFiles"

mkdir -p "abonnement"
mkdir -p "commande"
mkdir -p "chat"
cd "abonnement"
mkdir -p "images"
mkdir -p "recues"
cd ../commande
mkdir -p "preuves"
mkdir -p "recues"
cd ..

/usr/local/glassfish4/bin/asadmin start-domain --verbose
