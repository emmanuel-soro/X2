
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
#define ledPin_der         10
#define ledPin_izq         5
#define ledPin_arr         6
#define ledPin_abajo       9

#define sensorPin_der      A3
#define sensorPin_izq      A2
#define sensorPin_arr      A4
#define sensorPin_abajo    A5

/* Modo de ejecucion */
int lecturaSensor_der = 0;
int lecturaSensor_izq = 0;
int lecturaSensor_arr = 0;
int lecturaSensor_abajo = 0;
 
void setup(){
  Serial.begin(9600);   // 9600: puerto serie
  
  /* Inicializo LEDs  */
  pinMode(ledPin_der , OUTPUT);
  pinMode(ledPin_izq , OUTPUT);
  pinMode(ledPin_arr , OUTPUT);
  //pinMode(ledPin_abajo , OUTPUT);
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

  lecturaSensor_arr = analogRead(sensorPin_arr); 
  if(lecturaSensor_arr >= 75){
    analogWrite(ledPin_arr, 80);
    delay(250);
  }else{
    digitalWrite(ledPin_arr , HIGH);
  }  

  //lecturaSensor_abajo = analogRead(sensorPin_abajo); 
  //if(lecturaSensor_abajo >= 75){
  //  analogWrite(ledPin_abajo, 80);
  //  delay(250);
  //}else{
  //  digitalWrite(ledPin_abajo , HIGH);
  //}  
}
