package codekotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import codekotlin.entity.ComplicationEntity
import codekotlin.utils.DensityUtil
import codekotlin.utils.ScreenUtils
import com.example.allin.R

/**
 * Author:wanglezhi
 * Date:2019/6/10-16:30
 * Descripitoin:
 */
class LegendTwoPartHorizontalBarView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    /**最大值 真正的值*/
    var maxValue = 0
    //屏幕宽度
    var screenWidth = 0
    //缩放参数
    var scaleParam = 1f
    //水平方向除了最大水平bar的间距：屏幕宽度-最大水平宽度
    var blankLength=0f
    var rlLegend:LinearLayout?=null
    var rlNameRoot:RelativeLayout?=null
    var llBarContainer:LinearLayout?=null
    init {
        View.inflate(context, R.layout.medrecord_view_legend_horizontal_bar, this)
        rlLegend = findViewById(R.id.rlLegend)
        rlNameRoot = findViewById(R.id.rlNameRoot)
        llBarContainer = findViewById(R.id.llBarContainer)
        screenWidth = ScreenUtils.getScreenWidth(context)
        //水平方向除了最大水平bar的间距：屏幕宽度-最大水平宽度(70是左边文字50+两边边距各10)
        blankLength = DensityUtil.dp2px(context, 70f)
    }

    /**
     * 是否显示图例
     */
    fun showLegend(show:Boolean){
        rlLegend?.visibility = if(!show){View.GONE}else{View.VISIBLE}
    }

    /**
     * 是否隐藏title
     * @param hide tue:隐藏 默认不隐藏
     */
    fun hideTitle(hide:Boolean){
        rlNameRoot?.visibility = if(hide){View.GONE}else{View.VISIBLE}
    }

    fun setData(dataList: MutableList<ComplicationEntity>?){
        if (dataList.isNullOrEmpty()) {
            //无数据
        }else{
            llBarContainer?.removeAllViews()
            //遍历数据 找到所有数据中的最大值
            dataList.forEach {
                if(it.middleNum>maxValue)maxValue = it.middleNum
                if(it.afterNum>maxValue)maxValue = it.afterNum
            }
            val maxBarLength = (screenWidth - blankLength)/2f
            //缩放参数 = 最大值用的屏幕宽度/最大值
            scaleParam = maxBarLength / (maxValue.toFloat())
             dataList.forEach {
                val itemView = View.inflate(context, R.layout.medrecord_item_two_part_bar_honrizatal, null)
                val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(0, DensityUtil.dp2px(context, 16f).toInt(), 0, 0)
                itemView.layoutParams = layoutParams
                val tvBarName = itemView.findViewById<View>(R.id.tvBarName) as TextView
                val rlBarLeft = itemView.findViewById<View>(R.id.rlBarLeft)
                val rlBaRight = itemView.findViewById<View>(R.id.rlBaright)
                val tvBarNumLeft = itemView.findViewById<View>(R.id.tvBarNumLeft) as TextView
                val tvBarNumRight = itemView.findViewById<View>(R.id.tvBarNumRight) as TextView
                //左侧Y轴文字
                tvBarName.text = it.complicationName
                tvBarNumLeft.text = it.middleNum.toString()
                tvBarNumRight.text = it.afterNum.toString()
                //其他bar用 值*缩放参数=屏幕长度
                rlBarLeft.layoutParams.width = (it.middleNum.toFloat() * scaleParam).toInt()
                rlBaRight.layoutParams.width = (it.afterNum.toFloat() * scaleParam).toInt()
                itemView.layoutParams.height = DensityUtil.dp2px(context, 34f).toInt()
                llBarContainer?.addView(itemView)
            }
        }
    }
}