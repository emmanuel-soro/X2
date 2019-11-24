package ar.edu.soa.smartfarm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import ar.edu.soa.interfaces.RestService;
import ar.edu.soa.interfaces.ResultadoEstadisticas;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EstadisticasActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.17:8087")
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        this.loadInfoDataBase();
    }

    public void loadInfoDataBase(){
        new Thread(new Runnable() {
            private final Handler handler = new Handler() ;
            @Override
           public void run() {
                RestService restService = retrofit.create(RestService.class);
                Call<String> call = restService.getDatabase();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            ArrayList<ResultadoEstadisticas> arrayList = new ArrayList<>();
                            JSONArray jsonArray = new JSONArray(response.body().toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                ResultadoEstadisticas estadisticas = new ResultadoEstadisticas();
                                JSONObject dataobj = jsonArray.getJSONObject(i);
                                estadisticas.setNombre(dataobj.getString("nombre"));
                                estadisticas.setValor(dataobj.getString("valor"));
                                arrayList.add(estadisticas);
                            }

                            createChart(arrayList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                    }
                });
                handler.postDelayed(this, 5000);
           }
        }).start();
    }

    public void createChart(ArrayList<ResultadoEstadisticas> arrayList){
        BarChart chart = findViewById(R.id.barchart);
        ArrayList<BarEntry> chartEstadisticas = new ArrayList();
        ArrayList<String> labels = new ArrayList<String>();

        for (int i = 0; i < arrayList.size(); i++) {
            String x = arrayList.get(i).getNombre();
            String date = formatDate(x);
            labels.add("2016");
            labels.add("2017");
            labels.add("2018");
            Float value = Float.parseFloat(arrayList.get(i).getValor()) *100;
            chartEstadisticas.add(new BarEntry(i, value));
        }

        BarDataSet bardataset = new BarDataSet(chartEstadisticas, "Crecimiento");
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.MATERIAL_COLORS);

        chart.animateY(0);
        chart.setData(data);
        chart.setFitBars(true);
    }

    public String formatDate(String date){
        String año = date.substring(0,4);
        String mes = date.substring(4,6);
        String dia = date.substring(6,8);
        String hora = date.substring(8,10);
        String minutos = date.substring(10,12);
        String segundos = date.substring(12,14);

        return hora +   minutos + segundos;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
