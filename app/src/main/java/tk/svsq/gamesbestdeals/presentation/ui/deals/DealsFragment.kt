package tk.svsq.gamesbestdeals.presentation.ui.deals

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.data.common.Status
import tk.svsq.gamesbestdeals.databinding.FragmentDealsBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding

@AndroidEntryPoint
class DealsFragment : BaseFragment(R.layout.fragment_deals) {

    private val binding by viewBinding(FragmentDealsBinding::bind)
    private val viewModel: DealsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.deals.collectLatest {
                when (it.status) {
                    Status.SUCCESS -> Toast.makeText(requireContext(), "${it.data}", Toast.LENGTH_SHORT).show()
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), "${it.error?.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}