package tk.svsq.gamesbestdeals.data.model

import com.google.gson.annotations.SerializedName

data class StoreImagesApiModel(
    @SerializedName("banner") val banner: String? = null,
    @SerializedName("logo") val logo: String? = null,
    @SerializedName("icon") val icon: String? = null
)
