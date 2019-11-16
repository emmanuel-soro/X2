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

    private float acelVal;
    private float acelLast;
    private float shake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

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

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = acelVal - acelLast;
            shake = shake * 0.9f + delta;

            if(shake > 12 ) {
                Intent nosotrosIntent = new Intent(MainActivity.this, NosotrosActivity.class);
                startActivity(nosotrosIntent);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
