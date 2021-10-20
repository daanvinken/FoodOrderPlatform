kubectl delete -f nemesis.yaml
mvn clean package
#docker build -t system:1.0-SNAPSHOT system/.
#docker build -t inventory:1.0-SNAPSHOT inventory/
echo "Rebuilding 'order' pod..."
docker build -t order:1.0-SNAPSHOT order/.
kubectl apply -f nemesis.yaml
kubectl get pods
echo "Now showing logs of order pod..."
sleep 5
kubectl logs -f $(kubectl get pods | grep order | awk '{print $1}')
