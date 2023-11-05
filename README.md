# 1.5:

```
Request:
GET localhost:7070/api/plants
Accept: application/json

Response:
[
  {
    "id": 0,
    "plantType": "Rose",
    "plantName": "Gallicanae",
    "maxHeight": 350,
    "price": 299.0
  },
  {
    "id": 1,
    "plantType": "Rose",
    "plantName": "Gallicanae",
    "maxHeight": 350,
    "price": 299.0
  },
]
```

```
Request:
GET localhost:7070/api/plants/1
Accept: application/json

Response:
{
  "id": 1,
  "plantType": "Rose",
  "plantName": "Gallicanae",
  "maxHeight": 350,
  "price": 299.0
}
```

```
Request:
GET localhost:7070/api/plants/types/Rose
Accept: application/json

Response:
[
  {
    "id": 0,
    "plantType": "Rose",
    "plantName": "Gallicanae",
    "maxHeight": 350,
    "price": 299.0
  },
  {
    "id": 1,
    "plantType": "Rose",
    "plantName": "Gallicanae",
    "maxHeight": 350,
    "price": 299.0
  }
]
```

```
Request:
POST localhost:7070/api/plants
Accept: application/json

{
    "plantType": "Rose",
    "plantName": "Gallicanae",
    "maxHeight": 350,
    "price": 299.0
}

Response:
{
  "id": 2,
  "plantType": "Rose",
  "plantName": "Gallicanae",
  "maxHeight": 350,
  "price": 299.0
}
```

# 2.1:
| HTTP method | REST Ressource            | Exceptions and status(es) |
|-------------|---------------------------|---------------------------|
| GET         | `/api/plants`             |                           |
| GET         | `/api/plants/{id}`        | `Invalid id: 400`         |
| GET         | `/api/plants/type/{type}` | `Invalid type: 400`       |
| POST        | `/api/plants`             |                           |

# 3.4:
The stream api is inspired by the functional programming paradigm