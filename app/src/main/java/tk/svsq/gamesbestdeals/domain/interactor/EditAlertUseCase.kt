package tk.svsq.gamesbestdeals.domain.interactor

import tk.svsq.gamesbestdeals.data.network.tools.exceptions.asRetrofitException
import tk.svsq.gamesbestdeals.domain.model.favorites.Alert
import tk.svsq.gamesbestdeals.domain.repository.GameRepository
import javax.inject.Inject

class EditAlertUseCase @Inject constructor(
    private val repository: GameRepository,
) : UseCase<EditAlertUseCase.Params, Boolean>() {

    data class Params(val item: Alert)

    override suspend fun run(): Result<Boolean> {
        if (params == null) throw IllegalArgumentException("Parameter required")
        return try {
            repository.editAlert(params!!.item)
        } catch (e: Exception) {
            Result.failure(asRetrofitException(e, null))
        }
    }

}