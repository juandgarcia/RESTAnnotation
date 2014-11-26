# RESTAnnotation
Anotación que permite hacer llamados a servicios REST.
```
Para su ejecución se utilizó NetBeans 8.0.1
```

### Definición de la Anotación:
```
RESTAnnotation > annotation.RESTAnnotation
```
![](http://puu.sh/d6J67/6d0be08fad.png)

### Procesador:
```
RESTAnnotation > processor.RESTAnnotationProcessor
```
![](http://puu.sh/d6JDr/f997d74c9f.png)

### Uso de la Anotación:
```
RESTAnnotationDemo > demo.Main
```
![](http://puu.sh/d6JLs/3e68ba9b09.png)

### Servicio Utilizado:
```
En el proyecto RESTService se encuentra la definición del servicio REST utilizado. Se utilizó Glassfish 4.0 para el despliegue.
```
![](http://puu.sh/d6K1w/a54b305bac.png)

### Demo:
```
Una vez se compila (Build) el proyecto RESTAnnotationDemo, se hace el llamado al procesador de la anotación y se obtiene el siguiente resultado por consola:
```
![](http://puu.sh/d6KeQ/1e582a9324.png)
