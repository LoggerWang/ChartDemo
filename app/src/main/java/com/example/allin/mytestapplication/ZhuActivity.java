package com.example.allin.mytestapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.allin.mytestapplication.manager.BarChartManager;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import java.util.ArrayList;
import java.util.List;

/**
 * legend
 * 柱状图
 *
 */
public class ZhuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_zhu);
        BarChart barChart1 = findViewById(R.id.bar_chart1);
        BarChart barChart2 = findViewById(R.id.bar_chart2);


        BarChartManager barChartManager1 = new BarChartManager(barChart1);
        BarChartManager barChartManager2 = new BarChartManager(barChart2);

//        barChartManager2.getmBarChart().setBackgroundColor(Color.BLACK);
        barChart1.setBackgroundColor(Color.WHITE);
        barChart2.setBackgroundColor(Color.BLACK);
        //设置图表右下角描述
        Description description = new Description();
        description.setText("description描述文字");
        description.setTextColor(Color.RED);
        barChart2.setDescription(description);
        YAxis axisLeft = barChart2.getAxisLeft();
        axisLeft.setTextColor(Color.WHITE);

        YAxis axisRight = barChart2.getAxisRight();
        axisRight.setTextColor(Color.WHITE);

        XAxis xAxis = barChart2.getXAxis();
        xAxis.setTextColor(Color.WHITE);

        Legend legend = barChart2.getLegend();
        legend.setTextColor(Color.GRAY);

        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yValue.add((float) (Math.random() * 80));
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.MAGENTA);
        colours.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("唯医");
        names.add("医栈");
        names.add("医鼎");
        names.add("唯医骨科");

        //创建多条折线的图表
        barChartManager1.showBarChart(xValues, yValues.get(0), names.get(1), colours.get(2));
        barChartManager2.showBarChart(xValues, yValues, names, colours);
        barChartManager2.setXAxis(11f, 0f, 11);
    }
}
