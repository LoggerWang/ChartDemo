package codekotlin.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import codekotlin.entity.ProstheticCompanyAnalysis
import codekotlin.utils.DensityUtil
import codekotlin.utils.ScreenUtils
import com.example.allin.R

/**
 * Author:wanglezhi
 * Date:2019/6/5-14:18
 * Descripitoin: legend本人自定义的水平bar图表统计
 */
@SuppressLint("ViewConstructor")
class LegendHorizontalBarView(context: Context, val data: ProstheticCompanyAnalysis)
    : FrameLayout(context) {
    var tvHorizontalBarName:TextView ?= null
    var itemContainer:LinearLayout ?= null
    //缩放参数
    var scaleParam = 1f
    //屏幕宽度
    var screenWidth = 0
    //水平方向除了最大水平bar的间距：屏幕宽度-最大水平宽度
    var blankLength=0f
    //每个item的marginTop[
    var marginTop = 0f
    //item的高度
    var height = 100f
    init {
        screenWidth = ScreenUtils.getScreenWidth(context)
        //水平方向除了最大水平bar的间距：屏幕宽度-最大水平宽度(70是左边文字50+两边边距各10)
         blankLength = DensityUtil.dp2px(context, 70f)
        //每个item的marginTop[
        marginTop = DensityUtil.dp2px(context, 16f)
        //item的高度
        height = DensityUtil.dp2px(context, 34f)
        val inflate = View.inflate(context, R.layout.medrecord_view_legend_horizontal_bar, this)

         itemContainer = findViewById<View>(R.id.llBarContainer) as LinearLayout
        tvHorizontalBarName = findViewById<View>(R.id.tvHorizontalBarName) as TextView
        setName(data.analysisName)
        ViewTreeObserver.OnGlobalLayoutListener {  }
        if (!data.analysisBaseList.isNullOrEmpty()) {
            //每一个假体床架一个item，并添加到容器中
            setView()
        }
    }

    private fun setView() {
        data.analysisBaseList.forEachIndexed { index, analysisBase ->
            val itemView = View.inflate(context, R.layout.medrecord_item_bar_honrizatal, null)
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0, marginTop.toInt(), 0, 0)
            itemView.layoutParams = layoutParams
            //左侧名称
            val tvBarName = itemView.findViewById<View>(R.id.tvBarName) as TextView
            //整体色条
            val rlBar = itemView.findViewById<View>(R.id.rlBar) as RelativeLayout
            //数字
            val tvBarNum = itemView.findViewById<View>(R.id.tvBarNum) as TextView
            tvBarName.text = analysisBase.analysisName
            tvBarNum.text = analysisBase.analysisValueString
            if (index == 0) {
                val maxBarLength = screenWidth - blankLength
                rlBar.layoutParams.width = maxBarLength.toInt()
                //缩放参数 = 最大值用的屏幕宽度/最大值
                scaleParam = maxBarLength / (analysisBase.analysisValue.toFloat())
            } else {
                //其他bar用 值*缩放参数=屏幕长度
                rlBar.layoutParams.width = (analysisBase.analysisValue.toFloat() * scaleParam).toInt()
            }
            rlBar.layoutParams.height = height.toInt()
//            rlBar.setBackgroundResource(R.drawable.medrecord_bg_bar_select_6da1f8)
            itemContainer?.addView(itemView)
        }
    }

    /**
     * 设置图表名称
     */
    fun setName(barName: String) {
        tvHorizontalBarName?.text = data.analysisName
    }
}