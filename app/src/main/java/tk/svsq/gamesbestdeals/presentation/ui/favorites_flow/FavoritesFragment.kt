package tk.svsq.gamesbestdeals.presentation.ui.favorites_flow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.data.common.Resource
import tk.svsq.gamesbestdeals.data.common.Status
import tk.svsq.gamesbestdeals.databinding.FragmentFavoritesBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding
import tk.svsq.gamesbestdeals.presentation.ui.common.dialogs.EditEmailDialog

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
        binding.btnEmail.setOnClickListener {
            EditEmailDialog.show(
                context = requireContext(),
                message = "test",
                title = "email",
                okListener = { email -> viewModel.saveId(email) },
                okText = "OK",
                negativeListener = null,
                negativeText = "Cancel",
                isCancellable = false
            )
        }
        lifecycleScope.launchWhenStarted {
            viewModel.favoritesIds.collect {
                when(it.status) {
                    Status.SUCCESS -> {
                        viewModel
                    }
                    Status.ERROR -> {}
                    Status.LOADING -> {}
                }

            }
        }

    }

}