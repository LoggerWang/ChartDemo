package codekotlin.entity
/**
 * 数据统计实体
 */
data class AnalysisBase(
    //名称
    var analysisName: String,
    //百分比
    var analysisPercent: String,
    //数量
    var analysisValue: String,
    //数量
    var analysisValueString: String
)