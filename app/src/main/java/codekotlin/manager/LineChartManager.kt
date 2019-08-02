package codekotlin.manager

import android.graphics.Color
import android.graphics.DashPathEffect
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

import java.util.ArrayList
import android.graphics.Typeface
import codekotlin.XAxixYearMonthFormatter
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.highlight.ChartHighlighter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


/**
 * 线形图管理类
 */
class LineChartManager(private val lineChart: LineChart) {
    private val leftAxis: YAxis   //左边Y轴
    private val rightAxis: YAxis  //右边Y轴
    private val xAxis: XAxis      //X轴

    init {
        leftAxis = lineChart.axisLeft
        rightAxis = lineChart.axisRight
        xAxis = lineChart.xAxis
    }

    /**
     * 初始化LineChart
     */
    private fun initLineChart() {
        lineChart.setDrawGridBackground(false)
//        val rightAxis = lineChart.axisRight
//        rightAxis.isEnabled = false

        //右边Y轴
        val axisRight = lineChart.axisRight
        axisRight.isEnabled = false

//        axisRight.textColor = Color.BLUE //文字颜色
//        axisRight.gridColor = Color.RED //网格线颜色
//        axisRight.axisLineColor = Color.GREEN //Y轴颜色
        //是否显示整个图标外框框
        lineChart.setDrawBorders(false)
//        lineChart.setDrawGridBackground(true)
        //是否可拖拽
        lineChart.isDragEnabled = true
//        lineChart.setScaleEnabled(false)
//        lineChart.setPinchZoom(false)

        //是否绘制网格
        //GridLine：背景中每一列与X轴垂直的线
        //AxisLine : 与X轴重合的线

        val axis = lineChart.getAxis(YAxis.AxisDependency.LEFT)
        //和Y轴垂直网格线颜色
        axis.gridColor = Color.parseColor("#E6E6E6")
        axis.gridLineWidth = 1f
        //Y轴颜色
        axis.axisLineColor = Color.parseColor("#D8D8D8")
        axis.axisLineWidth = 1f
        axis.enableGridDashedLine(10f, 10f, 200f)
        axis.setLabelCount(5,true)
        axis.setDrawZeroLine(false)

//        axis.isEnabled = false
        //设置动画效果
//        setAnimate(1000, Easing.EasingOption.Linear,1000,Easing.EasingOption.Linear)

        //折线图例 标签 设置
        val legend = lineChart.legend
        //形状
        legend.formSize = 2f
        legend.form = Legend.LegendForm.CIRCLE
        legend.textSize = 12f
        legend.textColor = Color.parseColor("#333333")
        //显示位置
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.xEntrySpace = 24f
//        legend.yOffset = 100f

        //是否显示description
        lineChart.description.isEnabled = false

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //设置X轴坐标之间的最小间隔
        xAxis.granularity = 1f
//        xAxis.spaceMax = 150f
//        xAxis.setDrawAxisLine(false)
//        xAxis.axisMinimum = 0f
//        xAxis.granularity = 1f
//        xAxis.labelCount = 6


        setGridLine()

//        lineChart.`.isEnabled = false
        // 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleEnabled(true)
        //设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.isDoubleTapToZoomEnabled = false
        //X轴缩放比例
//        lineChart.scaleX = 1.5f
        //x轴缩放3倍，y轴缩放1倍
//        lineChart.viewPortHandler.matrixTouch.postScale(3f, 1f)
        //是否可以缩放 仅X轴
//        lineChart.isScaleXEnabled = true

//        xAxis.setLabelCount(6, true)
//        //是否绘制轴线
//        xAxis.setDrawAxisLine(true)
//        //设置x轴上每个点对应的线
//        xAxis.setDrawGridLines(false)
//        //是否绘制X轴上是数值
//        xAxis.setDrawLabels(true)
//        //X轴线的颜色
        xAxis.axisLineColor = Color.parseColor("#D8D8D8")
        xAxis.axisLineWidth = 1f
        //false时，滑动x轴的值跟着图表滑动，true时滑动，X轴的值不跟随滑动但数值跟随变化
        xAxis.setLabelCount(6,false)
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
//        xAxis.axisMinimum = 0f


        //将图表中最高值的顶部间距（占总轴范围的百分比）与轴上的最高值相比较。
        leftAxis.spaceMax = 30f
        //将图表中最低值的底部间距（占总轴范围的百分比）与轴上的最低值相比较。
        leftAxis.spaceMin = 0f
       //保证Y轴从0开始，不然会上移一点
        leftAxis.axisMinimum = 0f
//        rightAxis.axisMinimum = 0f
        //设置边距，这里设置右侧边距解决了X轴月份最后一个现实不全的问题
        lineChart.setExtraOffsets(0f,0f,35f,0f)

        lineChart.extraBottomOffset = 50f

    }

