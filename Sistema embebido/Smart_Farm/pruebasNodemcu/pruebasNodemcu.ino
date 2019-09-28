#include <ESP8266WiFi.h>

const char* wifiID = "Speedy-Fibra-F58CFB";
const char* wifiPass= "2b8b4d39D6FaDd5a2Y97";

void setup() {
  Serial.begin(115200);
  delay(1000);

  /**** CONEXION A WIFI****/

  Serial.print("Intentando conectar a:");
  Serial.println(wifiID);

  WiFi.begin(wifiID,wifiPass);
  WiFi.mode(WIFI_STA); //Modo cliente wifi
  

  while (WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.print(".");
  }
  
  Serial.println("");
  Serial.println("Conexion exitosa!");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP()); //Mostramos la ip obtenida
}
 
void loop() {

}
