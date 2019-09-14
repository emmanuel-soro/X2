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

* Pecera Acrilica/Madera

![imagenPecera](https://i.imgur.com/JgQzCvf.png)

Alto: 35cm
Ancho: 20cm
Largo: 70cm

### Hardware (embebido)
* Placa Arduino UNO 

![imagenPlaca](https://i.imgur.com/BDuNlqA.png)

Arduino Uno es una placa electrónica basada en el microcontrolador ATmega328. Cuenta con 14 entradas/salidas digitales, de las cuales 6 se pueden utilizar como salidas PWM (Modulación por ancho de pulsos) y otras 6 son entradas analógicas. Además, incluye un resonador cerámico de 16 MHz, un conector USB, un conector de alimentación, una cabecera ICSP y un botón de reseteado. 

* Cámara Voltek IP8133

![imagenCamaraFrontal](https://i.imgur.com/01uO4tY.png)
![imagenCamaraTrasera](https://i.imgur.com/IInZQbY.png)

* Módulo 

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
