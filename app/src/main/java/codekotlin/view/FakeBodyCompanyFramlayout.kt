package codekotlin.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import codekotlin.entity.ProstheticCompanyAnalysis
import com.example.allin.R

/**
 * Author:wanglezhi
 * Date:2019/6/5-10:25
 * Descripitoin:假体统计-假体公司
 */
@SuppressLint("ViewConstructor")
class FakeBodyCompanyFramlayout(context: Context, val prostheticCompanyAnalysisList: List<ProstheticCompanyAnalysis>) : FrameLayout(context) {
    init {
        View.inflate(context, R.layout.medrecord_framelayout_fake_company,this)
        val llContainer = findViewById<View>(R.id.llContainer) as LinearLayout
        prostheticCompanyAnalysisList.forEach {
            llContainer.addView(LegendHorizontalBarView(context, it))
        }
        invalidate()
    }
}