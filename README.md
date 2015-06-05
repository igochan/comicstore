# comicstore

La intención es crear una aplicación JEE "pura" sin ningún tipo de dependencia para poder probar desafios técnicos que se pueden dar en el día día de un programador.

La aplicación es un EAR que contiene un EJB con toda la lógica de negocio más dos WAR, uno con el frontal REST y otra con un frontal JSF que, otra vez, no utliza librerias fuera de la implementación estandard (lease, Primefaces, RichFaces, IceFaces, etc.). Por supuesto se podría crear todo en un solo WAR pero, como dije, se trata de probar desafíos.