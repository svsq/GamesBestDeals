package tk.svsq.gamesbestdeals.presentation.ui.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.FragmentSecondBinding
import tk.svsq.gamesbestdeals.presentation.base.Status

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: SecondViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

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
                    Status.SUCCESS -> Toast.makeText(requireContext(), "${it.data}", Toast.LENGTH_SHORT).show()
                    Status.ERROR -> Toast.makeText(requireContext(), "${it.error?.message}", Toast.LENGTH_SHORT).show()
                    Status.LOADING -> Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}