package tk.svsq.gamesbestdeals.presentation.ui.deals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tk.svsq.gamesbestdeals.databinding.ItemDealBinding
import tk.svsq.gamesbestdeals.domain.model.Deal
import tk.svsq.gamesbestdeals.presentation.common.toDateString

class DealItemViewHolder(private val dealItemBinding: ItemDealBinding) :
    RecyclerView.ViewHolder(dealItemBinding.root) {

    companion object {
        fun newInstance(parent: ViewGroup) =
            DealItemViewHolder(
                ItemDealBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))
    }

        fun bind(deal: Deal) {
            with(dealItemBinding) {
                tvTitle.text = deal.title
                tvStoreName.text = if(deal.storeID == "1") "Steam" else deal.storeID
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

        dealItemBinding.tvReleaseDate.isVisible = false
        return ""
    }
}