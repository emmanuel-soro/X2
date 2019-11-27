# Sistemas Operativos Avanzados

## Grupo: X2
- Frattini, Maximiliano Gabriel DNI: 26.849.323
- Rodeiro, Gonzalo DNI: 37.753.908
- Salva, Ricardo Nicolás DNI: 38.142.454
- Soro, Emmanuel DNI: 33.778.589

## Proyecto: SmartFarm :seedling::electron:
### Descripción
SmartFarm pretende monitorear el crecimiento de las plantas en cuanto a su follaje, mediante procesamiento de imágenes, y reportar estadísticas del crecimiento en base a los datos obtenidos a la aplicación mobile desarrollada en android.

El follaje de una planta es un termino que se utiliza para designar al conjunto de ramas cargadas de hojas, flores y frutos.
Puede ser:
- *Aplanado*: Cuando las hojas abiertas se extienden de uno y otro lado sobre el mismo plano.
- *Redondo o Cilíndrico*: Cuando las hojas se extienden alrededor de las ramas.
- *Cruzado*: Cuando las ramas presentan las hojas opuestas.

### Materiales

* Pecera FibroFacil/Madera

![imagenPecera](https://i.imgur.com/W75Drq4.png)

Alto: 35cm
Ancho: 20cm
Largo: 70cm

### Hardware (embebido)
* Placa Arduino UNO 

![imagenPlaca](https://i.imgur.com/BDuNlqA.png)

Arduino Uno es una placa electrónica basada en el microcontrolador ATmega328. Cuenta con 14 entradas/salidas digitales, de las cuales 6 se pueden utilizar como salidas PWM (Modulación por ancho de pulsos) y otras 6 son entradas analógicas. Además, incluye un resonador cerámico de 16 MHz, un conector USB, un conector de alimentación, una cabecera ICSP y un botón de reseteado. 

* Módulo Wifi Nodemcu

![imagenRed](https://i.imgur.com/8OSpDaY.png)

Está basado en el SoC (System on Chip) ESP8266, un chip altamente integrado, diseñado para las necesidades de un mundo conectado. Integra un potente procesador con Arquitectura de 32 bits y conectividad Wifi. Ofrece una completa y autocontenida solución WiFi Networking, permitiéndole trabajar como host de aplicaciones o reducir la carga de WiFi Networking de otro procesador.
La gran ventaja de este desarrollo con respecto al resto de módulos básicos de ESP8266, es que su programación se hace totalmente transparente, al no requerir ningún cambio en sus pines para la programación, y disponer de conexión USB al igual que Arduino
El módulo ESP-01 viene cargado de fabrica con el firmware AT, que permite conectar un Arduino a wifi utilizando comandos AT.


* Cámara Vivotek IP8133

![imagenCamaraFrontal](https://i.imgur.com/FMJxtRJ.png)

Target Eyeball Dome es una cámara de red para exteriores equipada con un filtro automático de corte IR y un LED IR para vigilancia diurna y nocturna. La cámara se adhiere a los estándares IP67. La cámara es compatible con el códec de video H.265 para lograr una mejor relación de compresión al tiempo que mantiene imágenes de alta calidad con anchos de banda de red reducidos. Con su WDR Pro, puede procesar escenas con una intensidad de luces contrastante y producir una imagen clara.

Carácteristicas:

-Modelo             EBD4700

-Iris               fijo

-Longitud focal     2.8 mm

-FOV horizontal     100 °

-Apertura máxima    F / 2.0

-Formato de imagen  1/3 "

* Led con PWM x8

![imagenLed](https://i.imgur.com/MTJZ4PK.png)

Un LED es un tipo particular de diodo que emite luz al ser atravesado por una corriente eléctrica.
Un diodo es una unión de dos materiales semiconductores con dopados distintos. Esta diferencia de dopado hace que genere una barrera de potencial, que como primera consecuencia hace que el paso de corriente en uno de los sentidos no sea posible.
Entonces, los diodos tienen polaridad, es decir, solo dejan pasar la corriente en un sentido.
La patilla larga debe ser conectada al voltaje positivo (ánodo), y la corta al voltaje negativo (cátodo).

Para calibrar la intensidad de los led utilizaremos PWM (Pulse Width Modulation). Lo que hace este tipo de señal es emitir, en lugar de una señal continua de salida,  es generar una serie de pulsos que se pueden variar en su duración pero a frecuencia constante. Así, la tensión promedio resultante es directamente proporcional a la duración de los pulsos dentro del rango del periodo indicado. Esto es, cuanto más juntos estén esos pulsos de +5v, mayor será la tensión promedio de nuestra salida, y cuanto más distantes sean estos, menor será la tensión.

![imagenPWM](https://i.imgur.com/xi4ujKd.png)

* Fuente 12v 5a Switching

![imagenFuente](https://i.imgur.com/ICGIBaO.png)

Para alimentar el sistema utilizaremos una fuente con las siguientes características:
- Fusible interno electrónico que evita la sobrecarga de la fuente.
- Entrada: 100-240V Automática 50/60 Hz.
- Carcasa Ignífuga.
- Conector: 5.5 x 2.1 mm.
- Voltaje de Salida: 12 Vcc.
- Dimensiones: 110 x 53 x 32 mm.
- Longitud de cable: 1 mts.

* Sensor de Luz x8

![imagenSensorLuz](https://i.imgur.com/dKVYvqw.png)

Un fotoresistor, o LDR (light-dependent resistor) es un dispositivo cuya resistencia varia en función de la luz recibida. Usaremos esta variación para medir, a través de las entradas analógicas, una estimación del nivel del luz dentro de la pecera.

Su funcionamiento se basa en el efecto fotoeléctrico. Un fotorresistor está hecho de un semiconductor de alta resistencia como el sulfuro de cadmio (Cds). Si la luz que incide en el dispositivo es de alta frecuencia, los fotones son absorbidos por las elasticidades del semiconductor dando a los electrones la suficiente energía para saltar la banda de conducción. El electrón libre que resulta, y su hueco asociado, conducen la electricidad, de tal modo que disminuye la resistencia. Los valores típicos varían entre 1 MΩ, o más, en la oscuridad y 100 Ω con luz brillante.

Las células de sulfuro del cadmio se basan en la capacidad del cadmio de variar su resistencia según la cantidad de luz que incide en la célula. Cuanta más luz incide, más baja es la resistencia. Las células son también capaces de reaccionar a una amplia gama de frecuencias, incluyendo infrarrojo (IR), luz visible, y ultravioleta (UV).

* Regulador de tensión 12V

![imagenTransistor](https://i.imgur.com/76nk9LJ.png)

Un regulador de tensión o regulador de voltaje es un dispositivo electrónico diseñado para mantener un nivel de tensión constante. Utilizaremos reguladores de tensión para lograr variar la potencia que llega a las luces LED mediante PWM.

* Fuente 12v 7a

![imagenBat](https://i.imgur.com/Rs3sfXR.png)

Utilizaremos una fuente adicional como fuente de energía externa para poder alimentar de manera correcta a las luces LED.

### Diagrama de Arquitectura General

![diagramaArq](https://i.imgur.com/iRVFk6D.png)

### Diagrama Funcional

![diagramaFuncional](https://i.imgur.com/jS8N7Tj.png)

### Diagrama Físico

![diagramaFisico](https://i.imgur.com/2lbGpZT.png)

![diagramaEsquematico](https://i.imgur.com/D04Codu.png)

### Diagrama Lógico

![diagramaLog](https://i.imgur.com/Q1zch5a.png)

### Diagrama de Software

![diagramaSW](https://i.imgur.com/OAWJ0Yu.png)

### Diagrama de Conexiones

![diagramaConex](https://i.imgur.com/i1r7j5J.png)

### Prototipo 1:

Este primer prototipo resulta bastante simple, pero necesario para tener una primera aproximación. Consiste en hacer funcionar dos luces LED conectandolas a la placa Arduino UNO.

![imagenProt1](https://i.imgur.com/pNulxLQ.png)

### Prototipo 2:

Este prototipo consiste en realizar el circuito necesario para poder obtener la información captada por un sensor de luz (LDR). Utilizamos una protoboard, una resistencia, un sensor de luz y la placa arduino UNO.

![imagenProt2](https://i.imgur.com/DGJl7Tv.png)

### Prototipo 3:

Aquí integramos el prototipo 1 junto al prototipo 2. Logramos obtener los datos leídos por el sensor de luz y en base a ellos, poder controlar la potencia que reciben las luces led mediante PWM.

![imagenProt3](https://i.imgur.com/e31PIQM.png)

### Prototipo 4:

En el anterior prototipo surgió el problema de que la potencia máxima que podiamos enviar con la placa arduino UNO era insuficiente, por lo que decidimos agregar una fuente externa para poder brindar una mayor potencia a las luces led.

![imagenProt4](https://i.imgur.com/qQUow4z.png)

### Prototipo 5:

Aquí incorporamos la estructura, comenzamos trabajando con una caja de carton en la cual añadimos el prototipo 4, cambiando las leds por una tira de luces led para poder obtener una luz más potente.

![imagenProt5](https://i.imgur.com/r1Hrp9p.png)

### Prototipo 6:

En este prototipo, tenemos la estructura de fibrofacil y dos tiras led con las cuales comenzamos a realizar medidas que tienen los sensores con determinadas potencias de las leds.

![imagenProt6](https://i.imgur.com/GT0os2S.png)

### Prototipo 7:

Aquí incorporamos la cámara, las leds ya ubicadas (faltando la led BAJA), se refinó el circuito y se lo ubicó en la esctructura. Decidimos reemplazar la batería por una fuente externa de 12v y 7a debido a que la batería tenia un peso significativo. En este prototipo ya pudimos obtener una imagen tomada por la camara mediante una petición enviada al servidor, el cual se comunica con la cámara para tomar un snapshot y la almacena.

![imagen1Prot7](https://i.imgur.com/p40qbOi.png)

![imagen1Prot7](https://i.imgur.com/rQ1NN5H.png)

![imagen1Prot7](https://i.imgur.com/UA2CwJR.png)

### Manual de Usuario
- El usuario ingresará a la aplicación Smart Farm desde su dispositivo Android (previamente descargada e instalada). 
Versión mínima de Android: 4.4 KitKat
- El usuario podrá observar el estado de los sensores, obtener la última imagen de la planta o ver las estadísticas realizadas por el procesamiento de imagen.

**Estado de los sensores**

1. Desde el dispositivo Android en la aplicación SmartFarm, el usuario deberá ingresar a la actividad "Estado de los sensores".
2. Se realizará la sincronizacion de arduino con el entorno creado en el dispositivo SmartFarm, donde se podra calibrar el estado de las leds. Los posibles estados que presenta el aplicativo es:
    *  Estado Reposo / inicial
    *  Estado de las leds
    *  Estado Follaje
    *  Estado Tallo

**Obtener reporte del crecimiento de la planta**

1. Desde el dispositivo Android en la aplicación SmartFarm, el usuario deberá ingresar a la actividad "Obtener estadísticas".
2. Se realizará la sincronización de la cámara con el entorno creado en el dispositivo SmartFarm, calibrando las Leds para obtener una luz correcta para tomar imágenes. Y se realizará el procesamiento de las mismas para obtener los datos:
    *  Nivel de follaje

Que se utilizarán para realizar una comparativa en caso de que existan datos anteriores sobre dicha planta, y se los informará. De lo contrario, se informará el Diámetro estimado del tallo y el nivel de follaje.

**Sensores utilizados**
-	El sensor TYPE_ACCELEROMETER mide la aceleración aplicada al dispositivo. Cuando detectamos un shake se accederá a la Activity “Estado de los sensores” y activara el estado tallo.
-	El sensor TYPE_PROXIMITY proporciona la distancia entre el dispositivo y otro objeto. Cuando detectamos que el sensor de proximidad del teléfono este tapado se accederá a la Activity “Obtener foto”
-	El sensor TYPE_LIGHT proporciona la luz ambiente en unidad de medida lux. Cuando se detecta que hay mucha luz ambiente, se muestra un mensaje por pantalla mostrando exactamente su valor.
