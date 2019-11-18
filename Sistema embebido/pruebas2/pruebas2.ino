#include <ESP8266WiFi.h>
const char* ssid = "Speedy-Fibra-F58CFB";
const char* password = "2b8b4d39D6FaDd5a2Y97";

WiFiServer server(80);
 
void setup() {
  Serial.begin(9600);
  delay(10);
 
  // Conecta a la red wifi.
  //Serial.println();
  //Serial.println();
  //Serial.print("Conectando con ");
  //Serial.println(ssid);
 
  WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  //Serial.println("");
  //Serial.println("Conectado con WiFi.");
 
  // Inicio del Servidor web.
  server.begin();
  //Serial.println("Servidor web iniciado.");
 
  // Esta es la IP
  //Serial.print("Esta es la IP para conectar: ");
  //Serial.print("http://");
  //Serial.print(WiFi.localIP());
  //Serial.println("/");
}
 
void loop() {

  while(WiFi.status() != WL_CONNECTED){
    WiFi.begin(ssid, password);
    delay(500);
    Serial.print(".");
  }
  
  // Consulta si se ha conectado algún cliente.
  WiFiClient client = server.available();
  if (!client) {
    return;
  }
 
  // Espera hasta que el cliente envíe datos.
  //Serial.println("Nuevo cliente.");
  while(!client.available()){
   
    delay(1);
  }
  /////////////////////////////////////////////////////
  // Lee la información enviada por el cliente.
  String req = client.readStringUntil('\r');
  //Serial.println(req);
  client.flush();

  // Consulta la petición del cliente.
  String val;
  if (req.indexOf("/dato?id=F") != -1){
  val = "Estado Follaje!";
  Serial.print('F');
  }
  else if (req.indexOf("/dato?id=R") != -1){
  val = "Estado Reposo!";
  Serial.print('R');
  }
  else if (req.indexOf("/dato?id=T") != -1){
  val = "Estado Tallo!";
  Serial.print('T');
  }
  else if(req.indexOf("/dato?id=W") != -1){
  val = "Estado Sensores!";
  Serial.print('W');
  }
  else{
  //Serial.println("Error.");
  client.stop();
  return;
  }
  //Serial.println(val);
  delay(10);

  //////////////////////////////////////////////
  //   Página WEB. ////////////////////////////
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); //  Comillas importantes.
  client.println(val);
  //Serial.println("Cliente desconectado.");
}
