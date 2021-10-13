package tk.svsq.gamesbestdeals.presentation.ui.deals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tk.svsq.gamesbestdeals.databinding.ItemDealBinding
import tk.svsq.gamesbestdeals.domain.model.Deal

class DealsAdapter : ListAdapter<Deal, DealItemViewHolder>(DiffCallback()) {

    private lateinit var binding: ItemDealBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        binding = ItemDealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DealItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DealItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = currentList.size

    class DiffCallback : DiffUtil.ItemCallback<Deal>() {
        override fun areItemsTheSame(oldItem: Deal, newItem: Deal) =
            oldItem.dealID == newItem.dealID

        override fun areContentsTheSame(oldItem: Deal, newItem: Deal) =
            oldItem == newItem
    }
}