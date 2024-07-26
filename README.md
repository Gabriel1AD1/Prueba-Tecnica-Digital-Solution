
# Social Network Application

## Descripción

Esta es una aplicación de red social en modo consola que permite a los usuarios publicar mensajes, seguir a otros usuarios y ver un dashboard con los mensajes de las personas que siguen.

## Requisitos

- Java 17 o superior
- Gradle

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/Gabriel1AD1/Prueba-Tecnica-Digital-Solution
    cd Digital-Solutions
    ```

2. Construye el proyecto con Gradle:
    ```sh
    gradle build
    ```

## Ejecución

Para ejecutar la aplicación en modo consola:
```sh
gradle run
```

## Ejemplos de Uso

### Publicar un mensaje
```sh
post @Alfonso Hola Mundo
```
Resultado:
```
Alfonso posted -> "Hola Mundo" @10:30
```

### Seguir a un Usuario
```sh
follow @Alicia @Ivan
```
Resultado:
```
Alicia empezó a seguir a Iván
```

### Ver el Dashboard
```sh
dashboard @Alicia
```
Resultado:
```
"Hoy puede ser un gran día" @Ivan @08:10
"Hola mundo" @Alfonso @10:30
```
