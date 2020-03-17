package ir.milad.androidexamples


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.milad.androidexamples.R
import kotlinx.android.synthetic.main.fragment_third.*

/**
 * A simple [Fragment] subclass.
 */
class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_result.text = "Received argument: " + arguments?.getString("primitive_argument")

        val profile : SecondFragment.Profile? = arguments?.getParcelable("custom_object")
        profile?.let {
            customObjectId.text = it.id.toString()
            customObjectName.text = it.name
        }
    }

}
