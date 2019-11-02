
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


/* Bibliotecas */
#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

/* Conexion de red  */
const char* wifiID = "Speedy-Fibra-F58CFB";
const char* wifiPass= "2b8b4d39D6FaDd5a2Y97";

/* Conexion del servidor  */
const char* ipServidor = "192.168.1.35";
const uint16_t puertoIpServidor = 8087;
WiFiClient client;
SoftwareSerial SerialEsp(3,2); // RX y TX
char data;

/* Modo de ejecucion */
int timeoutConexion = 10;

void setup() {
  Serial.begin(115200);
  SerialEsp.begin(9600);
  Serial.println("**** Bienvenido: Smart Farm ****");
  delay(1000);  
}
 
void loop() {
  if(conectar()){
     Serial.println("");
     Serial.println("Wifi ok!");
     Serial.println("IP address: ");
     Serial.println(WiFi.localIP());
     Serial.println("------------------");
     delay(300);
     Serial.print("Inicializando Cliente: ");


     verMensajeArduino();
     
     if (iniciarCliente()) {        
       /* Sacar imagen */        
       client.print(String("GET /photo")
       + " HTTP/1.1\r\n" +
       "Host: 192.168.1.47:8087" + "\r\n" +
       "Connection: keep-alive\r\n" +
       "\r\n"       
      );
      Serial.println("Todo bien, se saco la imagen");
    }else{
      Serial.println("Error: No se saco la imagen");
    }
  }else{
   Serial.println("Error: No se pudo conecetar al Wifi");
   client.stop();
  }
  delay(10000);   
}

bool conectar(){
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

bool iniciarCliente()
{
  return client.connect(ipServidor, puertoIpServidor) ? true : false;
}

void verMensajeArduino(){
  if(SerialEsp.available() > 0){
    Serial.println("Recibido: ");
    while(SerialEsp.available() > 0){
      data = SerialEsp.read();  
    }
    Serial.print(data);
  }
}