    /**
     * 设置 网格线
     */
    private fun setGridLine() {
        //设置和X轴垂直的线是否显示
        xAxis.setDrawGridLines(false)
        xAxis.gridColor = Color.RED
        //使用虚线组成的垂直网格线
        //参数：linelength：虚线长度
        // spacelength:虚线间隔长度
        // phase：虚线出发点（从第一根虚线的哪里出发）
        xAxis.enableGridDashedLine(40f, 20f, 20f)

    }

    private fun setAnimate(durationX:Int, animX: Easing.EasingOption,durationY:Int, animY: Easing.EasingOption) {
        lineChart.animateX(durationX, animX)
        lineChart.animateY(durationY, animY)
    }

    /**
     * 初始化曲线 每一个LineDataSet代表一条线
     *
     * @param lineDataSet
     * @param color
     * @param mode        折线图是否填充
     */
    private fun initLineDataSet(lineDataSet: LineDataSet, color: Int, mode: Boolean) {
        lineDataSet.color = color
        lineDataSet.setCircleColor(color)
        //设置线宽
        lineDataSet.lineWidth = 3f
        //设置圆心半径
        lineDataSet.circleRadius = 3f
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(true)
        lineDataSet.valueTextSize = 9f
        //设置折线图填充
        lineDataSet.setDrawFilled(mode)
        lineDataSet.formLineWidth = 1f
        lineDataSet.formSize = 15f
        //线模式为圆滑曲线（默认折线）
        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        lineDataSet.enableDashedHighlightLine(15f, 15f, 0f)//点击后的高亮线的显示样式
        //设置点击交点后显示高亮线宽
        lineDataSet.highlightLineWidth = 0.5f
        //是否禁用点击高亮线
        lineDataSet.isHighlightEnabled = true
        //点击时是否绘制水平提示线
        lineDataSet.setDrawHorizontalHighlightIndicator(false)
        //设置点击交点后显示交高亮线的颜色
        lineDataSet.highLightColor = Color.parseColor("#363F52")
        //设置显示值的文字大小
        lineDataSet.valueTextSize = 9f
        //设置禁用范围背景填充
        lineDataSet.setDrawFilled(false)
        //是否绘制曲线上值的点
        lineDataSet.setDrawCircles(false)

    }

    /**
     * 展示折线图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    fun showLineChart(xAxisValues: List<Float>, yAxisValues: List<Float>, label: String, color: Int) {
        initLineChart()
        val entries = ArrayList<Entry>()
        for (i in xAxisValues.indices) {
            entries.add(Entry(xAxisValues[i], yAxisValues[i]))
        }
        // 每一个LineDataSet代表一条线
        val lineDataSet = LineDataSet(entries, label)
        initLineDataSet(lineDataSet, color, true)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)
        val data = LineData(dataSets)
        //设置X轴的刻度数
        xAxis.setLabelCount(xAxisValues.size, true)
        lineChart.data = data
        lineChart.invalidate()
    }

    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param labels
     * @param colours
     */
    fun showLineChart(xAxisValues: List<Float>, yAxisValues: List<List<Float>>, labels: List<String>, colours: List<Int>) {
        initLineChart()
        var xMonths = arrayListOf<String>()
        xAxisValues.forEachIndexed { index, _ ->
            xMonths.add("19/06/$index")
        }
        xAxis.valueFormatter = XAxixYearMonthFormatter(xMonths)
//        xAxis.axisMinimum = 0f
        val dataSets = ArrayList<ILineDataSet>()
        for (i in yAxisValues.indices) {
            val entries = ArrayList<Entry>()
            var j = 0
            while (j < yAxisValues[i].size) {
                if (j >= xAxisValues.size) {
                    j = xAxisValues.size - 1
                }
                entries.add(Entry(xAxisValues[j], yAxisValues[i][j]))
                j++
            }
            val lineDataSet = LineDataSet(entries, labels[i])
            //是否绘制线上的数值
            lineDataSet.setDrawValues(true)
            initLineDataSet(lineDataSet, colours[i], false)
            dataSets.add(lineDataSet)
        }
        val lineData = LineData(dataSets)
        lineChart.data = lineData

        lineChart.setOnChartValueSelectedListener(object :OnChartValueSelectedListener{
            override fun onNothingSelected() {

            }

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                var yValues = arrayListOf<Float>()
                var data = lineChart.data
                var dataSets = data.dataSets as ArrayList<LineDataSet>
                dataSets.forEach {
                    var values = it.values
                    values.forEach { v->
                        if (v.x==e!!.x) {
                            yValues.add(v.y)
                        }
                    }
                }
                yValues

            }
        })

//        lineChart.setMaxVisibleValueCount(6)
//        lineChart.setVisibleXRangeMinimum(4f)
//        xAxis.axisMaximum =xAxisValues.size+0.1f
//        xAxis.mAxisMaximum = 10f
        lineChart.setVisibleXRange(0f,5.6f)
        lineChart.moveViewToX(xAxisValues.size-6f)
        xAxis.setCenterAxisLabels(false)

