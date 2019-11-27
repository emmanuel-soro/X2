package ar.edu.soa.smartfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ar.edu.soa.interfaces.FormatoEjeX;
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
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8087")
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        this.loadInfoDataBase();
    }

    public void loadInfoDataBase() {
//        new Thread(new Runnable() {
//            private final Handler handler = new Handler();
//
//            @Override
//            public void run() {
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
//                handler.postDelayed(this, 5000);
//            }
//        }).start();
    }

    public void createChart(ArrayList<ResultadoEstadisticas> estadisticas) {

        BarChart chart = findViewById(R.id.barchart);
        List<BarEntry> chartEstadisticas = new ArrayList();

        Long fechaInicial = Long.valueOf(estadisticas.get(0).getNombre());

        for (int i = 0; i < estadisticas.size(); i++) {
            String x = estadisticas.get(i).getNombre();

            Float fecha = Float.valueOf(estadisticas.get(i).getNombre());
            Float value = Float.valueOf(estadisticas.get(i).getValor());

            System.out.println("Fecha: " + (fecha - fechaInicial));
            System.out.println("Valor: " + (value * 1f));
            chartEstadisticas.add(new BarEntry(fecha - fechaInicial, value * 1f));
        }
        System.out.println();


        BarDataSet bardataset = new BarDataSet(chartEstadisticas, "Crecimiento");
        bardataset.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData data = new BarData(bardataset);
        data.setBarWidth(0.9f);

        chart.setData(data);
        chart.setFitBars(true);
        chart.animateXY(3000, 3000);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new FormatoEjeX(fechaInicial));
        xAxis.setLabelCount(chartEstadisticas.size());

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setValueFormatter(new PercentFormatter());
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
