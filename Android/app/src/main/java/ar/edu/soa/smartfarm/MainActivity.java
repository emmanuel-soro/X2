package ar.edu.soa.smartfarm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton boton_tomar_foto;
    ImageButton boton_nosotros;

    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        boton_tomar_foto = (ImageButton) findViewById(R.id.btnTomarFoto);
        boton_nosotros = (ImageButton) findViewById(R.id.btnNosotros);

        boton_tomar_foto.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent fotoIntent = new Intent(MainActivity.this, TomarFotoActivity.class);
                startActivity(fotoIntent);
            }
        });

        boton_nosotros.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nosotrosIntent = new Intent(MainActivity.this, NosotrosActivity.class);
                startActivity(nosotrosIntent);
            }
        });

    }

    @Override
    protected void onStop() {
        Parar_Sensores();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Parar_Sensores();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Parar_Sensores();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Ini_Sensores();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Ini_Sensores();
    }

    // Metodo para iniciar el acceso a los sensores
    protected void Ini_Sensores() {
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Metodo para parar la escucha de los sensores
    private void Parar_Sensores() {
        sm.unregisterListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        sm.unregisterListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
        sm.unregisterListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_PROXIMITY));
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            switch(sensorEvent.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER :
                    checkAccelerometer(sensorEvent);
                break;
                case Sensor.TYPE_PROXIMITY :
                    checkProximity(sensorEvent);
                break;
            }
        }

        public void checkAccelerometer( SensorEvent sensorEvent){
            if (sensorEvent.values[0] > 12|| sensorEvent.values[1] > 12 || sensorEvent.values[2] > 12 ) {
                Intent nosotrosIntent = new Intent(MainActivity.this, NosotrosActivity.class);
                startActivity(nosotrosIntent);
            }
        }

        public void checkProximity( SensorEvent sensorEvent){
            if (sensorEvent.values[0] == 0) {
                Intent fotoIntent = new Intent(MainActivity.this, TomarFotoActivity.class);
                startActivity(fotoIntent);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