        //x轴缩放3倍，y轴缩放1倍
//        var scaleParam = xAxisValues.size / 7f
//        lineChart.viewPortHandler.matrixTouch.postScale(scaleParam, 1f)
//        lineChart.moveViewToX(5f)


        var visibleXRange = lineChart.visibleXRange
//        leftAxis.resetAxisMaximum()
//        leftAxis.resetAxisMinimum()
//        lineChart.setOnDragListener { v, event ->
//            xAxis.getheig
//        }
        //Y轴是否自动缩放
        lineChart.isAutoScaleMinMaxEnabled = true
        lineChart.invalidate()
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
        lineChart.invalidate()
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
        xAxis.setLabelCount(labelCount, true)
        lineChart.invalidate()
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
        hightLimit.lineWidth = 2f
        hightLimit.textSize = 10f
        hightLimit.lineColor = color
        hightLimit.textColor = color
        leftAxis.addLimitLine(hightLimit)
        lineChart.invalidate()
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
        lineChart.invalidate()
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    fun setDescription(str: String) {
        val description = Description()
        description.text = str
        lineChart.description = description
        lineChart.invalidate()
    }

    /** @描述 轴线的绘制
     * x轴：调用.getXAxis()获取
     * 左边y轴：调用.getAxisLeft()获取
     * 右边y轴：调用.getAxisRight()获取
     */
    private fun drawAxis(mAxis: AxisBase) {
        //设置是否启用轴线：如果关闭那么就默认没有轴线/标签/网格线
        mAxis.isEnabled = true
        //设置是否开启绘制轴的标签
        mAxis.setDrawLabels(true)
        //是否绘制轴线
        mAxis.setDrawAxisLine(true)
        //是否绘制网格线
        mAxis.setDrawGridLines(true)
    }

    /** @描述 自定义轴的范围 */
    private fun customizeRange(mAxis: YAxis) {
        //设置坐标轴最大值：如果设置那么轴不会根据传入数据自动设置
        mAxis.axisMaximum = 10f
        //重置已经设置的最大值，自动匹配最大值
        mAxis.resetAxisMaximum()
        //设置坐标轴最小值：如果设置那么轴不会根据传入数据自动设置
        mAxis.axisMinimum = 5f
        //重置已经设置的最小值，自动匹配最小值
        mAxis.resetAxisMinimum()
        //将图表中最高值的顶部间距（占总轴范围的百分比）与轴上的最高值相比较。
        mAxis.spaceMax = 10f
        //将图表中最低值的底部间距（占总轴范围的百分比）与轴上的最低值相比较。
        mAxis.spaceMin = 10f
        //设置标签个数以及是否精确（false为模糊，true为精确）
        mAxis.setLabelCount(20, false)
        //如果设置为true，此轴将被反转，这意味着最高值将在底部，最低的顶部值。
        mAxis.isInverted = true
        //设置轴标签应绘制的位置。无论是inside_chart或outside_chart。
        mAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        //如果设置为true那么下面方法设置最小间隔生效，默认为false
        mAxis.isGranularityEnabled = true
        //设置Y轴的值之间的最小间隔。这可以用来避免价值复制当放大到一个地步，小数设置轴不再数允许区分两轴线之间的值。
        mAxis.granularity = 10f
    }

    /** @描述 调整轴的造型 */
    private fun modifyingAxis(mAxis: AxisBase) {
        //设置坐标轴标签文字颜色
        mAxis.textColor = Color.GREEN
        //设置坐标轴标签文字大小
        mAxis.textSize = 10f
        //设置坐标轴标签文字样式
        mAxis.typeface = Typeface.DEFAULT_BOLD
        //设置此轴网格线颜色
        mAxis.gridColor = Color.RED
        //设置此轴网格线宽度
        mAxis.gridLineWidth = 0.5f
        //设置坐标轴的颜色
        mAxis.axisLineColor = Color.RED
        //设置坐标轴的宽度
        mAxis.axisLineWidth = 1f
        //使用虚线组成的网格线
        //参数：linelength：虚线长度
        // spacelength:虚线间隔长度
        // phase：虚线出发点（从第一根虚线的哪里出发）
        mAxis.enableGridDashedLine(40f, 2f, 20f)
    }
}
