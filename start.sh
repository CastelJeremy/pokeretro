echo "Starting PokeRetro services..."

docker-compose -f backend/docker-compose.yaml up -d
docker-compose -f backend/auth/docker-compose.yaml up -d
docker-compose -f backend/inventory/docker-compose.yaml up -d
docker-compose -f backend/pokemon/docker-compose.yaml up -d
docker-compose -f backend/team/docker-compose.yaml up -d

docker-compose -f backend/incubator/docker-compose.yaml up -d
docker-compose -f backend/shop/docker-compose.yaml up -d
docker-compose -f backend/trainer/docker-compose.yaml up -d

docker-compose -f frontend/docker-compose.yaml up -d

echo "All services were started..."
echo "You can connect to http://localhost:80/ and start your journey !"