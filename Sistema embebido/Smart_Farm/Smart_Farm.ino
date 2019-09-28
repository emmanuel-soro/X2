const int ledPIN = 3;
const int ledPIN2 = 5;

int ledLDR = A3;
int ledLDR2 = A2;

int LDRReading = 0;
int LDRReading2 = 0;
 
void setup() {
  Serial.begin(9600);    //iniciar puerto serie
  pinMode(ledPIN , OUTPUT);  //definir pin como salida
  pinMode(ledPIN2 , OUTPUT);  //definir pin como salida
}
 
void loop(){
  LDRReading = analogRead(ledLDR); 
  if(LDRReading >= 75){
    analogWrite(ledPIN, 80);
    delay(250); // retardo para hacer más fácil la lectura
  }else{
    digitalWrite(ledPIN , HIGH);   // poner el Pin en HIGH
  }  
  
  LDRReading2 = analogRead(ledLDR2); 
  if(LDRReading2 >= 75){
    analogWrite(ledPIN2, 80);
    delay(250); // retardo para hacer más fácil la lectura
  }else{
    digitalWrite(ledPIN2 , HIGH);   // poner el Pin en HIGH
  }  
}
