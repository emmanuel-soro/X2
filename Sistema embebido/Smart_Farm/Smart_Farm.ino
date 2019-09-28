const int ledPIN = 2;
int ledLDR = A2;
int LDRReading = 0;
 
void setup() {
  Serial.begin(9600);    //iniciar puerto serie
  pinMode(ledPIN , OUTPUT);  //definir pin como salida
}
 
void loop(){
  LDRReading = analogRead(ledLDR); 
  if(LDRReading >= 75){
    analogWrite(ledPIN, 30);
    delay(250); // retardo para hacer más fácil la lectura
  }else{
    digitalWrite(ledPIN , HIGH);   // poner el Pin en HIGH
  }  
}
