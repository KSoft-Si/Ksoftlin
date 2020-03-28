package si.ksoft.ksoftlin.data.dbl.vote.list

import com.google.gson.annotations.SerializedName
import si.ksoft.ksoftlin.data.dbl.vote.Vote

data class Vote(val expiry: String, val extras: MutableList<String>, @SerializedName("user") val id: Long, val isWeekend: Boolean)

data class ListVoteResponse(val mutableList: MutableList<Vote>)