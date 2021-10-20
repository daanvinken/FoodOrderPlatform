# FoodOrderPlatform

Working on an Java JAX-WS REST API build on microservices, using Kubernetes and Postgres

1. start Minikube
2. Change docker registry `eval $(minikube docker-env)`
3. Build containers
4. Apply nemesis.yaml with kubectl

POST http://192.168.49.2:31001/api/orders/create
```json
{
  "restaurantId": 1,
  "orderitems": [
    {
      "name": "spaghetti carbonara",
      "price": 9.99,
      "id": 1,
      "quantity": 1
    },
    {
      "name": "Spaghetti aglio e olio",
      "price": 8.99,
      "id": 2,
      "quantity": 2
    },
    {
      "name": "Gorgeous Cotton Pizza",
      "price": 5,
      "id": 10,
      "quantity": 1
    }
  ],
  "totalAmount": 32.97
}
```

GET http://192.168.49.2:31001/api/orders/1



http://192.168.49.2:31000/system/properties
http://192.168.49.2:32000/inventory/systems/system-service
