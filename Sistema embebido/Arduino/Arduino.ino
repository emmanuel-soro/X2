
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
#define valorLow           0

void setup(){  
  Serial.begin(9600);   // 9600: puerto serie
  
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
  estadoReposo();
  estadoPulsador();
  estadoFollaje();
  estadoTallo();
}

void estadoReposo(){
  prenderLuces(valorLow);
}

void estadoPulsador(){
  if (digitalRead(pulsador) == HIGH) {
    prenderLuces(valorHigh);
  }else{
    prenderLuces(valorLow);
  }
}

void estadoFollaje(){
  dimerizarPines(sensorPin_der, ledPin_der);
  dimerizarPines(sensorPin_izq, ledPin_izq);
  dimerizarPines(sensorPin_arr, ledPin_arr);
  // digitalWrite(ledPin_aba, HIGH);
}

void prenderLuces(int valor){
  digitalWrite(ledPin_der, valor);
  digitalWrite(ledPin_izq, valor);
  digitalWrite(ledPin_arr, valor);
  digitalWrite(ledPin_aba, valor);
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
  digitalWrite(ledPin_der, valorLow);
  digitalWrite(ledPin_izq, valorLow);
  digitalWrite(ledPin_arr, valorLow);
  digitalWrite(ledPin_aba, valorHigh);
}
