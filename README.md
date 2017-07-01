# Proyecto Moviles
# UNIMAPS
### Briggette Roman Huaytalla- Rogger Valverde Flores- Victor Sotelo Chico
##### Descripción
La aplicación tiene como objetivo mostrar distintos lugares de la universidad y proporcionar una guia para que los usuarios puedan encontrar el lugar al que se dirigen. A continuación mostraremos las principales vistas y explicaremos la herramientas que usamos para implementarlas.
<br>
<br>
#### Login
<p align="center">
<img  src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima1.png" width="200" >
</p>
<br>
<br>

#### Registro
La aplicación genera una base de datos en SQLite para guardar los registros. Ademas se guardan en las preferencias, la sesión del usuario para que este no inicie sesión cada vez que abra la aplicación.
<p align="center">
<img  src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima8.png" width="200" >
</p>
<br>
<br>



#### Menu principal
En el menu principal tenemos 3 opciones que permitiran:
- Dirigirse al Mapa
- Entrar a la opción de orientación
- Utilizar la cámara
Además el menu principal contara con un navigation view.
<p align="center">
<img class="left" src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima4.png" width="200" >

<img class="right" src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima2.png" width="200" >
</p>

<br>
<br>

#### Lugares
Los en este fragments mostraremos distintos lugares de la universidad para implementarlo hicimos uso de:
- Cardview: Permite mostrar un conjunto de resultados de lugares en la universidad.
- Pestañas: Permite clasificar distintos tipos de lugares: Centros, Facultades y Oficinas.
- Sqlite: La base de datos sqlite fue usada para almacenar los lugares, en este caso se leyó un base de datos ya existente la cual estuvo almacenada en la carpeta assets.

<p align="center">
<img  src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima3.png" width="200" >
</p>
<br>
<br>

#### Mapa
En este fragment usamos el **API de GoogleMaps** con el objetivo de mostrarle al usuario su posición y los distintos lugares que fueron ubicados con marcadores.
<p align="center">
<img  src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima5.png" width="200" >
</p>

<br>
<br>

#### Orientación
En la activity **orientacion** hacemos uso de la clase **locationManager** la cual nos proveerá información de nuestra ubicación actual además usamos los siguientes sensores: acelerómetro y campo magnetico para detectar la rotación del celular ademas haciendo uso de funciones como Bearingto que nos proporciona grados para poder direccionar la flecha orientadora.
<p align="center">
<img class="left" src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima6.png" width="200" >

<img class="right" src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima7.png" width="450" >
</p>
<br>
<br>

#### Cámara
En este fragment usamos OpenGL y la el dispositivo camara con el objetivo de dibujar un indicador similar al de orientación e ir indicar la dirección que debera de seguir el usurio mientras ve la pantalla. De momento esta en proceso.
<p align="center">
<img  src="https://github.com/Visot/ProyectoMoviles/blob/master/img/ima9.png" width="500" >
</p>
