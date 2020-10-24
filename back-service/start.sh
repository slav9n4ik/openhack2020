docker rm -f hack-service
docker build -t slav9n4ik/hack-service .
docker run -dit -p 7777:7777 --name hack-service slav9n4ik/hack-service
docker logs -f hack-service