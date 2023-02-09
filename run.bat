@echo off

echo "Starting PokeRetro services..."

echo "Launching Arena Service..."
docker-compose -f backend/arena/docker-compose.yaml up
echo "Launching Authentification Service..."
docker-compose -f backend/auth/docker-compose.yaml up
echo "Launching Fight Service..."
docker-compose -f backend/fight/docker-compose.yaml up
echo "Launching Incubator Service..."
docker-compose -f backend/incubator/docker-compose.yaml up
echo "Launching Inventory Service..."
docker-compose -f backend/inventory/docker-compose.yaml up
echo "Launching Pokemon Service..."
docker-compose -f backend/pokemon/docker-compose.yaml up
#echo "Launching Report Service..."
#docker-compose -f backend/report/docker-compose.yaml up
echo "Launching Shop Service..."
docker-compose -f backend/shop/docker-compose.yaml up
echo "Launching Team Service..."
docker-compose -f backend/team/docker-compose.yaml up
echo "Launching Trainer Service..."
docker-compose -f backend/trainer/docker-compose.yaml up
echo "Launching WebApp Service..."
docker-compose -f frontend/docker-compose.yaml up

echo "All services are launching..."
echo "You can connect to http://localhost:80/ and start your journey !"