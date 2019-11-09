
/****************************************************************************
  --------------------------------------------------------------------------
  | Proyecto      : Smart Farm
  | Actualizado   : 02/11/2019
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
char data = "R";

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
  /* 4 Estados de las luces */
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
    }
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
