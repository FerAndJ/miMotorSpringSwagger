Proyecto final CODERHOUSE: Java

Fernando Juncos

Nombre del proyecto: MotorSpring

Mi proyecto de Spring consiste en un ABM de pilotos de multiples categorias que participaran de distintas carreras en un campeonato ficticio, compuesto por distintas carreras que fueron dadas de alta previamente
en el demo del aplicativo del sistema (asi como los distintos autos y equipos a los que puede pertencer un piloto).

El proyecto se vincula a una base de datos embebida H2 en el cual la relacion viene dadas por:

Mucho pilotos pueden pertenecer a muchas carreras (relacion muchos a muchos).

Muchos pilotos pueden pertenecer a un equipo (relacion muchos a uno).

Un piloto puede tener un auto (relacion uno a uno).

http://localhost:8080/h2-console/

URL para acceder a la base de datos embebida una vez corriendo el proyecto

user: sa
password: password

A su vez los pilotos pertenecen a distintas categorias, los autos tienen distintos tipos de motores,
y las carreras pueden tener una gama de neumaticos especifica (todo esto conseguido mediante ENUMS para
una mayor mantenibilidad).

El proyecto no consume ninguna API externa pero tiene configurado el DataController y DataRestAPI por si se requiere en un futuro.

A su vez, el proyecto expone una API REST, la cual mediante endpoints en post man, es posible realizar
las operaciones GET, POST, PUT Y DELETE de los pilotos. El proyecto requirio de mappers para pasar 
entidades a DTOs y viceversa para un manejo de bodys en la API REST y escritura de entidades en los repositorios
del proyecto.

Estas operaciones son funcionales y estan documentadas mediante swagger.

http://localhost:8080/swagger-ui/index.html

En el caso de usar postman:

GET:

localhost:8080/motorSpring/pilotos
(obtenemos todos los pilotos).


localhost:8080/motorSpring/filterCategoria?category=IMSA
(obtenemos todos los pilotos, por ejemplo, de la categoria IMSA).

localhost:8080/motorSpring/pilotosSort?order=asc
(obtenemos a los pilotos ordenados por peso, en caso de no ingresar ningun parametro, el valor por defecto sera descendente).

localhost:8080/motorSpring/piloto/1

(ejemplo para obtener al piloto 1, alonso)

POST:

localhost:8080/motorSpring/crearPiloto

(endpoint para crear un nuevo piloto)

Ejemplo:

 {
        "id": 3,
        "nombre": "Kamui Kobayashi",
        "peso": 63,
        "numero": 15,
        "nacionalidad": "Japonesa",
        "categoria": "IMSA",
        "equipo": "Mazda speed",
        "carreras": [
            "Gran premio de Sebring",
            "Gran Premio de Mónaco"
        ],
        "auto": "787B"
    }


(El proyecto valida que se ingrese un nombre y numero de piloto que no este repetido en los existentes,
sino se ingresa un ID en especifico o se agrega uno repetido, Spring automaticamente asignara uno
segun la estrategia correspondiente a la anotation del hibernate).

PUT:

localhost:8080/motorSpring/modificarPiloto/3


Se debe ingresar el id del numero de piloto que queremos modificar (se puede consultar
a que piloto pertence un cierto id con uno de los GET mencionados anteriormente) y de la misma forma
que en el POST, se introduce un body con los datos a modificar.

 {
        "id": 3,
        "nombre": "Kamui Kobayashi",
        "peso": 63,
        "numero": 25,
        "nacionalidad": "Japonesa",
        "categoria": "IMSA",
        "equipo": "Mazda speed",
        "carreras": [
            "Gran premio de Sebring",
            "Gran Premio de Mónaco"
        ],
        "auto": "787B"
    }

(ejemplo, modificamos el numero del piloto japones a 25)

Tambien valida que no se ingresen repetidos con otro piloto los nombres, numero, e ignora cualquier cambio de ID.

DELETE:

localhost:8080/motorSpring/eliminarPiloto/1

Ingresamos este endpoint si queremos eliminar al piloto 1.

(sucede a veces que la solicitud no se procesa correctamente por un problema de sincronizacion,
con la base embebida, en algunos casos es necesario simplemente ejecutar dos veces la ejecucion DELETE 
y el registro se borra correctamente.)


