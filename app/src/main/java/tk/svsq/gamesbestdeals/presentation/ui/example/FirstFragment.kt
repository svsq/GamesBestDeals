package tk.svsq.gamesbestdeals.presentation.ui.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.FragmentFirstBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : BaseFragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            navigateTo(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}