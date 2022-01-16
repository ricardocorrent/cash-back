# Getting start

## Creating a Postgres database using docker
Docker is required in this part

* Downloading postgres image
```
docker pull postgres
```
* creating a container to execute postgres 
```
docker run --name db-postgres -e "POSTGRES_PASSWORD=root" -p 5432:5432 -v ./database:/var/lib/postgresql/data -d postgres
```
* Listing the available containers
```
docker ps -a
```
* Starting the container using the 
```
docker start <container_id>
```

## Starting the server

The server starts using port `8080` by default `http://localhost:8080`

## How to create a user

* Endpoint
  * `http://localhost:8080/signup`
* Method
  * `@POST`
* Payload
```json
{
  "fullName": "John Smith",
  "email": "john@smith.com",
  "password": "123",
  "cpf": "645.403.170-59"
}
```
* Body response
```json
{
  "fullName": "John Smith",
  "email": "john@smith.com",
  "cpf": "645.403.170-59"
}
```

## Execute login

* Endpoint
    * `http://localhost:8080/login`
* Method
    * `@POST`
* Payload
```json
{
  "email": "john@smith.com",
  "password": "123"
}
```
* Header response

The header contains the token needed to do the next requests
```
key = Authorization
value = Bearer
```

## Validate login

* Endpoint
    * `http://localhost:8080/user/verify`
* Method
    * `@GET`
* Header
  * Key = `Authorization`
  * Value = token received in header on login starting with `Bearer `
* Payload
```json
{
  "email": "john@smith.com",
  "password": "123"
}
```
* Body response

```json
{
    "userName": "john@smith.com",
    "logged": true
}
```

## Create an ORDER

* Endpoint
    * `http://localhost:8080/order`
* Method
    * `@POST`
* Header
    * Key = `Authorization`
    * Value = token received in header on login starting with `Bearer `
* Payload
```json
{
  "code": "cccc",
  "value": 120,
  "cpf": "645.403.170-59",
  "date": "2022-01-16T12:47:12.824550"
}
```
* Body response

```json
{
  "id": "29e184bc-a791-42ee-a2a1-50b38d90f344",
  "code": "cccc",
  "value": 120,
  "date": "2022-01-16T12:47:12.824550",
  "cashBackPercentage": 10.0,
  "cashBackValue": 12.00,
  "status": "VALIDATION"
}
```

## List all ORDERS

* Endpoint
    * `http://localhost:8080/order`
* Method
    * `@GET`
* Header
    * Key = `Authorization`
    * Value = token received in header on login starting with `Bearer `

* Body response

```json
[
  {
    "id": "2299fd24-dd33-4db2-a236-dc324a514341",
    "code": "cccc",
    "value": 120.00,
    "date": "2022-01-16T12:47:12.82455",
    "cashBackPercentage": 10.00,
    "cashBackValue": 12.00,
    "status": "VALIDATION"
  },
  {
    "id": "29e184bc-a791-42ee-a2a1-50b38d90f344",
    "code": "4343",
    "value": 100.00,
    "date": "2022-01-16T12:47:12.82455",
    "cashBackPercentage": 10.00,
    "cashBackValue": 12.00,
    "status": "VALIDATION"
  }
]
```

## Getting order resume by month

* Endpoint
    * `http://localhost:8080/cashback/{cpf}`
* Path parameter
  * User cpf e.g. `12312312323`
* Method
    * `@GET`
* Header
    * Key = `Authorization`
    * Value = token received in header on login starting with `Bearer `

* Body response

```json
{
  "credit": 1219
}
```
