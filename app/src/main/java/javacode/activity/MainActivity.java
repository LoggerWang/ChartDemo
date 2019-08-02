package javacode.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.example.allin.R;

import codekotlin.activity.DataStatisticsActivity;
import codekotlin.activity.MyBarChartActivity;

/**
 * legend
 * 图标控件实例demo
 * 基于开源项目MPAndroidChart
 * GitHub地址：https://github.com/PhilJay/MPAndroidChart
 * 文档地址：https://jitpack.io/com/github/PhilJay/MPAndroidChart/v3.0.2/javadoc
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.activity_main);
    }

    public void goCircle(View view) {
        startActivity(new Intent(this,PieActivity.class));
    }

    public void goLineActivity(View view) {
        startActivity(new Intent(this,LineActivity.class));
    }

    public void goZhuActivity(View view) {
        startActivity(new Intent(this,ZhuActivity.class));
    }

    public void goSpider(View view) {
        startActivity(new Intent(this,SpiderAcitivity.class));
    }

    public void goDataStatistics(View view) {
        startActivity(new Intent(this,DataStatisticsActivity.class));
    }
    public void goMyBarChart(View view) {
        startActivity(new Intent(this,MyBarChartActivity.class));
    }
}
