package javacode.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javacode.manager.PieChartManager;

import com.example.allin.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;

import java.util.ArrayList;
import java.util.List;

/**
 * legend
 * 扇形图
 *
 */
public class PieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        PieChart mPieChart1 = findViewById(R.id.piechart);


        //设置图表右下角描述
        Description description = new Description();
        description.setText("唯医用户各端百分比");
        description.setTextColor(Color.BLACK);
        mPieChart1.setDescription(description);


        List<String> names = new ArrayList<>(); //每个模块的内容描述
        names.add("唯医");
        names.add("医栈");
        names.add("医鼎");
        names.add("唯医骨科");
        List<Float> date = new ArrayList<>(); //每个模块的值（占比率）
        date.add(10.3f);
        date.add(20.2f);
        date.add(30.4f);
        date.add(39.1f);
        List<Integer> colors = new ArrayList<>(); //每个模块的颜色
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.DKGRAY);
        //饼状图管理类


        PieChartManager pieChartManager = new PieChartManager(mPieChart1);
        pieChartManager.setSolidPieChart(names, date, colors);
    }
}
