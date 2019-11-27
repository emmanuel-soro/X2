/****************************************************************************
  --------------------------------------------------------------------------
  | Proyecto      : Smart Farm
  | Actualizado   : 16/11/2019
  | Tema          : Arduino 
  | Autores       : ~ Frattini, Maximiliano Gabriel (DNI: 26.849.323)
  |                 ~ Rodeiro, Gonzalo (DNI: 37.753.908)
  |                 ~ Salva, Ricardo Nicolás (DNI: 38.142.454)
  |                 ~ Soro, Emmanuel (DNI: 33.778.589)
  --------------------------------------------------------------------------
*****************************************************************************/

/* Constantes */
#define ledPin_arr         5
#define ledPin_izq         10
#define ledPin_der         6
#define ledPin_aba         9

#define sensorPin_arr      A2
#define sensorPin_izq      A3
#define sensorPin_der      A4

#define pulsador           8

#define valorHigh          255
#define valorLow           15
#define valorOff           0

#include <SoftwareSerial.h>
SoftwareSerial SerialEsp(2, 3); // RX - TX

char data = ' '; //Variable global que se utiliza para capturar las peticiones del nodemcu por el puerto serie "SerialEsp".
char estadoAnterior = ' ';

/* Flags de tiempo */
unsigned long startMillis;
unsigned long currentMillis;
unsigned long tiempoFotoDiaria = 2500;

void setup(){  
  Serial.begin(19200);
  SerialEsp.begin(9600);
  
  /* Inicializo LEDs  */
  pinMode(ledPin_der, OUTPUT);
  pinMode(ledPin_izq, OUTPUT);
  pinMode(ledPin_arr, OUTPUT);
  pinMode(ledPin_aba, OUTPUT);

  /* Inicialiazo pulsador */
  pinMode(pulsador, INPUT);

  startMillis = millis();
}

void loop()
{
  if (digitalRead(pulsador) == HIGH) 
  {
     prenderLucesDigital(valorHigh);
  }
  else
  {
     checkSerialCom();
     switch (data)
     {
        case 'R': 
          Serial.println("ENTRE ESTADO REPOSO");
          estadoReposo();
          break;
        case 'F': 
          Serial.println("ENTRE ESTADO FOLLAJE");
          estadoFollaje();
          break;
        case 'W':  
          Serial.println("ENTRE ESTADOS DE EVALUACION DE SENSORES Y LUCES");
          estadoSensores();
          estadoLuces();
          break;     
        case 'T': 
          Serial.println("ENTRE ESTADO TALLO");
          estadoTallo();
          break;
        case 'D':
          Serial.println("FOTO DIARIA");
          estadoFotoDiaria();
          break;
        default:
          Serial.println("defecto");
          estadoReposo();
          break;       
     }
  }
}

void estadoReposo()
{
  prenderLucesAnalogico(valorLow);
}

void estadoFollaje()
{
  digitalWrite(ledPin_aba, valorOff);
  /* Ciclo necesario ya que la dimerizacion es en TR, no es bloqueante.
   * Ante algun cambio debido a una peticion del mcu o de presionar el
   * boton, sale del ciclo.
   */
  while(data == 'F' && digitalRead(pulsador) == LOW)
  {
    checkSerialCom();
    dimerizarPines(sensorPin_der, ledPin_der);
    dimerizarPines(sensorPin_izq, ledPin_izq);
    dimerizarPines(sensorPin_arr, ledPin_arr);
  }
}

void estadoFotoDiaria()
{
  /* Funcion para tomar la foto diaria. Se espera un tiempo para
   * Sincronizar la camara con el ambiente del SE. Cualquier peticion
   * sera recibida. Que el boton este prendido no genera un conflicto
   * al tomar la imagen. Ciclo necesario debido a la dimerizacion en
   * TR. Sin el ciclo, las luces parpadean.
   */
  digitalWrite(ledPin_aba, valorOff);
  startMillis = millis();
  currentMillis = millis();
  while(currentMillis - startMillis < tiempoFotoDiaria){
    currentMillis = millis();
    checkSerialCom();
    dimerizarPines(sensorPin_der, ledPin_der);
    dimerizarPines(sensorPin_izq, ledPin_izq);
    dimerizarPines(sensorPin_arr, ledPin_arr);
  }
  if(data == 'D')
  {
    data = estadoAnterior;
  }
  
}

void prenderLucesDigital(int valor)
{
  digitalWrite(ledPin_der, valor);
  digitalWrite(ledPin_izq, valor);
  digitalWrite(ledPin_arr, valor);
  digitalWrite(ledPin_aba, valor);
}

void prenderLucesAnalogico(int valor)
{
  analogWrite(ledPin_der, valor);
  analogWrite(ledPin_izq, valor);
  analogWrite(ledPin_arr, valor);
  analogWrite(ledPin_aba, valor);
}

