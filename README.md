# Demo Capitole.

## Descripción del Servicio

Este servicio proporciona un endpoint para consultar precios basados en filtros específicos. Los filtros incluyen la fecha de aplicación, el ID del producto y el ID de la marca. La lógica de negocio del servicio se encarga de buscar en una base de datos MongoDB los precios que coinciden con los filtros proporcionados, considerando rangos de fechas y prioridades.

# Config inicial mongodb.

## Prerrequisitos

Docker instalado y operativo en la máquina.

## Desplegar MongoDB con Docker

1. **Existe archivo `docker-compose.yml` en la carpeta resources del proyecto con el siguiente contenido:**

    ```yaml
    version: '3.8'
    services:
      mongodb:
        image: mongo:6-jammy
        container_name: mongodb
        ports:
          - '27017:27017'
        volumes:
          - dbdata6:/data/db
    volumes:
      dbdata6:
    ```
2. **Seguidamente levantamos el contendor mediante el comando**

    ```bash
    docker-compose up -d
    ```

3. **A continuación nos conectamos al contenedor mediante el comando**
    ```bash
    docker-compose exec mongodb mongosh
    ```
    
4. **Una vez conectados al contendor podemos proceder a alimentar la base de datos inicial**
    ```
    db = db.getSiblingDB('productos');

    db.prices.drop(); // Limpiar colección si existe

    db.prices.insertMany([
      { "brand_id": 1, "start_date": ISODate("2020-06-15T16:00:00Z"), "end_date": ISODate("2020-12-31T23:59:59Z"), "price_list": 4, "product_id": 35455, "priority": 1, "price": 38.95, "curr": "EUR" },
      { "brand_id": 2, "start_date": ISODate("2021-01-01T00:00:00Z"), "end_date": ISODate("2021-06-30T23:59:59Z"), "price_list": 4, "product_id": 35456, "priority": 0, "price": 40.00, "curr": "USD" },
      { "brand_id": 1, "start_date": ISODate("2020-07-01T00:00:00Z"), "end_date": ISODate("2020-12-31T23:59:59Z"), "price_list": 4, "product_id": 35457, "priority": 2, "price": 25.00, "curr": "GBP" },
      { "brand_id": 3, "start_date": ISODate("2020-05-01T00:00:00Z"), "end_date": ISODate("2020-08-31T23:59:59Z"), "price_list": 5, "product_id": 35458, "priority": 3, "price": 20.00, "curr": "EUR" },
      { "brand_id": 2, "start_date": ISODate("2020-10-01T00:00:00Z"), "end_date": ISODate("2020-12-31T23:59:59Z"), "price_list": 4, "product_id": 35459, "priority": 1, "price": 30.00, "curr": "USD" },
      { "brand_id": 4, "start_date": ISODate("2021-01-01T00:00:00Z"), "end_date": ISODate("2021-03-31T23:59:59Z"), "price_list": 6, "product_id": 35460, "priority": 0, "price": 45.00, "curr": "JPY" },
      { "brand_id": 1, "start_date": ISODate("2021-02-01T00:00:00Z"), "end_date": ISODate("2021-06-30T23:59:59Z"), "price_list": 5, "product_id": 35461, "priority": 2, "price": 33.50, "curr": "EUR" },
      { "brand_id": 3, "start_date": ISODate("2021-03-01T00:00:00Z"), "end_date": ISODate("2021-09-30T23:59:59Z"), "price_list": 6, "product_id": 35462, "priority": 1, "price": 29.99, "curr": "USD" },
      { "brand_id": 2, "start_date": ISODate("2021-07-01T00:00:00Z"), "end_date": ISODate("2021-12-31T23:59:59Z"), "price_list": 4, "product_id": 35463, "priority": 3, "price": 22.50, "curr": "GBP" },
      { "brand_id": 4, "start_date": ISODate("2021-04-01T00:00:00Z"), "end_date": ISODate("2021-06-30T23:59:59Z"), "price_list": 7, "product_id": 35464, "priority": 0, "price": 50.00, "curr": "JPY" },
      { "brand_id": 1, "start_date": ISODate("2021-05-01T00:00:00Z"), "end_date": ISODate("2021-10-31T23:59:59Z"), "price_list": 5, "product_id": 35465, "priority": 2, "price": 35.00, "curr": "EUR" },
      { "brand_id": 3, "start_date": ISODate("2021-06-01T00:00:00Z"), "end_date": ISODate("2021-12-31T23:59:59Z"), "price_list": 6, "product_id": 35466, "priority": 1, "price": 27.50, "curr": "USD" },
      { "brand_id": 2, "start_date": ISODate("2021-08-01T00:00:00Z"), "end_date": ISODate("2021-12-31T23:59:59Z"), "price_list": 4, "product_id": 35467, "priority": 3, "price": 23.00, "curr": "GBP" },
      { "brand_id": 4, "start_date": ISODate("2021-09-01T00:00:00Z"), "end_date": ISODate("2021-12-31T23:59:59Z"), "price_list": 7, "product_id": 35468, "priority": 0, "price": 55.00, "curr": "JPY" },
      { "brand_id": 1, "start_date": ISODate("2021-10-01T00:00:00Z"), "end_date": ISODate("2021-12-31T23:59:59Z"), "price_list": 5, "product_id": 35469, "priority": 2, "price": 38.00, "curr": "EUR" },
      { "brand_id": 3, "start_date": ISODate("2021-11-01T00:00:00Z"), "end_date": ISODate("2022-03-31T23:59:59Z"), "price_list": 6, "product_id": 35470, "priority": 1, "price": 32.50, "curr": "USD" },
      { "brand_id": 2, "start_date": ISODate("2021-12-01T00:00:00Z"), "end_date": ISODate("2022-06-30T23:59:59Z"), "price_list": 4, "product_id": 35471, "priority": 3, "price": 24.00, "curr": "GBP" },
      { "brand_id": 4, "start_date": ISODate("2022-01-01T00:00:00Z"), "end_date": ISODate("2022-12-31T23:59:59Z"), "price_list": 7, "product_id": 35472, "priority": 0, "price": 60.00, "curr": "JPY" },
      { "brand_id": 1, "start_date": ISODate("2022-02-01T00:00:00Z"), "end_date": ISODate("2022-12-31T23:59:59Z"), "price_list": 5, "product_id": 35473, "priority": 2, "price": 37.00, "curr": "EUR" },
      { "brand_id": 3, "start_date": ISODate("2022-03-01T00:00:00Z"), "end_date": ISODate("2022-12-31T23:59:59Z"), "price_list": 6, "product_id": 35474, "priority": 1, "price": 29.99, "curr": "USD" },
      { "brand_id": 2, "start_date": ISODate("2022-04-01T00:00:00Z"), "end_date": ISODate("2022-12-31T23:59:59Z"), "price_list": 4, "product_id": 35475, "priority": 3, "price": 21.50, "curr": "GBP" }
    ]);
    ```

