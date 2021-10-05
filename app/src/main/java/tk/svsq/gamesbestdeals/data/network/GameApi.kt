package tk.svsq.gamesbestdeals.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tk.svsq.gamesbestdeals.data.model.StoreApiModel

interface GameApi {

    @GET("stores")
    suspend fun getStoresList(): Response<List<StoreApiModel>>

    @GET("alerts")
    suspend fun editAlert(
        @Query("action") action: String,
        @Query("email") email: String,
        @Query("gameID") gameID: Int,
        @Query("price") price: Float,
    ): Response<Boolean>

}