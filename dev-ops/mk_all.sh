#!/bin/bash
clear

echo ""
echo ""
echo "COMPILANDO DISCOVERY ..."
cd ../discovery/
mvn clean package
cd -
echo ""
echo ""
read -rsp 'FIM - PRESSIONE UMA TECLA PARA CONTINUAR ...'
echo ""
echo ""
echo ""
echo "COMPILANDO MS-Order ..."
cd ../ms-order/
mvn clean package
cd -
echo ""
echo ""
read -rsp 'FIM - PRESSIONE UMA TECLA PARA CONTINUAR ...'
echo ""
echo ""
echo ""
echo "COMPILANDO  MS-Payment..."
cd ../ms-payment/
mvn clean package
cd -
echo ""
echo ""
read -rsp 'FIM  - PRESSIONE UMA TECLA PARA CONTINUAR ...'
echo ""
echo ""
echo ""
echo "COMPILANDO  MS-Shipping ..."
cd ../ms-shipping/
mvn clean package
cd -
echo ""
echo ""
read -rsp 'FIM  - PRESSIONE UMA TECLA PARA CONTINUAR ...'
echo ""
echo ""
echo ""
echo "COMPILANDO  BFF ..."
cd ../bff-web/
mvn clean package
cd -
echo ""
echo ""
echo ""
docker rmi $(docker images  -f 'dangling=true' -q)
echo " FIM"


