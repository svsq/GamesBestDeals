package tk.svsq.gamesbestdeals.presentation.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tk.svsq.gamesbestdeals.R

abstract class BaseBottomDialogFragment : BottomSheetDialogFragment() {

    open val resLayout = 0
    open val customTheme = 0
    open val setRoundCorner = 0

    private var behavior: BottomSheetBehavior<FrameLayout>? = null
    private var sheetFrame: FrameLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.bottom_dialog, container, false)
        if (setRoundCorner != 0) {
            view.findViewById<FrameLayout>(R.id.rootLayout).background =
                ContextCompat.getDrawable(requireContext(), setRoundCorner)
        }
        val frameLayout = view.findViewById<FrameLayout>(R.id.flBottomDialogContainer)
        if (resLayout != 0) frameLayout.addView(View.inflate(context, resLayout, null))
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        context?.let { ctx ->
            return CustomBottomDialog(ctx, customTheme).apply {
                setOnShowListener {
                    // Find a Frame layout with Bottom sheet behavior in the dialog
                    (it as? BottomSheetDialog)?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                        ?.let { sheet -> setupBottomSheet(sheet) }
                }
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    /**
     * Setup bottom sheet behaviour and save a link to the sheet layout
     * @param sheet - bottom sheet frame layout
     */
    private fun setupBottomSheet(sheet: FrameLayout) {
        sheetFrame = sheet
        behavior = BottomSheetBehavior.from(sheetFrame!!)
        behavior?.apply {
            peekHeight = 0
            isHideable = true
            skipCollapsed = true
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    open fun onBackPressed() {
        dismiss()
    }

    inner class CustomBottomDialog(context: Context, theme: Int) : BottomSheetDialog(context, theme) {
        override fun onStart() {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        override fun onBackPressed() {
            this@BaseBottomDialogFragment.onBackPressed()
        }
    }

}