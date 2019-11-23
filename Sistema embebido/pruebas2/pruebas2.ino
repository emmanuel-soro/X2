#include <ESP8266WiFi.h>

const char* ssid = "SO Avanzados";
const char* password = "SOA.2019";

/* Flags de tiempo */
unsigned long startMillis;
unsigned long currentMillis;

WiFiServer server(80);

/* Conexion del servidor  */
const char* ipServidor = "192.168.30.180";
const uint16_t puertoIpServidor = 8087;
WiFiClient client;

void setup() {
  Serial.begin(9600);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
 
  // Inicio del Servidor web.
  server.begin();
  //Serial.println("Servidor web iniciado.");
  // Esta es la IP
  Serial.print("Esta es la IP para conectar: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");

  startMillis = millis();
}


bool iniciarCliente()
{
  return client.connect(ipServidor, puertoIpServidor) ? true : false;
}
 
void loop() {
  
  if(WiFi.status() != WL_CONNECTED){
    WiFi.begin(ssid, password);
  }
  else{
        currentMillis = millis();
        if(currentMillis - startMillis < 300000){
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
           else if (iniciarCliente()) {        
              /* Sacar imagen */        
               Serial.println("Sacando Foteli");
               client.print(String("GET /photo")
               + " HTTP/1.1\r\n" +
               "Host: 192.168.30.180:8087" + "\r\n" +
               "Connection: keep-alive\r\n" +
               "\r\n"       
              );
               startMillis = millis();
           }
           else{
            Serial.println("Error al sacar foto.");
           }
        }
}
