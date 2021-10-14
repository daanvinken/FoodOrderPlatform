kubectl delete -f kubernetes.yaml
mvn clean package
#docker build -t system:1.0-SNAPSHOT system/.
#docker build -t inventory:1.0-SNAPSHOT inventory/
docker build -t order:1.0-SNAPSHOT order/.
kubectl apply -f kubernetes.yaml
kubectl get pods