package si.ksoft.ksoftlin.endpoints

import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import si.ksoft.ksoftlin.builder.client
import si.ksoft.ksoftlin.data.dbl.vote.VoteResponse
import si.ksoft.ksoftlin.data.dbl.vote.list.ListVoteResponse
import si.ksoft.ksoftlin.util.api

/**
 * The class containing HTTP Calls to the KSoft.Si DBL (Discord Bot List) Endpoint.
 *
 * @since 2.0
 * @author chachy
 */


class DiscordBotListEndpoint(private val token: String) {
    suspend fun isBotVotedByUser(botId: Long, userId: Long) = client.get<VoteResponse>("$api/webhook/dbl/check") {
        header("Authorization", token)
        parameter("bot", botId)
        parameter("user", userId)
    }

    suspend fun getListOfVotes(botId: Long) = client.get<ListVoteResponse>("$api/webhook/dbl/list") {
        header("Authorization", token)
        parameter("bot", botId)
    }
}
