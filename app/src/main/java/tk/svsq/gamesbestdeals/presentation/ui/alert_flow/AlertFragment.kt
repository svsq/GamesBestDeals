package tk.svsq.gamesbestdeals.presentation.ui.alert_flow

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.FragmentAlertBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding

@AndroidEntryPoint
class AlertFragment : BaseFragment(R.layout.fragment_alert) {

    private val binding by viewBinding(FragmentAlertBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}