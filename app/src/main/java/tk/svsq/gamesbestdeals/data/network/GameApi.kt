package tk.svsq.gamesbestdeals.data.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import tk.svsq.gamesbestdeals.data.model.StoreApiModel

interface GameApi {

    @GET("stores")
    suspend fun getStoresList(): Response<List<StoreApiModel>>

    @GET("alerts")
    suspend fun editAlert(): Response<Boolean>

}