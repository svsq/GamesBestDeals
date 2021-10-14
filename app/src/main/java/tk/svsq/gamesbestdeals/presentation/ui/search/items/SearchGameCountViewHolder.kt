package tk.svsq.gamesbestdeals.presentation.ui.search.items

import android.view.LayoutInflater
import android.view.ViewGroup
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.ItemSearchTitleBinding
import tk.svsq.gamesbestdeals.domain.model.game.GameCount
import tk.svsq.gamesbestdeals.presentation.base.BaseViewHolder

class SearchGameCountViewHolder(
    private val binding: ItemSearchTitleBinding,
) : BaseViewHolder<GameCount>(binding) {

    companion object {
        fun create(
            parent: ViewGroup,
        ) = SearchGameCountViewHolder(
            ItemSearchTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun bind(item: GameCount) {
        with(binding) {
            tvCount.text = itemView.context.getString(R.string.search_count, item.count)
        }
    }

}