
/****************************************************************************
  --------------------------------------------------------------------------
  | Proyecto      : Smart Farm
  | Actualizado   : 28/09/2019
  | Tema          : Modulo Wifi 
  | Autores       : ~ Frattini, Maximiliano Gabriel (DNI: 26.849.323)
  |                 ~ Rodeiro, Gonzalo (DNI: 37.753.908)
  |                 ~ Salva, Ricardo Nicolás (DNI: 38.142.454)
  |                 ~ Soro, Emmanuel (DNI: 33.778.589)
  --------------------------------------------------------------------------
*****************************************************************************/

#include <ESP8266WiFi.h>

/* Constantes */
#define TIME_OUT 10

/* Conexion de red */
const char* wifiID = "Speedy-Fibra-F58CFB";
const char* wifiPass= "2b8b4d39D6FaDd5a2Y97";
int timeoutConexion = 10;

void setup() {
  Serial.begin(115200);
  delay(1000);
  if(conectar()){
     Serial.println("");
     Serial.println("Conexion exitosa!");
     Serial.println("IP address: ");
     Serial.println(WiFi.localIP()); //Mostramos la ip obtenida
  }
}
 
void loop() {
  
}

bool conectar(){
  /* Conexión wifi */
  Serial.println("");
  Serial.print("Intentando conectar a:");
  Serial.println(wifiID);

  WiFi.begin(wifiID,wifiPass);
  WiFi.mode(WIFI_STA); //Modo cliente wifi  

  while (WiFi.status() != WL_CONNECTED && (timeoutConexion > 0)){
    delay(500);
    Serial.print(".");
    timeoutConexion--;
  }

  if (WiFi.status() == WL_CONNECTED){
    return true;
  } else{
    Serial.print("\nNo pudo conectarse a la red: ");    
    Serial.println(wifiID);
    return false;
  }
}