## Uso del Servicio

### 1. Lanzar una Petición

- **URL del Endpoint**: `http://localhost:8080/prices`
- **Método HTTP**: `GET`

- **Parámetros de Consulta**:
  - `applicationDate`: Fecha en formato `yyyy-MM-dd'T'HH:mm:ss.SSSX` (Ej. `2022-06-15T12:00:00.000Z`)
  - `productId`: ID del producto (Ej. `35455`)
  - `brandId`: ID de la marca (Ej. `1`)

- **Ejemplo de Petición**:
  - **URL**: `http://localhost:8080/prices?applicationDate=2022-06-15T12:00:00.000Z&productId=35455&brandId=1`
  - **Método**: `GET`

### 2. Verificar la Respuesta

- **Código de Estado**:
  - `200 OK`: Si se encuentran precios que coinciden con los filtros proporcionados.
  - `200 OK`: Si no se encuentran precios que coincidan con los filtros.
  - `400 Bad Request`: Si alguno de los parámetros de consulta no es válido.

- **Ejemplo de Respuesta**:

  ```json
  [
    {
      "brandId": 1,
      "startDate": "2022-02-01T00:00:00.000Z",
      "endDate": "2022-12-31T23:59:59.000Z",
      "priceList": 5,
      "productId": 35473,
      "priority": 2,
      "price": 37.00,
      "currency": "EUR"
    },
    {
      "brandId": 3,
      "startDate": "2022-03-01T00:00:00.000Z",
      "endDate": "2022-12-31T23:59:59.000Z",
      "priceList": 6,
      "productId": 35474,
      "priority": 1,
      "price": 29.99,
      "currency": "USD"
    }
  ]
    ```

## Descripción de la Lógica de Negocio

### Filtros de Consulta

- **Fecha de Aplicación (`applicationDate`)**: La fecha debe estar dentro del rango definido por `start_date` y `end_date` en los documentos de la base de datos.
- **ID del Producto (`productId`)**: Filtra los documentos por el ID del producto.
- **ID de la Marca (`brandId`)**: Filtra los documentos por el ID de la marca.

### Consulta en la Base de Datos

- **Rango de Fechas**: La consulta busca documentos en los que la fecha de aplicación esté entre `start_date` y `end_date`.
- **Prioridad**: Si hay múltiples documentos que cumplen con los filtros, solo se selecciona el documento con la mayor prioridad. La prioridad se utiliza para desambiguar en caso de múltiples precios para la misma fecha.

### Ejemplo de Consulta en MongoDB

```javascript
db.prices.find({
  start_date: { $lte: ISODate('2022-06-15T12:00:00.000Z') },
  end_date: { $gte: ISODate('2022-06-15T12:00:00.000Z') },
  product_id: 35455,
  brand_id: 1
}).sort({ priority: -1 });
```
