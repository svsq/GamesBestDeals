package tk.svsq.gamesbestdeals.presentation.ui.search.filter

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.DialogFilterSearchBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseBottomDialogFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding
import tk.svsq.gamesbestdeals.presentation.ui.search.SearchGamesViewModel

@AndroidEntryPoint
class FilterSearchGameDialog : BaseBottomDialogFragment() {

    companion object {
        fun newInstance(
            fragmentManager: FragmentManager,
        ) = FilterSearchGameDialog().show(fragmentManager, FilterSearchGameDialog::class.java.simpleName)
    }

    override val resLayout = R.layout.dialog_filter_search
    override val customTheme = R.style.AppBottomSheetDialogThemeNotFloating

    private val binding by viewBinding(DialogFilterSearchBinding::bind)
    private val viewModel: SearchGamesViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
    }

    private fun initView() {
        viewModel.searchParams.apply {
            binding.cbExact.isChecked = exact
            steamAppID?.let { binding.etSteamAppId.setText(it) }
            binding.sbAmount.progress = limit
        }
        binding.tvAmount.text = binding.sbAmount.progress.toString()
        binding.sbAmount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvAmount.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun initListeners() {
        binding.btnApply.setOnClickListener {
            setParams()
            dismiss()
            if (viewModel.searchParams.query.isNotEmpty()) viewModel.searchGames()
        }
    }

    private fun setParams() {
        viewModel.searchParams.exact = binding.cbExact.isChecked
        binding.etSteamAppId.text.toString().takeIf { it.isNotEmpty() }?.let {
            viewModel.searchParams.steamAppID = it
        }

        viewModel.searchParams.limit = binding.sbAmount.progress
    }

}