package tk.svsq.gamesbestdeals.data.network.tools.exceptions

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Exception uses in Retrofit error Throwable.
 * It may contains several Kinds of errors
 */
class RetrofitException(
        val mes: String?,
        val url: String?,
        val response: Response<*>?,
        val serverErrorType: ServerErrorType,
        val exception: Throwable?,
        val retrofit: Retrofit?
) : RuntimeException(mes, exception) {

    companion object {

        fun httpError(url: String, response: Response<*>?, retrofit: Retrofit?): RetrofitException {
            val message = "httpError at url=$url with code=\"${response?.code().toString()}\" and message=\"${response?.message()}\""
            return RetrofitException(
                    message,
                    url,
                    response,
                    ServerErrorType.HTTP,
                    null,
                    retrofit
            )
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(
                    exception.message,
                    null,
                    null,
                    ServerErrorType.NETWORK,
                    exception,
                    null
            )
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(
                    exception.message,
                    null,
                    null,
                    ServerErrorType.UNEXPECTED,
                    exception,
                    null
            )
        }

    }

    /**
     * HTTP response body converted to specified method. Null if there is no
     * response
     *
     * @param type - error data class
     * @return error data class
     * @throws IOException if unable to convert the body to the specified method
     */
    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        return response?.errorBody()?.let { errorBody ->
            retrofit?.let { retrofit ->
                val converter: Converter<ResponseBody, T> =
                        retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
                return converter.convert(errorBody)
            } ?: Gson().fromJson(errorBody.string(), type)
        }
    }

    enum class ServerErrorType {
        // An IOException occurred while communicating to the server
        NETWORK,

        // A non-200 HTTP status code was received from the server
        HTTP,

        // An internal error occurred while attempting to execute a request. It is best practice to
        // re-throw this exception so your application crashes.
        UNEXPECTED
    }
}

internal fun asRetrofitException(throwable: Throwable, retrofit: Retrofit?): RetrofitException {
    return when (throwable) {
        is HttpException -> {
            // We had non-200 http error
            val response = throwable.response()
            RetrofitException.httpError(
                response!!.raw().request.url.toString(),
                response,
                retrofit
            )
        }
        is SocketTimeoutException -> {
            RetrofitException.unexpectedError(throwable)
        }
        is IOException -> {
            // A network error happened
            RetrofitException.networkError(throwable)
        }
        else -> {
            // We don't know what happened. We need to simply convert to an unknown error
            RetrofitException.unexpectedError(throwable)
        }
    }
}