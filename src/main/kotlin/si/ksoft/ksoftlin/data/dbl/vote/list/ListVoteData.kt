package si.ksoft.ksoftlin.data.dbl.vote.list

import com.google.gson.annotations.SerializedName
import si.ksoft.ksoftlin.data.dbl.vote.Vote

data class Vote(
    val expiry: String,
    val extras: List<String>,
    @SerializedName("user") val id: Long,
    val isWeekend: Boolean
)

data class ListVoteResponse(val votes: List<Vote>)