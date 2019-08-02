package codekotlin.manager

import android.graphics.Color

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

import java.util.ArrayList

/**
 * 饼状图管理类
 */
class PieChartManager(internal var pieChart: PieChart) {

    init {
        initChart()
    }

    private fun initChart() {
        pieChart.holeRadius = 60f//半径    
        pieChart.transparentCircleRadius = 40f// 半透明圈    
        pieChart.setDrawCenterText(true)//饼状图中间可以添加文字    
        pieChart.isDrawHoleEnabled = true
        pieChart.rotationAngle = 90f// 初始旋转角度    
        pieChart.isRotationEnabled = false// 可以手动旋转    
        pieChart.setUsePercentValues(true)//显示成百分比  
        pieChart.rotationAngle = -90f
        val legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.xEntrySpace = 7f
        legend.yEntrySpace = 0f
        legend.yOffset = 0f

        pieChart.animateXY(1000, 1000)//设置动画  
    }

    /**
     * 设置饼状图
     *
     * @param name   饼状图分类的名字
     * @param date   数值
     * @param colors 颜色集合
     */
    fun setPieChart(name: List<String>, date: List<Float>, colors: List<Int>) {
        val yValue = ArrayList<PieEntry>()
        for (i in date.indices) {
            val entry = PieEntry(date[i], name[i])
            yValue.add(entry)
        }
        val set = PieDataSet(yValue, "")
        set.setDrawValues(true)
        set.valueTextSize = 12f
        set.colors = colors
        set.valueTextColor = Color.WHITE

        val data = PieData(set)
        pieChart.data = data
        pieChart.invalidate() // refresh
    }

    /**
     * 设实心饼状图
     *
     * @param name   饼状图分类的名字
     * @param date   数值
     * @param colors 颜色集合
     */
    fun setSolidPieChart(name: List<String>, date: List<Float>, colors: List<Int>) {

        pieChart.holeRadius = 0f//实心圆   
        pieChart.transparentCircleRadius = 0f// 半透明圈  
        pieChart.setDrawCenterText(false)//饼状图中间不可以添加文字  

        val yValue = ArrayList<PieEntry>()
        for (i in date.indices) {
            val entry = PieEntry(date[i], name[i])
            yValue.add(entry)
        }
        val set = PieDataSet(yValue, "")
        set.setDrawValues(true)
        set.valueTextSize = 12f
        set.colors = colors

        set.valueTextColor = Color.WHITE
        val data = PieData(set)
        pieChart.data = data
        pieChart.invalidate() // refresh
    }

    /**
     * 设置饼状图中间的描述内容
     *
     * @param str
     */
    fun setCenterDescription(str: String, color: Int) {
        pieChart.centerText = str
        pieChart.setCenterTextColor(color)
        pieChart.setCenterTextSize(12f)
        pieChart.invalidate()
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    fun setDescription(str: String) {
        val description = Description()
        description.text = str
        pieChart.description = description
        pieChart.invalidate()
    }
}
