# kubectl delete -f postgres-service.yaml
# kubectl delete -f postgres-depoyment.yaml
# kubectl delete -f postgres-storage.yaml
# kubectl delete -f postgres-configmap.yaml
###
kubectl apply -f postgres-configmap.yaml
kubectl apply -f postgres-storage.yaml
kubectl apply -f postgres-deployment.yaml
kubectl apply -f postgres-service.yaml