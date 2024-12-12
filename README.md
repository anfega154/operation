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

## Arquitectura y Diseño

El proyecto sigue una Arquitectura Hexagonal, también conocida como Arquitectura de Puertos y Adaptadores, junto con los principios de Diseño Dirigido por el Dominio (DDD). Esto garantiza modularidad, escalabilidad y facilidad de mantenimiento.

Capas de la Arquitectura
1.	Core Domain (Núcleo del Dominio):
•	Contiene la lógica de negocio y las entidades principales.
•	Totalmente independiente de los adaptadores externos e infraestructura.
•	Clases principales:
•	Satellite: Representa un satélite con datos como nombre, distancia y mensaje fragmentado.
•	LocationCalculator: Determina la posición de la nave mediante trilateración.
•	MessageDecoder: Reconstruye el mensaje de auxilio.
2.	Application (Capa de Aplicación):
•	Orquesta los casos de uso del sistema y actúa como intermediario entre el núcleo del dominio y los adaptadores externos.
•	Clases principales:
•	DetermineMessageAndLocation: Caso de uso que combina la lógica de trilateración y decodificación del mensaje.
•	DTOs (objetos de transferencia de datos):
•	Response: Respuesta que incluye la posición y el mensaje decodificado.
•	SatelliteRequest, SatelliteSplitRequest: Representan las solicitudes entrantes.
3.	Infraestructura:
•	Implementa los adaptadores para las interacciones externas.
•	Clases principales:
•	QuasarController: Controlador REST que maneja los endpoints.
•	ValidationErrorResponse: Respuesta estándar para errores de validación.
•	GlobalException: Centraliza el manejo de excepciones.
•	LocalizationHelper: Manejo de textos localizados.

Flujo de Datos
1.	El cliente envía datos a través de los endpoints expuestos por QuasarController.
2.	El controlador delega los datos al caso de uso DetermineMessageAndLocation.
3.	El caso de uso invoca métodos en el núcleo del dominio (LocationCalculator, MessageDecoder) para ejecutar la lógica.
4.	El caso de uso devuelve una respuesta que el controlador REST transforma y envía al cliente.

Ventajas del Diseño
•	Desacoplamiento: La lógica central no depende de detalles de infraestructura.
•	Escalabilidad: Fácil de agregar nuevas funcionalidades mediante adaptadores o casos de uso.
•	Mantenibilidad: Responsabilidades claras entre las capas.


## Pruebas

Para ejecutar las pruebas, usa el siguiente comando: ./gradlew test

Puedes ver y probar todos los endpoints disponibles en el proyecto.

Se utilizan los siguientes frameworks:
•	JUnit 5: Para pruebas unitarias.
•	Mockito: Para pruebas unitarias con mocks.