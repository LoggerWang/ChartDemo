package codekotlin.manager

import android.graphics.Color

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

import java.util.ArrayList

/**
 * 柱形图管理类
 */
class BarChartManager(private val mBarChart: BarChart) {
    private val leftAxis: YAxis
    private val rightAxis: YAxis
    private val xAxis: XAxis

    init {
        leftAxis = mBarChart.axisLeft
        rightAxis = mBarChart.axisRight
        xAxis = mBarChart.xAxis
        initLineChart()
    }

    fun getmBarChart(): BarChart {
        return mBarChart
    }

    /**
     * 初始化LineChart
     */
    private fun initLineChart() {
        //背景颜色
        //        mBarChart.setBackgroundColor(Color.WHITE);
        //是否绘制网格背景
        mBarChart.setDrawGridBackground(false)
        //设置网格背景色
        mBarChart.setGridBackgroundColor(Color.TRANSPARENT)
        //柱形图背景阴影
        mBarChart.setDrawBarShadow(false)
        mBarChart.isHighlightFullBarEnabled = false

        //显示边界
        mBarChart.setDrawBorders(true)
        //设置动画效果
        //        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        //        mBarChart.animateX(1000, Easing.EasingOption.Linear);

        //折线图例 标签 设置
        val legend = mBarChart.legend
        legend.form = Legend.LegendForm.LINE
        legend.textSize = 11f
        //显示位置
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        //保证Y轴从0开始，不然会上移一点
        leftAxis.axisMinimum = 0f
        rightAxis.axisMinimum = 0f
    }

    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    fun showBarChart(xAxisValues: List<Float>, yAxisValues: List<Float>, label: String, color: Int) {
        //        initLineChart();
        val entries = ArrayList<BarEntry>()
        for (i in xAxisValues.indices) {
            entries.add(BarEntry(xAxisValues[i], yAxisValues[i]))
        }
        // 每一个BarDataSet代表一类柱状图
        val barDataSet = BarDataSet(entries, label)

        barDataSet.color = color
        barDataSet.valueTextSize = 9f
        barDataSet.formLineWidth = 1f
        barDataSet.formSize = 15f

        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(barDataSet)
        val data = BarData(dataSets)
        //设置X轴的刻度数
        xAxis.setLabelCount(xAxisValues.size - 1, false)
        mBarChart.data = data
    }

    /**
     * 展示柱状图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    fun showBarChart(xAxisValues: List<Float>, yAxisValues: List<List<Float>>, labels: List<String>, colours: List<Int>) {
        initLineChart()
        val data = BarData()
        for (i in yAxisValues.indices) {
            val entries = ArrayList<BarEntry>()
            for (j in 0 until yAxisValues[i].size) {

                entries.add(BarEntry(xAxisValues[j], yAxisValues[i][j]))
            }
            val barDataSet = BarDataSet(entries, labels[i])

            barDataSet.color = colours[i]
            barDataSet.valueTextColor = colours[i]
            barDataSet.valueTextSize = 10f
            barDataSet.axisDependency = YAxis.AxisDependency.LEFT
            data.addDataSet(barDataSet)
        }

        val amount = yAxisValues.size //需要显示柱状图的类别 数量
        val groupSpace = 0.12f //柱状图组之间的间距
        val barSpace = ((1 - 0.12) / amount.toDouble() / 10.0).toFloat() // x4 DataSet
        val barWidth = ((1 - 0.12) / amount.toDouble() / 10.0 * 9).toFloat() // x4 DataSet

        //        float groupSpace = 0.12f; //柱状图组之间的间距
        //        float barSpace =  0.02f; // x4 DataSet
        //        float barWidth = 0.2f; // x4 DataSet
        // (0.2 + 0.02) * 4 + 0.12 = 1.00 即100% 按照百分百布局
        xAxis.setLabelCount(amount - 1, false)
        //柱状图宽度
        data.barWidth = barWidth
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace)

        mBarChart.data = data
    }


    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    fun setYAxis(max: Float, min: Float, labelCount: Int) {
        if (max < min) {
            return
        }
        leftAxis.axisMaximum = max
        leftAxis.axisMinimum = min
        leftAxis.setLabelCount(labelCount, false)

        rightAxis.axisMaximum = max
        rightAxis.axisMinimum = min
        rightAxis.setLabelCount(labelCount, false)
        mBarChart.invalidate()
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    fun setXAxis(max: Float, min: Float, labelCount: Int) {
        xAxis.axisMaximum = max
        xAxis.axisMinimum = min
        xAxis.setLabelCount(labelCount, false)

        mBarChart.invalidate()
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    fun setHightLimitLine(high: Float, name: String?, color: Int) {
        var name = name
        if (name == null) {
            name = "高限制线"
        }
        val hightLimit = LimitLine(high, name)
        hightLimit.lineWidth = 4f
        hightLimit.textSize = 10f
        hightLimit.lineColor = color
        hightLimit.textColor = color
        leftAxis.addLimitLine(hightLimit)
        mBarChart.invalidate()
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    fun setLowLimitLine(low: Int, name: String?) {
        var name = name
        if (name == null) {
            name = "低限制线"
        }
        val hightLimit = LimitLine(low.toFloat(), name)
        hightLimit.lineWidth = 4f
        hightLimit.textSize = 10f
        leftAxis.addLimitLine(hightLimit)
        mBarChart.invalidate()
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    fun setDescription(str: String) {
        val description = Description()
        description.text = str
        mBarChart.description = description
        mBarChart.invalidate()
    }
}
