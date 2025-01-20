# Proyecto Asociación Criptomonedas - prueba técnica Nivel 1 Bancolombia ![Project Version](https://img.shields.io/badge/tag-v1.0-pink) [![GitHub commits](https://badgen.net/github/commits/superpollo2/Backend-associate-cryptocurrency)](https://GitHub.com/superpollo2/Backend-associate-cryptocurrency/commit/) 
## Desarrolladora - Laura Vanesa Tascón Cataño - Ing se sistemas 

![Java Version](https://img.shields.io/badge/java-21-blue.svg)
![Springboot Version](https://img.shields.io/badge/springboot-3.4.1-orange)
![Jaccoco Version](https://img.shields.io/badge/jacoco-1.15.0-pink)
![Clean Architecture Version](https://img.shields.io/badge/cleanarchitecture-3.20.10-blue)
![Pitest Version](https://img.shields.io/badge/pitest-1.15.0-blue)
--------
![JWT](https://img.shields.io/badge/JWT-black?logo=JSON%20web%20tokens)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?logo=swagger&logoColor=white)

> [!NOTE]
> Este es un proyecto creado para una prueba técnica

## Índice  
1. [Bases de Datos](#2-bases-de-datos)  
   1.1. [Elección de la DB](#21-elección-de-la-db)  
   1.2. [Diagrama Entidad-Relación](#22-diagrama-entidad-relación)  
2. [Diagrama de Flujo](#3-diagrama-de-flujo)  

---
## Resumen
La Empresa X busca gestionar los usuarios que operan criptomonedas en sus intercambios con otras plataformas. Para ello, necesita asociar a cada usuario las criptomonedas que puede intercambiar según su país de origen, ya que la disponibilidad de criptomonedas varía según las regulaciones nacionales. El sistema debe permitir consultas a un servicio que interactúe con la base de datos para validar y obtener esta información.

## Objetivos del Proyecto:

1. Asociar criptomonedas permitidas a cada usuario según su país de origen.
2. Crear un servicio de consultas que permita acceder a esta información de forma eficiente.
3. Validar que los usuarios solo puedan intercambiar criptomonedas permitidas en su región.

## HU relacionada al alcance
`Como` usuario de la plataforma, `quiero` asociar nuevas criptomonedas a mi cuenta siempre que esté permitido en mi país, `para` poder realizar transacciones con ellas de forma segura y conforme a las regulaciones locales.

# 1. Arquitectura
Este proyecto se costruye usando arquitectura limpia mediante el patron o arquitectura
creada por Bancolombia llamado Scaffold
![image](https://github.com/user-attachments/assets/a9dd639e-a876-47ea-b074-6f84f49f63ec)
**documentación** https://bancolombia.github.io/scaffold-clean-architecture/docs/intro

## 2. Bases de Datos  
### 2.1. Elección de la DB


### 2.2. Diagrama Entidad-Relación  
![image](https://github.com/user-attachments/assets/b2bffd96-0c45-4033-8508-45c88256d1bd)

## Colleción postman y Swagger
De momento se encuentra desactualizada, no fue posible actualizarla
https://drive.google.com/drive/folders/1X6vRp6KC9GiFQ4bXs6o6Vn3JlGBOsIcv?usp=drive_link


## Sonarcloud
https://sonarcloud.io/project/overview?id=superpollo2_Backend-associate-cryptocurrency

---
## 3. Diagrama de Flujo  
![Diagrama de Flujo](images/img_1.png)  

## 4. Trabajo a Futuro

Aún queda trabajo por realizar en el proyecto, pero se ha logrado avanzar significativamente, alcanzando un buen porcentaje de desarrollo. Hasta el momento se han abordado aspectos clave como la gestión de recepción de errores, la implementación de validaciones de entrada mediante JsonSchema, la creación de endpoints funcionales, la realización de pruebas unitarias, y la configuración adecuada de repositorios, entre otros.

Para completar el proyecto, las próximas tareas incluyen:

- Mejorar la documentación en Swagger: Incorporar todos los casos de uso del sistema para ofrecer una visión más detallada y completa a los desarrolladores y usuarios técnicos.
- Mapear los errores en una matriz para mayor claridad y solución de problemas
- Agregar pruebas de aceptación automatizadas: Implementar herramientas como Cucumber para garantizar que todos los flujos de trabajo principales cumplan con los criterios de aceptación definidos.
- Despliegue: Realizar un despliegue eficiente en el entorno de producción, asegurando la estabilidad y disponibilidad del sistema.
- Automatización: Integrar procesos automatizados que mejoren el flujo de desarrollo, pruebas y despliegue, optimizando la entrega continua y el mantenimiento.
- Estas actividades consolidarán el proyecto y garantizarán su calidad, robustez y alineación con los objetivos iniciales.

## 5. para correr este proyecto 
Requisitos para ejecutar el proyecto:

Maven: Versión 8.0 o superior.
JDK: Java 17.
Configuración del main: El proyecto requiere configurar el archivo Main como se muestra en la siguiente imagen:
![image](https://github.com/user-attachments/assets/c68f3abb-e766-41f3-86ec-add16b1422f7)
Credenciales y base de datos: Las credenciales necesarias para la conexión se encuentran en la carpeta Drive. Cabe aclarar que, al ser un ejercicio práctico, la base de datos configurada tiene una duración limitada y expirará en aproximadamente una semana.

