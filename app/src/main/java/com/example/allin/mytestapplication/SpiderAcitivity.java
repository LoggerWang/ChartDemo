package com.example.allin.mytestapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.allin.mytestapplication.manager.RadarChartManager;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;

import java.util.ArrayList;
import java.util.List;

public class SpiderAcitivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider);

        RadarChart mRadarChart = findViewById(R.id.radarchart);
        List<String> xData = new ArrayList<>();
        xData.add("主任医师");
        xData.add("副主任医师");
        xData.add("主治医师");
        xData.add("住院医师");
        xData.add("医学生");

        List<List<Float>> yData = new ArrayList<>();

        ArrayList<Float> yVals1 = new ArrayList<>();
        ArrayList<Float> yVals2 = new ArrayList<>();
        ArrayList<Float> yVals3 = new ArrayList<>();
        ArrayList<Float> yVals4 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            yVals1.add((float) ((Math.random() * 200) + 50));
            yVals2.add((float) (Math.random() * 200) + 40);
            yVals3.add((float) (Math.random() * 200) + 30);
            yVals4.add((float) (Math.random() * 200) + 60);
        }
        yData.add(yVals1);
        yData.add(yVals2);
        yData.add(yVals3);
        yData.add(yVals4);

        List<String> names = new ArrayList<>();
        names.add("视频");
        names.add("病例");
        names.add("会议");
        names.add("文库");
        names.add("其他");

        List<Integer> colors = new ArrayList<>();

        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);

        //设置图表右下角描述
        Description description = new Description();
        description.setText("唯医用户画像");
        description.setTextColor(Color.BLACK);
        mRadarChart.setDescription(description);

        RadarChartManager radarChartManager = new RadarChartManager(this, mRadarChart);
        radarChartManager.setYscale(240f, 0f, 6);
        radarChartManager.showRadarChart(xData, yData, names, colors);
    }
}
