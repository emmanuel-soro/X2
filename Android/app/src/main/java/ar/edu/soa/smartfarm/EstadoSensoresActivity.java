package ar.edu.soa.smartfarm;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ar.edu.soa.interfaces.RestService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EstadoSensoresActivity extends AppCompatActivity {

    Switch switchSensor, switchFollaje, switchReposo, switchTallo;
    String value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_sensores);

        switchSensor    = (Switch) findViewById(R.id.switchSensores);
        switchFollaje   = (Switch) findViewById(R.id.switchFollaje);
        switchReposo    = (Switch) findViewById(R.id.switchReposo);
        switchTallo     = (Switch) findViewById(R.id.switchTallo);

        // verifico el shake enviado desde el main activity
        String value2 = getIntent().getStringExtra("var");
        if(("on").equals(value2)){
            switchTallo.setChecked(true);
        }

        switchSensor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchSensor.setChecked(true);
                    switchFollaje.setChecked(false);
                    switchReposo.setChecked(false);
                    switchTallo.setChecked(false);
                    value = "W";
                    enviarPeticion(value);
                }
            }
        });

        switchFollaje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchSensor.setChecked(false);
                    switchFollaje.setChecked(true);
                    switchReposo.setChecked(false);
                    switchTallo.setChecked(false);
                    value = "F";
                    enviarPeticion(value);
                }
            }
        });

        switchReposo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchSensor.setChecked(false);
                    switchFollaje.setChecked(false);
                    switchReposo.setChecked(true);
                    switchTallo.setChecked(false);
                    value = "R";
                    enviarPeticion(value);
                }
            }
        });

        switchTallo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchSensor.setChecked(false);
                    switchFollaje.setChecked(false);
                    switchReposo.setChecked(false);
                    switchTallo.setChecked(true);
                    value = "T";
                    enviarPeticion(value);
                }
            }
        });
    }

    public void enviarPeticion(String value) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.30.164")
                .client(client)
                .build();

        RestService restService = retrofit.create(RestService.class);
        Call<ResponseBody> call = restService.getDato(value);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                 "OK", Toast.LENGTH_SHORT);

                toast1.show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                "ERROR", Toast.LENGTH_SHORT);

                toast2.show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
