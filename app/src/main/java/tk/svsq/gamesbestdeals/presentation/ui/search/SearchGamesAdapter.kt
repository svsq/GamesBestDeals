package tk.svsq.gamesbestdeals.presentation.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import tk.svsq.gamesbestdeals.domain.model.game.Game
import tk.svsq.gamesbestdeals.presentation.ui.search.items.GameMarker
import tk.svsq.gamesbestdeals.presentation.base.BaseViewHolder

class SearchGamesAdapter : ListAdapter<GameMarker, BaseViewHolder<*>>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = currentList.size

    class DiffCallback : DiffUtil.ItemCallback<GameMarker>() {
        override fun areItemsTheSame(oldItem: GameMarker, newItem: GameMarker) =
            (oldItem as? Game)?.gameID == (newItem as? Game)?.gameID

        override fun areContentsTheSame(oldItem: GameMarker, newItem: GameMarker) =
            oldItem == newItem
    }

}