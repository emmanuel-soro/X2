# Sistemas Operativos Avanzados

## Grupo: X2
- Frattini, Maximiliano Gabriel DNI: 26.849.323
- Hornos, Nicolas DNI: 36.076.620
- Rodeiro, Gonzalo DNI: 37.753.908
- Salva, Ricardo Nicolás DNI: 38.142.454
- Soro, Emmanuel DNI: 33.778.589

## Proyecto: Smartfarm :seedling::electron:
### Descripción
Smartfarm intenta automatizar el cuidado de las plantas, monitoreando y tomando decisiones en tiempo real para el cuidado de las mismas interactuando con el usuario a través de una aplicación mobile.
En este proyecto se tendran en cuenta las siguientes consideraciones:
  1. Sistema central de control (interaccion de HW y SF)
  2. Sistema de iluminación
  3. Sistema de regado
  4. Monitoreo de crecimiento  

### Materiales
* Maceta
*	Bomba de agua

![imagenBomba](https://i.imgur.com/rVQ10FG.png)

* Pecera Acrilica

![imagenPecera](https://i.imgur.com/JgQzCvf.png)

Alto: 35cm
Ancho: 20cm
Largo: 70cm

### Hardware (embebido)
* Placa Arduino UNO 

![imagenPlaca](https://i.imgur.com/BDuNlqA.png)

Arduino Uno es una placa electrónica basada en el microcontrolador ATmega328. Cuenta con 14 entradas/salidas digitales, de las cuales 6 se pueden utilizar como salidas PWM (Modulación por ancho de pulsos) y otras 6 son entradas analógicas. Además, incluye un resonador cerámico de 16 MHz, un conector USB, un conector de alimentación, una cabecera ICSP y un botón de reseteado. 

* Cámara

* Módulo Red W5100

![imagenRed](https://i.imgur.com/Cxsl3RF.png)

El W5100 es un controlador de Ethernet fabricado por Wiznet especialmente diseñado para aplicaciones embebidas. Podemos emplear este controlador con un procesador como Arduino para implementar comunicación por internet.
Incluye una pila de TCP/IP por hardware y buffer interno de 16Kbytes para Tx/Rx. Esto permite liberar de estas tareas al procesador, siendo una de sus principales ventajas frente a otros controladores de Ethernet como el ENC28J60.

* Sensor de humedad del suelo (Higrómetro FC-28)

![imagenSensorHum](https://i.imgur.com/4vnynI5.png)

El FC-28 es un sensor sencillo que mide la humedad del suelo por la variación de su conductividad. Se distribuye con una placa de medición estándar que permite obtener la medición como valor analógico o como una salida digital, activada cuando la humedad supera un cierto umbral.
Los valores obtenidos van desde 0 sumergido en agua, a 1023 en el aire (o en un suelo muy seco). Un suelo ligeramente húmero daría valores típicos de 600-700. Un suelo seco tendrá valores de 800-1023.

* Sensor de temperatura del ambiente Arduino LM35

![imagenSensorTemp](https://i.imgur.com/QbLgyS2.png)

El sensor de temperatura Arduino LM35 es un sensor muy económico. La salida de este sensor es analógica y el calibrado lo hace directamente en grados Celsius. La temperatura que admite oscila entre los 2º C y los 150º C. 

* Sensor de nivel de agua

![imagenSensorAguaTanque](https://i.imgur.com/pSkGPQe.png)

El sensor HC-SR04 es un sensor de distancia que utiliza transductores de ultrasonido para detectar objetos. De esta forma, lo utilizaremos para medir el nivel de agua del tanque.
El Trigger (emisor) manda un pulso de ultrasonidos y el Echo (receptor) se encarga de recibirlo. Por lo tanto tenemos el tiempo que tarda en recibirlo y la velocidad (la del sonido). Entonces, la formula para calcular la distancia (que se encuentra en la ficha técnica) será:

Distancia = (Tiempo en estado HIGH * Velocidad del sonido) / 2

Se divide por 2 ya que la estamos midiendo dos veces (lo que tarda la señal en chocar con el objeto y volver).

* Placa Led con PWM

![imagenPlacaLed](https://i.imgur.com/luzOgDl.png)

* Coolers

![imagenCooler](https://i.imgur.com/OGovQMG.png)

Para disminuir la temperatura dentro de la pecera, utilizaremos un Cooler genérico.

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

### Diagrama Funcional

![diagramaFuncional](https://i.imgur.com/9L3qCes.png)

### Diagrama Físico

![diagramaFisico](https://i.imgur.com/6TUDqmG.png)
