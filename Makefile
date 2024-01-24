up-prod:
	 docker-compose -f docker-compose.prod.yml up -d --build

down-prod:
	docker-compose -f docker-compose.prod.yml down -v

up-dev:
	 docker-compose -f docker-compose.dev.yml up -d --build

down-dev:
	docker-compose -f docker-compose.dev.yml down -v