package tk.svsq.gamesbestdeals.presentation.ui.favorites_flow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.FragmentFavoritesBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding
import tk.svsq.gamesbestdeals.presentation.ui.common.dialogs.EditEmailDialog

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEmail.setOnClickListener {
            EditEmailDialog.show(
                context = requireContext(),
                message = "test",
                title = "email",
                okListener = { email -> Toast.makeText(requireContext(), email, Toast.LENGTH_SHORT).show() },
                okText = "OK",
                negativeListener = null,
                negativeText = "Cancel",
                isCancellable = false
            )
        }
    }

}