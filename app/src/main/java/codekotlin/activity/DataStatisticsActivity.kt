package codekotlin.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import codekotlin.manager.LineChartManager
import com.example.allin.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.DecimalFormat
import java.util.ArrayList

/**
 * Author:wanglezhi
 * Date:2019/5/30-14:46
 * Descripitoin:
 */
class DataStatisticsActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_statistics)

        val mLineChartSingle = findViewById<View>(R.id.lineChareView) as LineChart
        val barChar = findViewById<View>(R.id.barChar) as HorizontalBarChart

//        setSingleLine(mLineChartSingle)
        setLineChartMultyData(mLineChartSingle)

        setStackedBar(barChar)
    }

    private fun setStackedBar(barChar: BarChart) {
        barChar.setDrawGridBackground(false)
        barChar.description.isEnabled = false
        barChar.xAxis.isEnabled =true
        barChar.axisRight.isEnabled = false

        // scaling can now only be done on x- and y-axis separately
        barChar.setPinchZoom(false)

        barChar.setDrawBarShadow(false)
        barChar.setDrawValueAboveBar(true)
        barChar.isHighlightFullBarEnabled = false

        barChar.axisLeft.isEnabled = false
        barChar.axisRight.axisMaximum = 25f
        barChar.axisRight.axisMinimum = -25f
        barChar.axisRight.setDrawGridLines(false)
        barChar.axisRight.setDrawZeroLine(true)
        barChar.axisRight.setLabelCount(7, false)
//        barChar.axisRight.valueFormatter = CustomFormatter()
        barChar.axisRight.textSize = 9f

        val xAxis = barChar.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.textSize = 9f
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = 110f
        xAxis.setCenterAxisLabels(true)
        xAxis.labelCount = 12
        xAxis.granularity = 10f
//        xAxis.setValueFormatter(object : ValueFormatter() {
//
//            private val format = DecimalFormat("###")
//
//            fun getFormattedValue(value: Float): String {
//                return format.format(value.toDouble()) + "-" + format.format((value + 10).toDouble())
//            }
//        })

        val l = barChar.legend
        l.form = Legend.LegendForm.CIRCLE
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.formSize = 8f
        l.formToTextSpace = 4f
        l.xEntrySpace = 6f

        // IMPORTANT: When using negative values in stacked bars, always make sure the negative values are in the array first
        val values = ArrayList<BarEntry>()
        values.add(BarEntry(5f, floatArrayOf(-10f, 10f)))
        values.add(BarEntry(15f, floatArrayOf(-12f, 13f)))
        values.add(BarEntry(25f, floatArrayOf(-15f, 15f)))
        values.add(BarEntry(35f, floatArrayOf(-17f, 17f)))
        values.add(BarEntry(45f, floatArrayOf(-19f, 20f)))
        values.add(BarEntry(55f, floatArrayOf(-19f, 19f)))
        values.add(BarEntry(65f, floatArrayOf(-16f, 16f)))
        values.add(BarEntry(75f, floatArrayOf(-13f, 14f)))
        values.add(BarEntry(85f, floatArrayOf(-10f, 11f)))
        values.add(BarEntry(95f, floatArrayOf(-5f, 6f)))
        values.add(BarEntry(105f, floatArrayOf(-1f, 2f)))

        val set = BarDataSet(values, "Age Distribution")
        set.setDrawIcons(false)
//        set.valueFormatter = CustomFormatter()
        set.valueTextSize = 7f
        set.axisDependency = YAxis.AxisDependency.RIGHT
        set.setColors(Color.rgb(67, 67, 72), Color.rgb(124, 181, 236))
        set.stackLabels = arrayOf("Men", "Women")

        val data = BarData(set)
        data.barWidth = 8.5f
        barChar.data = data
        barChar.invalidate()
    }

    private fun setSingleLine(mLineChartSingle: LineChart) {


        //获取示例
        val legend = mLineChartSingle.legend
        //是否隐藏示例
        legend.setEnabled(false);
        //设置示例文字颜色
        legend.textColor = Color.BLACK
        //设置示例图片类型
        legend.form = Legend.LegendForm.CIRCLE
        //设置示例垂直显示位置，默认BOTTOM, 还有居中CENTER,顶部TOP
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        //设置示例水平显示位置，默认LEFT, 还有居中CENTER,居右RIGHT
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        //设置示例展示方向默认水平HORIZONTAL
        legend.orientation = Legend.LegendOrientation.HORIZONTAL

        //显示边界线,默认不显示
        mLineChartSingle.setDrawBorders(false)
        //设置整个图表背景色
        //        mLineChartSingle.setBackgroundColor(Color.BLACK)
        //是否绘制网格背景
        mLineChartSingle.setDrawGridBackground(true)
        //设置网格背景色
        mLineChartSingle.setGridBackgroundColor(Color.TRANSPARENT)
        //设置X轴是否可缩放,默认可缩放
        //        mLineChartSingle.setScaleXEnabled(true);
        //设置Y轴是否可缩放,默认可缩放
        //        mLineChartSingle.setScaleYEnabled(true);
        //设置图表右下角描述
        //        val description = Description()
        //        description.text = "description描述文字"
        //        description.textColor = Color.WHITE
        //        mLineChartSingle.description = description


        //获取X轴
        val xAxis = mLineChartSingle.xAxis
        //设置X轴底部显示
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //设置X轴坐标之间的最小间隔
        xAxis.granularity = 1f
        //设置X轴的刻度数量,第二个参数表示是否平均分配
        xAxis.setLabelCount(12, true)
        //设置X轴字体颜色
        xAxis.textColor = Color.BLACK
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        //        xAxis.setAxisMinimum(0f);
        //        xAxis.setAxisMaximum(20f);
        val mList = ArrayList<String>()
        for (i in 0..11) {
            mList.add((i + 1).toString() + "月")
        }
        xAxis.valueFormatter = IAxisValueFormatter { value, axis -> mList[value.toInt()] }


        //获取右侧Y轴
        val axisRight = mLineChartSingle.axisRight
        //右侧Y轴是否显示
        axisRight.isEnabled = false
        axisRight.textColor = Color.BLUE //文字颜色
        axisRight.gridColor = Color.RED //网格线颜色
        axisRight.axisLineColor = Color.GREEN //Y轴颜色

        //获取左侧Y轴
        val axisLeft = mLineChartSingle.axisLeft
        //设置左侧Y轴的颜色
        axisLeft.textColor = Color.BLACK

        //限制线
        val limitLine = LimitLine(500f, "月度目标新增用户")
        limitLine.lineWidth = 4f //宽度
        limitLine.textSize = 10f
        limitLine.textColor = Color.RED  //颜色
        limitLine.lineColor = Color.BLUE
        //        axisRight.addLimitLine(limitLine); //Y轴添加限制线


        //设置数据
        val entries = ArrayList<Entry>()
        for (i in 0..11) {
            entries.add(Entry(i.toFloat(), Math.random().toFloat() * 1000))
        }
        //一个LineDataSet就是一条线
        val lineDataSet = LineDataSet(entries, "唯医年度新增用户数据统计2017")
        //设置曲线值的圆点是实心还是空心,true:是空心
        lineDataSet.setDrawCircleHole(true)
        //设置显示值的字体大小
        lineDataSet.valueTextSize = 9f
        //线模式为圆滑曲线（默认折线LINEAR）,CUBIC_BEZIER圆滑曲线，STEPPED垂直折线，HORIZONTAL_BEZIER也是圆滑曲线
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        //设置线的颜色,同时也设置了legend示例的图片颜色了
        lineDataSet.color = Color.BLUE
        //设置节点的颜色
        lineDataSet.setCircleColor(Color.YELLOW)
        //设置曲线节点空心的颜色
        lineDataSet.setCircleColorHole(Color.GREEN)
        //设置线上节点文字的颜色
        lineDataSet.valueTextColor = Color.RED
        val data = LineData(lineDataSet)
        mLineChartSingle.data = data
    }


    /**
     * 设置多线图
     * @param lineChartMulity
     */
    private fun setLineChartMultyData(lineChartMulity: LineChart) {
        val lineChartManager = LineChartManager(lineChartMulity)
        //List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours
        //设置x轴的数据
        val xValues = ArrayList<Float>()
        for (i in 0..16) {
            xValues.add(i.toFloat())
        }

        //设置y轴的数据()
        val yValues = ArrayList<List<Float>>()
        for (i in 0..2) {
            val yValue = ArrayList<Float>()
            for (j in 0..16) {
                if (j==5) {
                    yValue.add(300f)
                }else{
                    yValue.add((Math.random() * 1).toFloat())

                }
            }
            yValues.add(yValue)
        }
        //颜色集合
        val colours = ArrayList<Int>()
        colours.add(Color.parseColor("#6DA1F8"))
        colours.add(Color.parseColor("#FE9494"))
        colours.add(Color.parseColor("#92E9AC"))

        //线的名字集合
        val names = ArrayList<String>()
        names.add("入院")
        names.add("手术")
        names.add("出院")
        lineChartManager.showLineChart(xValues, yValues, names, colours)
    }


}