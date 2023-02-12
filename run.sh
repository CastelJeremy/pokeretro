echo "Starting PokeRetro services..."

#echo "Launching Arena Service..."
#docker-compose -f backend/arena/docker-compose.yaml up > backend/arena/logs
echo "Launching Authentification Service..."
docker-compose -f backend/auth/docker-compose.yaml up > backend/auth/logs
# echo "Launching Fight Service..."
# docker-compose -f backend/fight/docker-compose.yaml up > backend/fight/logs
echo "Launching Inventory Service..."
docker-compose -f backend/inventory/docker-compose.yaml up > backend/inventory/logs
echo "Launching Pokemon Service..."
docker-compose -f backend/pokemon/docker-compose.yaml up > backend/pokemon/logs
#echo "Launching Report Service..."
#docker-compose -f backend/report/docker-compose.yaml up > backend/report/logs
echo "Launching Incubator Service..."
docker-compose -f backend/incubator/docker-compose.yaml up > backend/incubator/logs
echo "Launching Shop Service..."
docker-compose -f backend/shop/docker-compose.yaml up > backend/shop/logs
echo "Launching Team Service..."
docker-compose -f backend/team/docker-compose.yaml up > backend/team/logs
echo "Launching Trainer Service..."
docker-compose -f backend/trainer/docker-compose.yaml up > backend/trainer/logs
echo "Launching WebApp Service..."
docker-compose -f frontend/docker-compose.yaml up > frontend/logs

echo "All services are launching..."
echo "You can connect to http://localhost:80/ and start your journey !"