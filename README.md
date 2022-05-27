# sugestionService
 Web Service for recieving USA and Canada cities information

Este proyecto esta creado con SpringBoot, utiliza SpringData y SpringDataMongo para gestionar una base de
datos embebida de MongoDB.

La base de datos es FlappDoodle Embedded MongoDB.

Esta es la URL base del proyecto desplegado en google cloud plattform
https://suggestionservice-351517.uc.r.appspot.com/

Esta es la URL para obtener informacion de todas las ciudades
https://suggestionservice-351517.uc.r.appspot.com/cities

Esta es la URL donde se despliega la API
https://suggestionservice-351517.uc.r.appspot.com/cities/suggestions
Y se utilizan 3 parametros 
?name es el parametro para buscar por nombre de la ciudad
&lon es el parametro de longitud
&lat es el parametro de latitud

Se puede enviar solo el parametro de '?name' pero no genera puntajes (Score)

Este metodo retorna 20 resultados ordenados por puntaje 
curl https://suggestionservice-351517.uc.r.appspot.com/cities/suggestions?name=we&lat=43&long=-78

Este metodo retorna 154 resultados, pero no retorna un puntaje, debido a que no utiliza los 
parametros de latitud y longitud
curl https://suggestionservice-351517.uc.r.appspot.com/cities/suggestions?name=west