void dimerizarPines(int sensor, int pin)
{
  int lecturaSensor = analogRead(sensor); 
  if(lecturaSensor >= 255)
  {
    analogWrite(pin, 0);
  }
  else
  {
    analogWrite(pin, 255 - lecturaSensor);
  }
}

void estadoTallo()
{
  digitalWrite(ledPin_der, valorOff);
  digitalWrite(ledPin_izq, valorOff);
  digitalWrite(ledPin_arr, valorOff);
  digitalWrite(ledPin_aba, valorHigh);
}

void checkSerialCom()
{
  if(SerialEsp.available() > 0)
  { 
    while(SerialEsp.available() > 0)
    {
      char aux;
      aux = (char)SerialEsp.read(); // Leemos del puerto serial.
      /* Verificamos que la peticion sea correcta */
      if(aux == 'R' || aux == 'F' || aux == 'T' || aux == 'W')
        data = aux;
      if(aux == 'D')
      {
        estadoAnterior = data;
        data = aux;
      }
      Serial.print(data);
    }
  }
}

void estadoSensores()
{
  /* Ponemos las luces en valor alto */
  prenderLucesDigital(valorHigh);
  
  delay(100); //Tiempo para que los sensores LDR se estabilicen
  
  /* Leemos las lecturas de los sensores LDR */
  int lecturaSensor1 = analogRead(sensorPin_izq);  
  int lecturaSensor2 = analogRead(sensorPin_der); 
  int lecturaSensor3 = analogRead(sensorPin_arr);
  
  /* Ponemos las luces en valor bajo */  
  prenderLucesDigital(0);
  
  delay(100); //Tiempo para que los sensores LDR se estabilicen
  
  /* Leemos las nuevas lecturas de los sensores LDR */
  int lecturaSensor1b = analogRead(sensorPin_izq);  
  int lecturaSensor2b = analogRead(sensorPin_der); 
  int lecturaSensor3b = analogRead(sensorPin_arr);
  
  /* Comparamos las distintas lecturas de cada sensor
   * si no variaron signfica que el sensor no funciona  */ 
  if (lecturaSensor1 - lecturaSensor1b > 50 )
    Serial.println("Sensor 1 OK");
  else
    Serial.println("Sensor 1 No funciona");
  if (lecturaSensor2 - lecturaSensor2b > 50 )
    Serial.println("Sensor 2 OK");
  else
    Serial.println("Sensor 2 No funciona");
  if (lecturaSensor3 - lecturaSensor3b > 50 )
    Serial.println("Sensor 3 OK");
  else
    Serial.println("Sensor 3 No funciona");    
}

void estadoLuces()
{
  boolean der = estadoLuz(255,0,0,0,"Derecha");
  boolean izq = estadoLuz(0,255,0,0,"Izquierda");
  boolean arr = estadoLuz(0,0,255,0,"Arriba");
  boolean abj = estadoLuz(0,0,0,255,"Abajo");
}

boolean estadoLuz(int valor_der, int valor_izq, int valor_arr, int valor_aba, String luz )
{
  /* Ponemos las luces en los valores determinados por parametro. La led que se probara
   * tendra un valor maximo, mientras que las demas tendran un valor minimo. */
  digitalWrite(ledPin_der, valor_der);  
  digitalWrite(ledPin_izq, valor_izq);
  digitalWrite(ledPin_arr, valor_arr);
  digitalWrite(ledPin_aba, valor_aba);

  /* Capturamos las lecturas de los sensores LDR */
  int lecturaSensor1a = analogRead(sensorPin_izq);  
  int lecturaSensor2a= analogRead(sensorPin_der); 
  int lecturaSensor3a = analogRead(sensorPin_arr); 

  delay(100); //Tiempo prudencial para estabilizar los sensores LDR

  /* Ponemos a todas las luces en valor minimo */
  prenderLucesDigital(0);
  
  /* Capturamos las nuevas lecturas de los sensores LDR */
  int lecturaSensor1c = analogRead(sensorPin_izq);
  int lecturaSensor2c = analogRead(sensorPin_der);
  int lecturaSensor3c = analogRead(sensorPin_arr);
  
  delay(100); //Tiempo prudencial para estabilizar los sensores LDR
  
  /* Si las lecturas cumplen la condicion, la luz funciona. De lo contrario, no funciona */
  if (lecturaSensor1c > lecturaSensor1a  && lecturaSensor2c > lecturaSensor2a && lecturaSensor3c > lecturaSensor3a)
  {
     Serial.print("OK Luz ");
     Serial.println(luz);
     return true;
  }
  else 
  {
     Serial.print("NO Funciona luz ");
     Serial.println(luz);
     return false;
  }
}
