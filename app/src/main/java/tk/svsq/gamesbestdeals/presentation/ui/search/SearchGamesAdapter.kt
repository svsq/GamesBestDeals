package tk.svsq.gamesbestdeals.presentation.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.domain.model.game.Game
import tk.svsq.gamesbestdeals.domain.model.game.GameCount
import tk.svsq.gamesbestdeals.domain.model.game.GameMarker
import tk.svsq.gamesbestdeals.presentation.base.BaseViewHolder
import tk.svsq.gamesbestdeals.presentation.ui.search.items.SearchGameCountViewHolder
import tk.svsq.gamesbestdeals.presentation.ui.search.items.SearchGameViewHolder

class SearchGamesAdapter : ListAdapter<GameMarker, BaseViewHolder<*>>(DiffCallback()) {

    companion object {
        private const val TITLE = R.layout.item_search_title
        private const val ITEM = R.layout.item_game_info
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType) {
            ITEM -> SearchGameViewHolder.create(parent)
            TITLE -> SearchGameCountViewHolder.create(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> (holder as? SearchGameViewHolder)?.run {
                getItem(position)?.let { bind(it as Game) }
            }
            TITLE -> (holder as? SearchGameCountViewHolder)?.run {
                getItem(position)?.let { bind(it as GameCount) }
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is Game -> ITEM
            is GameCount -> TITLE
            else -> throw IllegalArgumentException("Invalid type of item $position")
        }
    }

    override fun getItemCount() = currentList.size

    class DiffCallback : DiffUtil.ItemCallback<GameMarker>() {
        override fun areItemsTheSame(oldItem: GameMarker, newItem: GameMarker) =
            (oldItem as? Game)?.gameID == (newItem as? Game)?.gameID &&
            (oldItem as? GameCount)?.count == (newItem as? GameCount)?.count

        override fun areContentsTheSame(oldItem: GameMarker, newItem: GameMarker) =
            oldItem == newItem
    }

}