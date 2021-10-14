package tk.svsq.gamesbestdeals.presentation.ui.search.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import tk.svsq.gamesbestdeals.databinding.ItemGameInfoBinding
import tk.svsq.gamesbestdeals.domain.model.game.Game
import tk.svsq.gamesbestdeals.presentation.base.BaseViewHolder

class SearchGameViewHolder(
    private val binding: ItemGameInfoBinding,
) : BaseViewHolder<Game>(binding) {

    companion object {
        fun create(
            parent: ViewGroup,
        ) = SearchGameViewHolder(
            ItemGameInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

        internal var onItemClicked: ((Game) -> Unit)? = null
    }

    override fun bind(item: Game) {
        with(binding) {
            Glide.with(itemView.context)
                .load(item.thumb)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                .into(ivThumb)

            tvGameTitle.text = item.external
            tvCheapestDeal.text = item.cheapest
        }
    }

}