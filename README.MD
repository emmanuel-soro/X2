# Sistemas Operativos Avanzados

## Grupo: X2
- Frattini, Maximiliano Gabriel DNI: 26.849.323
- Hornos, Nicolas DNI: 36.076.620
- Rodeiro, Gonzalo DNI: 37.753.908
- Salva, Ricardo Nicolás DNI: 38.142.454
- Soro, Emmanuel DNI: 33.778.589

## Proyecto: SmartFarm :seedling::electron:
### Descripción
SmartFarm pretende monitorear el crecimiento de las plantas en cuanto a su follaje y a su tronco/tallo, mediante procesamiento de imagenes, y reportar estadísticas del crecimiento en base a los datos obtenidos a la aplicación mobile desarrollada en android.

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

* Módulo Red W5100

![imagenRed](https://i.imgur.com/Cxsl3RF.png)

El W5100 es un controlador de Ethernet fabricado por Wiznet especialmente diseñado para aplicaciones embebidas. Podemos emplear este controlador con un procesador como Arduino para implementar comunicación por internet.
Incluye una pila de TCP/IP por hardware y buffer interno de 16Kbytes para Tx/Rx. Esto permite liberar de estas tareas al procesador, siendo una de sus principales ventajas frente a otros controladores de Ethernet como el ENC28J60.

* Cámara Vivotek IP8133

![imagenCamaraFrontal](https://i.imgur.com/6mES8IZ.png)
![imagenCamaraTrasera](https://i.imgur.com/yVQaOoq.png)

Carácteristicas:

-Image Sensor 1/4" Progressive CMOS

-Max. Resolution 1280x800 (1MP)

-Lens Type Fixed-focal

-Focal Length f = 3.45 mm

-Aperture F2.4

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

### Diagrama de Arquitectura General

![diagramaArq](https://i.imgur.com/OxN4loy.png)

### Diagrama Funcional

![diagramaFuncional](https://i.imgur.com/EbUnzOe.png)

### Diagrama Físico

![diagramaFisico](https://i.imgur.com/WfSehHu.png)
