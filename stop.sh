echo "Stopping PokeRetro services..."

# echo "Launching WebApp Service..."
# docker-compose -f frontend/docker-compose.yaml up -d

docker-compose -f backend/incubator/docker-compose.yaml down
docker-compose -f backend/shop/docker-compose.yaml down
docker-compose -f backend/trainer/docker-compose.yaml down

docker-compose -f backend/inventory/docker-compose.yaml down
docker-compose -f backend/pokemon/docker-compose.yaml down
docker-compose -f backend/team/docker-compose.yaml down
docker-compose -f backend/auth/docker-compose.yaml down

echo "All services were stopped..."