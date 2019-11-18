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
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sm;
    ImageButton boton_tomar_foto;
    ImageButton boton_nosotros;
    ImageButton boton_sensores;
    ImageButton boton_estadisticas;
    String txt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        boton_tomar_foto = (ImageButton) findViewById(R.id.btnTomarFoto);
        boton_nosotros = (ImageButton) findViewById(R.id.btnNosotros);
        boton_sensores = (ImageButton) findViewById(R.id.btnEstadoSensores);
        boton_estadisticas = (ImageButton) findViewById(R.id.btnEstadisticas);

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

        boton_estadisticas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            Intent estadisticasIntent = new Intent(MainActivity.this, EstadisticasActivity.class);
            startActivity(estadisticasIntent);
            }
        });

        boton_sensores.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            Intent sensoresIntent = new Intent(MainActivity.this, EstadoSensoresActivity.class);
            startActivity(sensoresIntent);
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
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Metodo para parar la escucha de los sensores
    private void Parar_Sensores() {
        sm.unregisterListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            switch (sensorEvent.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    checkAccelerometer(sensorEvent);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    checkProximity(sensorEvent);
                    break;
                case Sensor.TYPE_LIGHT:
                    checkLight(sensorEvent);
                    break;
            }
        }

        public void checkAccelerometer(SensorEvent sensorEvent) {

            if (sensorEvent.values[0] > 12 || sensorEvent.values[1] > 12 || sensorEvent.values[2] > 12) {
                Intent nosotrosIntent = new Intent(MainActivity.this, NosotrosActivity.class);
                startActivity(nosotrosIntent);
            }
        }

        public void checkProximity(SensorEvent sensorEvent) {
            if (sensorEvent.values[0] == 0) {
                Intent fotoIntent = new Intent(MainActivity.this, TomarFotoActivity.class);
                startActivity(fotoIntent);
            }
        }
        public void checkLight(SensorEvent sensorEvent) {
            float[] values1 = sensorEvent.values;
            if ((Math.abs(values1[0]) > 1000)) {
                txt += "Luminosidad\n";
                txt += sensorEvent.values[0] + " Luz \n";
                Toast.makeText(getBaseContext(), txt, Toast.LENGTH_SHORT).show();
                Log.i("sensor", "TYPE_LIGHT running  \n");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
