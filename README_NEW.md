# Demo Capitole.

## Descripción del Servicio

Este servicio proporciona un endpoint para consultar precios basados en filtros específicos. Los filtros incluyen la fecha de aplicación, el ID del producto y el ID de la marca. La lógica de negocio del servicio se encarga de buscar en una base de datos H2 configurada en memoria los precios que coinciden con los filtros proporcionados, considerando rangos de fechas y prioridades.

## Descripción de la Lógica de Negocio

### Filtros de Consulta

- **Fecha de Aplicación (`applicationDate`)**: La fecha debe estar dentro del rango definido por `start_date` y `end_date` en los documentos de la base de datos.
- **ID del Producto (`productId`)**: Filtra los documentos por el ID del producto.
- **ID de la Marca (`brandId`)**: Filtra los documentos por el ID de la marca.

### Consulta en la Base de Datos

- **Rango de Fechas**: La consulta busca documentos en los que la fecha de aplicación esté entre `start_date` y `end_date`.
- **Prioridad**: Si hay múltiples documentos que cumplen con los filtros, solo se selecciona el documento con la mayor prioridad. La prioridad se utiliza para desambiguar en caso de múltiples precios para la misma fecha.

## Uso del Servicio

### 1. Lanzar una Petición

- **URL del Endpoint**: `http://localhost:8080/prices`
- **Método HTTP**: `GET`

- **Parámetros de Consulta**:
  - `applicationDate`: Fecha en formato `yyyy-MM-dd'T'HH:mm:ss` (Ej. `2022-06-15T12:00:00`)
  - `productId`: ID del producto (Ej. `35455`)
  - `brandId`: ID de la marca (Ej. `1`)

- **Ejemplo de Petición**:
  - **URL**: `http://localhost:8080/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1`
  - **Método**: `GET`

### 2. Verificar la Respuesta

- **Código de Estado**:
  - `200 OK`: Si se encuentran precios que coinciden con los filtros proporcionados.
  - `200 OK`: Si no se encuentran precios que coincidan con los filtros.
  - `400 Bad Request`: Si alguno de los parámetros de consulta no es válido.

- **Ejemplo de Respuesta**:

  ```json
  {
	"productId": 35455,
	"brandId": 1,
	"tariff": 1,
	"price": 35.5,
	"currency": "EUR"
  }
  ```
