package codekotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import codekotlin.entity.AnalysisBase
import codekotlin.entity.ComplicationEntity
import codekotlin.entity.ProstheticCompanyAnalysis
import codekotlin.view.FakeBodyCompanyFramlayout
import codekotlin.view.LegendTwoPartHorizontalBarView
import com.example.allin.R

/**
 * Author:wanglezhi
 * Date:2019/7/10-14:47
 * Descripitoin:
 */
class MyBarChartActivity : AppCompatActivity() {

    var llRootView: LinearLayout?=null
    var fakeBodyCompanyFramlayout:FakeBodyCompanyFramlayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivy_mybarchart)
        llRootView = findViewById(R.id.llRootView)
        setFakeData()
    }

    private fun setFakeData() {
        var datas = arrayListOf<ComplicationEntity>()

        var complicationEntity = ComplicationEntity(10,23,"AAA")
        var complicationEntity2 = ComplicationEntity(20,53,"BBB")
        var complicationEntity3 = ComplicationEntity(32,56,"CCC")
        var complicationEntity4 = ComplicationEntity(11,32,"DDD")
        datas.add(complicationEntity)
        datas.add(complicationEntity2)
        datas.add(complicationEntity3)
        datas.add(complicationEntity4)
        setComplicationData(datas)


        var prostheticCompanyAnalysisList = arrayListOf<ProstheticCompanyAnalysis>()

        var arrayListOf = arrayListOf<AnalysisBase>()
        var analysisBase = AnalysisBase("山","120","123","123")
        var analysisBase1 = AnalysisBase("山1","90","93","93")
        var analysisBase2 = AnalysisBase("山2","30","73","73")
        var analysisBase3 = AnalysisBase("山3","40","23","23")
        arrayListOf.add(analysisBase)
        arrayListOf.add(analysisBase1)
        arrayListOf.add(analysisBase2)
        arrayListOf.add(analysisBase3)
        var prostheticCompanyAnalysis = ProstheticCompanyAnalysis(arrayListOf,"名称一")



        var arrayListOf22 = arrayListOf<AnalysisBase>()
        var analysisBase22 = AnalysisBase("猛虎","33","244","244")
        var analysisBase122 = AnalysisBase("猛虎1","23","155","155")
        var analysisBase222 = AnalysisBase("猛虎2","23","66","66")
        var analysisBase322 = AnalysisBase("猛虎3","43","27","27")
        arrayListOf22.add(analysisBase22)
        arrayListOf22.add(analysisBase122)
        arrayListOf22.add(analysisBase222)
        arrayListOf22.add(analysisBase322)
        var prostheticCompanyAnalysis2 = ProstheticCompanyAnalysis(arrayListOf22,"名称二")



        prostheticCompanyAnalysisList.add(prostheticCompanyAnalysis)
        prostheticCompanyAnalysisList.add(prostheticCompanyAnalysis2)


        fakeBodyCompanyFramlayout = FakeBodyCompanyFramlayout(this, prostheticCompanyAnalysisList as ArrayList<ProstheticCompanyAnalysis>)
        llRootView?.addView(fakeBodyCompanyFramlayout)
    }



    /**
     * 设置并发症统计数据
     */
    private fun setComplicationData(data: MutableList<ComplicationEntity>?) {
        llRootView?.removeAllViews()
        val legendTwoPartHorizontalBarView = LegendTwoPartHorizontalBarView(this)
        legendTwoPartHorizontalBarView.showLegend(true)
        legendTwoPartHorizontalBarView.hideTitle(true)
        legendTwoPartHorizontalBarView.setData(data)
        llRootView?.addView(legendTwoPartHorizontalBarView)
    }
}