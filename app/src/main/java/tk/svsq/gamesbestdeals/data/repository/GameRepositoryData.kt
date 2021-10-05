package tk.svsq.gamesbestdeals.data.repository

import okhttp3.Headers
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import tk.svsq.gamesbestdeals.data.mapper.DealMapper
import tk.svsq.gamesbestdeals.data.mapper.StoreMapper
import tk.svsq.gamesbestdeals.data.network.GameApi
import tk.svsq.gamesbestdeals.data.network.tools.exceptions.RetrofitException
import tk.svsq.gamesbestdeals.domain.model.Deal
import tk.svsq.gamesbestdeals.domain.model.Store
import tk.svsq.gamesbestdeals.domain.repository.GameRepository
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryData @Inject constructor (
    private val gameApi: GameApi,
    private val storeMapper: StoreMapper,
    private val dealMapper: DealMapper
) : GameRepository {

    override suspend fun getStoresList(): Result<List<Store>> {
        return request(
            response = gameApi.getStoresList(),
            transform = { storeMapper.map(it) },
            default = emptyList()
        )
    }

    override suspend fun getDealsList(storeId: String, upperPrice: String): Result<List<Deal>> {
        return request(
            response = gameApi.getDealsList(storeId, upperPrice),
            transform = { dealMapper.map(it) },
            default = emptyList()
        )
    }
}

internal fun <T, R> request(response: Response<T>, transform: (T) -> R, default: T): Result<R> =
    requestEx(response, { b, _ -> transform(b) }/*, {}*/, default)

class IOExceptionNoNetworkConnection : IOException("No Network Connection")

internal fun <T, R> requestEx(response: Response<T>, transform: (T, Headers) -> R, default: T): Result<R> {
    return try {
        when (response.isSuccessful) {
            true -> {
                Result.success(transform((response.body() ?: default), response.headers()))
            }
            false -> {
                Result.failure(
                    RetrofitException.httpError(
                        response.raw().request.url.toString(),
                        response,
                        null
                    )
                )
            }
        }
    } catch (exception: Throwable) {
        Result.failure(
            when (exception) {
                is HttpException -> {
                    // We had non-200 http error
                    exception.response().let { res ->
                        RetrofitException.httpError(
                            res?.raw()?.request?.url.toString(),
                            res,
                            null
                        )
                    }
                }
                is SocketTimeoutException -> {
                    RetrofitException.unexpectedError(exception)
                }
                is IOException -> {
                    // A network error happened
                    RetrofitException.networkError(exception)
                }
                else -> {
                    // We don't know what happened. We need to simply convert to an unknown error
                    RetrofitException.unexpectedError(exception)
                }
            }
        )
    }
}