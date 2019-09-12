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

En este proyecto se tendran en cuenta las siguientes consideraciones:
  1. Sistema central de control (interaccion de HW y SF)
  2. Sistema de control de iluminación
  3. Sistema de procesamiento de imagenes  

### Materiales

* Pecera Acrilica/Madera

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

* Led con PWM

![imagenPlacaLed](https://i.imgur.com/luzOgDl.png)

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

* Sensor de Luz x4

![imagenSensorLuz](https://i.imgur.com/hwoIgQL.png)

Un fotoresistor, o LDR (light-dependent resistor) es un dispositivo cuya resistencia varia en función de la luz recibida. Usaremos esta variación para medir, a través de las entradas analógicas, una estimación del nivel del luz dentro de la pecera.

Un fotoresistor está formado por un semiconductor, típicamente sulfuro de cadmio CdS. Al incidir la luz sobre él algunos de los fotones son absorbidos, provocando que electrones pasen a la banda de conducción y, por tanto, disminuyendo la resistencia del componente.

Por tanto, un fotoresistor disminuye su resistencia a medida que aumenta la luz sobre él. Los valores típicos son de 1 Mohm en total oscuridad, a 50-100 Ohm bajo luz brillante.

### Diagrama Funcional

![diagramaFuncional](https://i.imgur.com/EbUnzOe.png)

### Diagrama Físico

![diagramaFisico](https://i.imgur.com/WfSehHu.png)
