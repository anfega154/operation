# Proyecto Operación Fuego de Quásar

Este proyecto implementa un sistema para recibir información de satélites, determinar la posición de una nave en el espacio y decodificar el mensaje de auxilio. Se utiliza Java, Spring Boot y otras tecnologías para la implementación.

## Requisitos

- JDK 17 o superior
- Gradle 7.0 o superior

## Tecnologías

- **Spring Boot**: Framework principal para la creación de servicios REST.
- **JUnit 5**: Para pruebas unitarias.
- **Mockito**: Para pruebas unitarias con mocks.

## Instalación

1. Clona este repositorio:
   git clone https://github.com/anfega154/operation.git
   cd operation
   
2.	Asegúrate de tener JDK 17 y Gradle instalados.
3.	Para instalar las dependencias, ejecuta: ./gradlew build

## Ejecución

Para ejecutar el proyecto, usa el siguiente comando: ./gradlew bootRun

## Rutas de la API

POST /api/topsecret

Descripción: Recibe la información de los satélites y devuelve la ubicación y el mensaje de auxilio de la nave.

Cuerpo de la solicitud:
```json
{
  "satellites": [
    {
      "name": "kenobi",
      "distance": 100.0,
      "message": ["", "este", "", "mensaje", ""]
    },
    {
      "name": "skywalker",
      "distance": 115.5,
      "message": ["", "es", "", "", "secreto"]
    },
    {
      "name": "sato",
      "distance": 142.7,
      "message": ["este", "", "un", "", ""]
    }
  ]
}
```
Respuesta (200 OK):
```json
{
  "position": { "x": -487.0, "y": 1557.0 },
  "message": "este es un mensaje secreto"
}
```

POST /api/topsecret_split/{satellite_name}

Descripción: Recibe la información de un satélite y la almacena temporalmente. Este endpoint permite dividir la información por satélite.

Cuerpo de la solicitud:
```json
{
  "distance": 100.0,
  "message": ["", "este", "", "mensaje", ""]
}
```

Respuesta (200 OK):
```json
{
  "message": "Data for satellite 'kenobi' updated successfully."
}
```
GET /api/topsecret_split

Descripción: Devuelve la ubicación y el mensaje final, si hay suficiente información de los tres satélites.

Respuesta (200 OK):

```json
{
  "position": { "x": -487.0, "y": 1557.0 },
  "message": "este es un mensaje secreto"
}
```

## Pruebas

Para ejecutar las pruebas, usa el siguiente comando: ./gradlew test

puedes ver y probar todos los endpoints disponibles en el proyecto.