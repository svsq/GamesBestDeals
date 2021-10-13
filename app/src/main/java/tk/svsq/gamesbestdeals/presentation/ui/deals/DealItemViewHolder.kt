package tk.svsq.gamesbestdeals.presentation.ui.deals

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tk.svsq.gamesbestdeals.databinding.ItemDealBinding
import tk.svsq.gamesbestdeals.domain.model.Deal
import tk.svsq.gamesbestdeals.presentation.common.gone
import tk.svsq.gamesbestdeals.presentation.common.toDateString

class DealItemViewHolder(private val dealItemBinding: ItemDealBinding) :
    RecyclerView.ViewHolder(dealItemBinding.root) {

        fun bind(deal: Deal) {
            with(dealItemBinding) {
                tvTitle.text = deal.title
                tvReleaseDate.text = getReleaseDate(deal)
                tvRegularPrice.text = "Regular price: \$${deal.normalPrice}"
                tvSalePrice.text = "Sale price: \$${deal.salePrice}"
                tvSavingsPercentage.text = "Savings: ${deal.savings.toDouble().toInt()}%"
                Glide.with(itemView)
                    .load(deal.thumb)
                    .centerInside()
                    .into(ivPicture)
            }
        }

    private fun getReleaseDate(deal: Deal): String {
        if (deal.releaseDate > 0) {
            return "Release Date: ${deal.releaseDate.toDateString()}"
        }

        if(deal.lastChange > 0) {
            return "Last change: ${deal.lastChange.toDateString()}"
        }

        dealItemBinding.tvReleaseDate.gone()
        return ""
    }
}