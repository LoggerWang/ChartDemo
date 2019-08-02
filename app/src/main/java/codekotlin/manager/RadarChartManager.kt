package codekotlin.manager

import android.content.Context

import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet

import java.util.ArrayList

/**
 * 网状图管理类
 */

class RadarChartManager(private val mContext: Context, private val mRadarChart: RadarChart) {
    private val yAxis: YAxis
    private val xAxis: XAxis

    init {
        yAxis = mRadarChart.yAxis
        xAxis = mRadarChart.xAxis
    }

    private fun initRadarChart() {
        // 绘制线条宽度，圆形向外辐射的线条  
        mRadarChart.webLineWidth = 1.5f
        // 内部线条宽度，外面的环状线条  
        mRadarChart.webLineWidthInner = 1.0f
        // 所有线条WebLine透明度  
        mRadarChart.webAlpha = 100
        //点击点弹出的标签
        //        RadarMarkerView mv = new RadarMarkerView(mContext, R.layout.radar_markerview);
        //        mRadarChart.setMarkerView(mv);

        mRadarChart.isRotationEnabled = false

        val xAxis = mRadarChart.xAxis
        // X坐标值字体大小  
        xAxis.textSize = 12f
        // Y坐标值字体样式  
        // Y坐标值标签个数  
        yAxis.setLabelCount(6, false)
        // Y坐标值字体大小  
        yAxis.textSize = 15f
        // Y坐标值是否从0开始  
        yAxis.setStartAtZero(true)
        yAxis.isEnabled = false
        val l = mRadarChart.legend
        // 图例位置  
        l.position = Legend.LegendPosition.LEFT_OF_CHART
        // 图例X间距  
        l.xEntrySpace = 2f
        // 图例Y间距  
        l.yEntrySpace = 1f
    }

    fun showRadarChart(xData: List<String>, yDatas: List<List<Float>>, names: List<String>, colors: List<Int>) {
        initRadarChart()
        xAxis.valueFormatter = IAxisValueFormatter { value, axis -> xData[value.toInt() % xData.size] }

        val sets = ArrayList<IRadarDataSet>()
        for (i in yDatas.indices) {
            val yValues = ArrayList<RadarEntry>()
            for (j in 0 until yDatas[i].size) {
                yValues.add(RadarEntry(yDatas[i][j], j))
            }
            val radarDataSet = RadarDataSet(yValues, names[i])
            radarDataSet.color = colors[i]
            radarDataSet.setDrawFilled(true)
            // 数据线条宽度 
            radarDataSet.lineWidth = 2f
            sets.add(radarDataSet)
        }

        val data = RadarData(sets)
        // 数据字体大小  
        data.setValueTextSize(8f)
        // 是否绘制Y值到图表  
        data.setDrawValues(true)
        mRadarChart.data = data
        mRadarChart.invalidate()
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    fun setDescription(str: String) {
        val description = Description()
        description.text = str
        mRadarChart.description = description
        mRadarChart.invalidate()
    }

    fun setYscale(max: Float, min: Float, labelCount: Int) {
        yAxis.setLabelCount(labelCount, false)
        yAxis.axisMinimum = min
        yAxis.axisMaximum = max
        mRadarChart.invalidate()
    }
}
