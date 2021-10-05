package tk.svsq.gamesbestdeals.data.mapper

import tk.svsq.gamesbestdeals.data.mapper.base.Mapper
import tk.svsq.gamesbestdeals.data.model.StoreApiModel
import tk.svsq.gamesbestdeals.data.model.StoreImagesApiModel
import tk.svsq.gamesbestdeals.domain.model.Store
import tk.svsq.gamesbestdeals.domain.model.StoreImages
import javax.inject.Inject

class StoreMapper @Inject constructor() : Mapper<StoreApiModel, Store>() {

    override fun map(from: StoreApiModel) = with(from) {
        Store(
            storeID = storeID,
            storeName = storeName,
            isActive = isActive == 1,
            images = StoreImages(banner = images?.banner, logo = images?.logo, icon = images?.icon)
        )
    }

    override fun reverse(to: Store) = with(to) {
        StoreApiModel(
            storeID = storeID,
            storeName = storeName,
            isActive = if (isActive) 1 else 0,
            images = StoreImagesApiModel(banner = images?.banner, logo = images?.logo, icon = images?.icon)
        )
    }
}