package tk.svsq.gamesbestdeals.presentation.ui.favorites_flow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.data.common.Status
import tk.svsq.gamesbestdeals.databinding.FragmentFavoritesBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getIds()
        binding.btnEmail.setOnClickListener {}
        lifecycleScope.launchWhenStarted {
            viewModel.favoritesIds.collect {
                when(it.status) {
                    Status.SUCCESS -> {}
                    Status.ERROR -> {}
                    Status.LOADING -> {}
                }
            }
        }
    }

}