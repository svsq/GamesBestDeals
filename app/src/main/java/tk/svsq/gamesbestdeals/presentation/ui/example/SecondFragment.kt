package tk.svsq.gamesbestdeals.presentation.ui.example

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.FragmentSecondBinding
import tk.svsq.gamesbestdeals.data.common.Status
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding

@AndroidEntryPoint
class SecondFragment : BaseFragment(R.layout.fragment_second) {

    private val binding by viewBinding(FragmentSecondBinding::bind)
    private val viewModel: SecondViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        observeStoresList()
    }

    private fun observeStoresList() {
        lifecycleScope.launch {
            viewModel.stores.collectLatest {
                when(it.status) {
                    Status.SUCCESS -> Toast.makeText(requireContext(), "${it.data?.size}", Toast.LENGTH_SHORT).show()
                    Status.ERROR -> Toast.makeText(requireContext(), "${it.error?.message}", Toast.LENGTH_SHORT).show()
                    Status.LOADING -> Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}