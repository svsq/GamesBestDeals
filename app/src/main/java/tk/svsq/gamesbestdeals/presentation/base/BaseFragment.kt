package tk.svsq.gamesbestdeals.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    fun navigateTo(
        resourceId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: FragmentNavigator.Extras? = null
    ) {
        findNavController().apply {
            currentDestination?.getAction(resourceId)?.let {
                navigate(resourceId, args, navOptions, navigatorExtras)
            }
        }
    }

    fun navigateUp() = findNavController().navigateUp()
}