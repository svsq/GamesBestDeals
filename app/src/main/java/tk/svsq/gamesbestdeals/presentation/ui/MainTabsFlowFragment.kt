package tk.svsq.gamesbestdeals.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import tk.svsq.gamesbestdeals.databinding.FragmentMainTabsBinding
import tk.svsq.gamesbestdeals.presentation.common.viewBinding

@AndroidEntryPoint
class MainTabsFlowFragment : Fragment() {

    /*private var _binding: FragmentMainTabsBinding? = null
    private val binding get() = _binding!!*/
    private val binding by viewBinding(FragmentMainTabsBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //_binding = FragmentMainTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

}