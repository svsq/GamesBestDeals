package tk.svsq.gamesbestdeals.data.mapper

import tk.svsq.gamesbestdeals.data.mapper.base.Mapper
import tk.svsq.gamesbestdeals.data.model.GameApiModel
import tk.svsq.gamesbestdeals.domain.model.Game
import javax.inject.Inject

class GameMapper @Inject constructor() : Mapper<GameApiModel, Game>() {

    override fun map(from: GameApiModel) = with(from) {
        Game(
            gameID = gameID,
            steamAppID = steamAppID,
            cheapest = cheapest,
            cheapestDealID = cheapestDealID,
            external = external,
            internalName = internalName,
            thumb = thumb.orEmpty()
        )
    }

    override fun reverse(to: Game) = with(to) {
        GameApiModel(
            gameID = gameID,
            steamAppID = steamAppID,
            cheapest = cheapest,
            cheapestDealID = cheapestDealID,
            external = external,
            internalName = internalName,
            thumb = thumb
        )
    }

}