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
![Diagrama Entidad-Relación](images/img.png) 

## Documentación Swagger

## Despliegue

## Sonarcloud

## Dockerfile

---

## 3. Diagrama de Flujo  
![Diagrama de Flujo](images/img_1.png)  

