package codekotlin

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.util.*


/**
 * Author:wanglezhi
 * Date:2019/6/13-17:53
 * Descripitoin:折线图X轴的显示文字转换
 */
class XAxixYearMonthFormatter(var months: ArrayList<String>) : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        if(value.toInt()>months.size-1)return ""
        return months[value.toInt()]
    }

}