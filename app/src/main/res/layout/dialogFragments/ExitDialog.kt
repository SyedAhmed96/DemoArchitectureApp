
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.fictivestudios.demoarcitectureapp.R
import com.fictivestudios.demoarcitectureapp.databinding.ExitDialogFragmentBinding


class ExitDialog : DialogFragment() {
    var binding: ExitDialogFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exit_dialog, container, false)

        getDialog()?.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimation
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding?.btnExit?.setOnClickListener(View.OnClickListener {
            activity?.finish()
        })

        binding?.btnClose?.setOnClickListener(View.OnClickListener {
            dialog?.dismiss()
        })
        return binding?.root
    }
}