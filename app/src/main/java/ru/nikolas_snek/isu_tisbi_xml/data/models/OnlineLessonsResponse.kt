import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class OnlineLessonsResponse(
    @Json(name = "time") val time: String,
    @Json(name = "list") val list: List<Task>
)

@JsonClass(generateAdapter = true)
data class Task(
    @Json(name = "taskId") val taskId: Int,
    @Json(name = "number") val number: Int,
    @Json(name = "startDate") val startDate: String,
    @Json(name = "postponedDate") val postponedDate: String?, // Может быть null
    @Json(name = "endDate") val endDate: String,
    @Json(name = "name") val name: String,
    @Json(name = "objectName") val objectName: String,
    @Json(name = "statusTypeId") val statusTypeId: Int,
    @Json(name = "entityTypeId") val entityTypeId: Int,
    @Json(name = "objectId") val objectId: Int,
    @Json(name = "sendRolePeopleName") val sendRolePeopleName: String,
    @Json(name = "dateCreate") val dateCreate: String,
    @Json(name = "isRemark") val isRemark: Int,
    @Json(name = "taskRemarkId") val taskRemarkId: Int?, // Может быть null
    @Json(name = "ruleId") val ruleId: Int,
    @Json(name = "ruleName") val ruleName: String,
    @Json(name = "taskStartDate") val taskStartDate: String,
    @Json(name = "ruleStatus") val ruleStatus: Int,
    @Json(name = "categoryId") val categoryId: Int,
    @Json(name = "categoryName") val categoryName: String,
    @Json(name = "taskTypeId") val taskTypeId: Int?, // Может быть null
    @Json(name = "readDate") val readDate: String?, // Может быть null
    @Json(name = "isObserver") val isObserver: Int,
    @Json(name = "perfEntityTypeId") val perfEntityTypeId: Int,
    @Json(name = "perfObjectId") val perfObjectId: Int,
    @Json(name = "perfObjectName") val perfObjectName: String
)

