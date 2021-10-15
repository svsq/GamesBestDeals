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

    private val binding by viewBinding(FragmentMainTabsBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}