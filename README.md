
# Social Network Application

## Descripción

Esta es una aplicación de red social en modo consola que permite a los usuarios publicar mensajes, seguir a otros usuarios y ver un dashboard con los mensajes de las personas que siguen.
La app maneja un estado por cada inicio de session y cada cerrado de session mejora la forma de enviar mensajes y tiene un estado que permite evitar escribir doble codigo

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

### Login Data de Prueba
username:
```sh
Ivan
```

password:
```sh
1234
```


### Publicar un mensaje
```sh
post Hola Mundo
```
Resultado:
```
Alfonso posted -> "Hola Mundo" @10:30
```

### Seguir a un Usuario
```sh
follow @Ivan
```
Resultado:
```
Alicia empezó a seguir a Iván
```

### Ver el Dashboard
```sh
dashboard 
```
Resultado:
```
"Hoy puede ser un gran día" @Ivan @08:10
"Hola mundo" @Alfonso @10:30
```
