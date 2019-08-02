package codekotlin.entity

/**
 * 假体公司页面实体
 */
data class ProstheticCompanyAnalysis(
    //公司及数量集合
        var analysisBaseList: List<AnalysisBase>,
    //假体名称
        var analysisName: String
)