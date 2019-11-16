package ar.edu.soa.smartfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class EstadisticasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        BarChart chart = findViewById(R.id.barchart);

        ArrayList<BarEntry> NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(0, 945f));
        NoOfEmp.add(new BarEntry(1, 1040f));
        NoOfEmp.add(new BarEntry(2, 1133f));
        NoOfEmp.add(new BarEntry(3, 1240f));
        NoOfEmp.add(new BarEntry(4, 1369f));
        NoOfEmp.add(new BarEntry(5, 1487f));
        NoOfEmp.add(new BarEntry(6, 1501f));
        NoOfEmp.add(new BarEntry(7, 1645f));
        NoOfEmp.add(new BarEntry(8, 1578f));
        NoOfEmp.add(new BarEntry(9, 1695f));

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Crecimiento");

        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.MATERIAL_COLORS);

        chart.animateY(5000);
        chart.setData(data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
