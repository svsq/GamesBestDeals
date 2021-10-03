package tk.svsq.gamesbestdeals.data.mapper

import tk.svsq.gamesbestdeals.data.mapper.base.Mapper
import tk.svsq.gamesbestdeals.data.model.DealApiModel
import tk.svsq.gamesbestdeals.data.model.StoreApiModel
import tk.svsq.gamesbestdeals.data.model.StoreImagesApiModel
import tk.svsq.gamesbestdeals.domain.model.Deal
import tk.svsq.gamesbestdeals.domain.model.Store
import tk.svsq.gamesbestdeals.domain.model.StoreImages
import javax.inject.Inject

class DealMapper @Inject constructor() : Mapper<DealApiModel, Deal>() {

    override fun map(from: DealApiModel) = with(from) {
        Deal(
            dealID = dealID ?: "",
            storeID = storeID ?: "",
            gameID = gameID ?: "",
            title = title ?: "",
            internalName = internalName ?: "",
            metacriticLink = metacriticLink ?: "",
            salePrice = salePrice ?: "",
            normalPrice = normalPrice ?: "",
            isOnSale = isOnSale == "1",
            savings = savings ?: "",
            metacriticScore = metacriticScore ?: "",
            steamRatingText = steamRatingText ?: "",
            steamRatingPercent = steamRatingPercent ?: "",
            steamRatingCount = steamRatingCount,
            steamAppID = steamAppID ?: "",
            releaseDate = releaseDate ?: -1,
            lastChange = lastChange ?: -1,
            thumb = thumb ?: "",
            dealRating = dealRating ?: ""
        )
    }

    override fun reverse(to: Deal) = with(to) {
        DealApiModel(
            dealID = dealID,
            storeID = storeID,
            gameID = gameID,
            title = title,
            internalName = internalName,
            metacriticLink = metacriticLink,
            salePrice = salePrice,
            normalPrice = normalPrice,
            isOnSale = if (isOnSale) "1" else "0",
            savings = savings,
            metacriticScore = metacriticScore,
            steamRatingText = steamRatingText,
            steamRatingPercent = steamRatingPercent,
            steamRatingCount = steamRatingCount,
            steamAppID = steamAppID,
            releaseDate = releaseDate,
            lastChange = lastChange,
            thumb = thumb,
            dealRating = dealRating
        )
    }
}