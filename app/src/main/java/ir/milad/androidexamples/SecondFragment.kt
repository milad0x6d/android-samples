package ir.milad.androidexamples


import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ir.milad.androidexamples.R
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSecond.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("primitive_argument","my argument")
            bundle.putParcelable("custom_object",Profile(99,"milad"))
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_thirdFragment,bundle)
        }
    }

    class Profile(var id : Int,var name:String) : Parcelable{

        constructor(parcel: Parcel) : this(parcel.readInt(),parcel.readString())

        override fun writeToParcel(p0: Parcel?, p1: Int) {
            p0!!.writeInt(id)
            p0.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Profile> {
            override fun createFromParcel(parcel: Parcel): Profile {
                return Profile(parcel)
            }

            override fun newArray(size: Int): Array<Profile?> {
                return arrayOfNulls(size)
            }
        }

    }
}
