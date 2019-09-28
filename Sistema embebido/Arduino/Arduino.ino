
/****************************************************************************
  --------------------------------------------------------------------------
  | Proyecto      : Smart Farm
  | Actualizado   : 28/09/2019
  | Tema          : Arduino 
  | Autores       : ~ Frattini, Maximiliano Gabriel (DNI: 26.849.323)
  |                 ~ Rodeiro, Gonzalo (DNI: 37.753.908)
  |                 ~ Salva, Ricardo Nicolás (DNI: 38.142.454)
  |                 ~ Soro, Emmanuel (DNI: 33.778.589)
  --------------------------------------------------------------------------
*****************************************************************************/

/* Constantes */
#define ledPin_der         3
#define ledPin_izq         5
#define sensorPin_der      A3
#define sensorPin_izq      A2

/* Modo de ejecucion */
int lecturaSensor_der = 0;
int lecturaSensor_izq = 0;
 
void setup(){
  Serial.begin(9600);   // 9600: puerto serie
  
  /* Inicializo LEDs  */
  pinMode(ledPin_der , OUTPUT);
  pinMode(ledPin_izq , OUTPUT);
}
 
void loop(){
  lecturaSensor_der = analogRead(sensorPin_der); 
  if(lecturaSensor_der >= 75){
    analogWrite(ledPin_der, 80);
    delay(250);
  }else{
    digitalWrite(ledPin_der , HIGH);
  }  
  
  lecturaSensor_izq = analogRead(sensorPin_izq); 
  if(lecturaSensor_izq >= 75){
    analogWrite(ledPin_izq, 80);
    delay(250);
  }else{
    digitalWrite(ledPin_izq , HIGH);
  }  
}
