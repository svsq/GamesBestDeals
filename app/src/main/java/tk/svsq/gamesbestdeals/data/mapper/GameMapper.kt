package tk.svsq.gamesbestdeals.data.mapper

import tk.svsq.gamesbestdeals.data.mapper.base.Mapper
import tk.svsq.gamesbestdeals.data.model.GameApiModel
import tk.svsq.gamesbestdeals.domain.model.game.Game
import javax.inject.Inject

class GameMapper @Inject constructor() : Mapper<GameApiModel, Game>() {

    override fun map(from: GameApiModel) = with(from) {
        Game(
            gameID = gameID.orEmpty(),
            steamAppID = steamAppID.orEmpty(),
            cheapest = cheapest.orEmpty(),
            cheapestDealID = cheapestDealID.orEmpty(),
            external = external.orEmpty(),
            internalName = internalName.orEmpty(),
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