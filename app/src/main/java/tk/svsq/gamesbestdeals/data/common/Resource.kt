package tk.svsq.gamesbestdeals.data.common

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {

    companion object {
        fun <T> loading(): Resource<T> {
            return Resource(
                Status.LOADING,
                null,
                null
            )
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable?): Resource<T> {
            return Resource(
                Status.ERROR,
                null,
                error
            )
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}