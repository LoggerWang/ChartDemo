package codekotlin.entity

/**
 * 假体数量页面实体
 */
data class ProstheticNumAnalysis(
        val analysisDateTime: String,
    //假体及数量
        val prostheticAnalysisList: List<ProstheticAnalysis>,
    //假体总数
        val prostheticTotalNum: String
)