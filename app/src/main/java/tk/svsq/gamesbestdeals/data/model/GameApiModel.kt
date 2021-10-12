package tk.svsq.gamesbestdeals.data.model

import com.google.gson.annotations.SerializedName

data class GameApiModel(
    @SerializedName("gameID") val gameID: String,
    @SerializedName("steamAppID") val steamAppID: String? = null,
    @SerializedName("cheapest") val cheapest: String,
    @SerializedName("cheapestDealID") val cheapestDealID: String,
    @SerializedName("external") val external: String,
    @SerializedName("internalName") val internalName: String,
    @SerializedName("thumb") val thumb: String? = null,
)
