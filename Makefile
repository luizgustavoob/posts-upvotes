API_NAME = luizgustavoob/posts-upvotes-api:latest
FRONT_NAME = luizgustavoob/posts-upvotes-front:latest

.PHONY: build
build:
	docker image build -t $(API_NAME) -f posts-upvotes/Dockerfile posts-upvotes
	docker image build -t $(FRONT_NAME) -f posts-upvotes-front/Dockerfile posts-upvotes-front

.PHONY: up
up: build
	docker-compose up -d

.PHONY: down
down:
	docker-compose down
