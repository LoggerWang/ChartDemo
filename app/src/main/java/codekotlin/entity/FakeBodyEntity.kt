package codekotlin.entity
/**
 * 假体统计
 */
data class FakeBodyEntity(
    //假体公司
        val prostheticCompanyAnalysisList: List<ProstheticCompanyAnalysis>?,
    //假体刷领
        val prostheticNumAnalysis: ProstheticNumAnalysis?
)