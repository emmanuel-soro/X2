

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

int lecturaSensor = 0;

#include <SoftwareSerial.h>
SoftwareSerial SerialEsp(2, 3); // RX - TX
char data = "W";

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
}

void loop(){
  /* 4 Estados de las luces 
  if(!estadoPulsador()){
    checkSerialCom();
    if(data == 'F'){
      Serial.println("ENTRE ESTADO FOLLAJE");
      estadoFollaje();
    }
    if(data == 'T'){
      Serial.println("ENTRE ESTADO TALLO");
      estadoTallo();
      delay(5000);
      }
    if(data == 'W'){
      Serial.println("ENTRE ESTADOS DE SENSORES Y LUCES");
      estadoSensores();
      delay(2000);
      estadoLuces();
      delay(2000);
      } 
    }*/   

      estadoSensores();
      delay(2000);
      estadoLuces();
      delay(2000);
}

void estadoReposo(){
  prenderLucesAnalogico(valorLow);
}

boolean estadoPulsador(){
  if (digitalRead(pulsador) == HIGH) {
    prenderLucesDigital(valorHigh);
    return true;
  }else{
    estadoReposo();
    return false;
  }
}

void estadoFollaje(){
  digitalWrite(ledPin_aba, valorOff);
  int cont = 0;
  while(cont < 10000){
    dimerizarPines(sensorPin_der, ledPin_der);
    dimerizarPines(sensorPin_izq, ledPin_izq);
    dimerizarPines(sensorPin_arr, ledPin_arr);
    cont = cont + 1;
  }
}

void prenderLucesDigital(int valor){
  digitalWrite(ledPin_der, valor);
  digitalWrite(ledPin_izq, valor);
  digitalWrite(ledPin_arr, valor);
  digitalWrite(ledPin_aba, valor);
}

void prenderLucesAnalogico(int valor){
  analogWrite(ledPin_der, valor);
  analogWrite(ledPin_izq, valor);
  analogWrite(ledPin_arr, valor);
  analogWrite(ledPin_aba, valor);
}

void dimerizarPines(int sensor, int pin){
  int lecturaSensor = analogRead(sensor); 
  if(lecturaSensor >= 255){
    analogWrite(pin, 0);
  }else{
    analogWrite(pin, 255 - lecturaSensor);
  }
}

void estadoTallo(){
  digitalWrite(ledPin_der, valorOff);
  digitalWrite(ledPin_izq, valorOff);
  digitalWrite(ledPin_arr, valorOff);
  digitalWrite(ledPin_aba, valorHigh);
}

void checkSerialCom(){
  if(SerialEsp.available() > 0){ // Checkeamos si hay informacion disponible.
    while(SerialEsp.available() > 0){
      data = (char)SerialEsp.read(); // Leemos del puerto serial.
      Serial.print(data);
    }
  }
}

void estadoSensores(){
  prenderLucesDigital(valorHigh);
  delay(200);
  int lecturaSensor1 = analogRead(sensorPin_izq);  
  int lecturaSensor2 = analogRead(sensorPin_der); 
  int lecturaSensor3 = analogRead(sensorPin_arr);  
  prenderLucesDigital(0);
  delay(200);
  int lecturaSensor1b = analogRead(sensorPin_izq);  
  int lecturaSensor2b = analogRead(sensorPin_der); 
  int lecturaSensor3b = analogRead(sensorPin_arr); 

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

void estadoLuces(){
  boolean der = estadoLuz(255,0,0,0,"Derecha");
  boolean izq = estadoLuz(0,255,0,0,"Izquierda");
  boolean arr = estadoLuz(0,0,255,0,"Arriba");
  boolean abj = estadoLuz(0,0,0,255,"Abajo");
}

boolean estadoLuz(int valor_der, int valor_izq, int valor_arr, int valor_aba, String luz ){
  digitalWrite(ledPin_der, valor_der);  
  digitalWrite(ledPin_izq, valor_izq);
  digitalWrite(ledPin_arr, valor_arr);
  digitalWrite(ledPin_aba, valor_aba);
  int lecturaSensor1a = analogRead(sensorPin_izq);  
  int lecturaSensor2a= analogRead(sensorPin_der); 
  int lecturaSensor3a = analogRead(sensorPin_arr); 
  delay(200);
  prenderLucesDigital(0);
  int lecturaSensor1c = analogRead(sensorPin_izq);  
  int lecturaSensor2c = analogRead(sensorPin_der); 
  int lecturaSensor3c = analogRead(sensorPin_arr); 
  delay(200);

  if (lecturaSensor1c > lecturaSensor1a  && lecturaSensor2c > lecturaSensor2a && lecturaSensor3c > lecturaSensor3a){
     Serial.print("OK Luz ");
     Serial.println(luz);
     return true;
  } else {
     Serial.println("NO Funciona luz ");
     Serial.println(luz);
     return false;
  }

}
