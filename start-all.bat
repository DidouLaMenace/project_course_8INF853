@echo off
setlocal

REM Liste des chemins vers les répertoires des microservices
set SERVICES=back-end\discovery-server back-end\gateway back-end\auth-ms back-end\accounts-ms back-end\banking-ms back-end\catalog-ms

REM Chemin vers le projet React
set FRONT_END_PATH=front-end

REM Boucle pour lancer chaque microservice
for %%S in (%SERVICES%) do (
    echo Starting microservice in %%S...
    cd %%S
    start cmd /k "mvnw spring-boot:run"
    cd ..
    cd ..
)

REM Lancer le projet React (front-end)
echo Starting React Front-End...
cd %FRONT_END_PATH%
start cmd /k "npm start"
cd ..

echo All services are starting...
pause
