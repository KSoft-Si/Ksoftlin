package si.ksoft.ksoftlin.data.dbl.vote

import com.google.gson.annotations.SerializedName
import si.ksoft.ksoftlin.data.dbl.vote.list.Vote


data class Vote(val expiry: String, val extras: MutableList<String>, val isWeekend: Boolean)

data class VoteResponse(@SerializedName("voted") val isVoted: Boolean, val data: Vote)