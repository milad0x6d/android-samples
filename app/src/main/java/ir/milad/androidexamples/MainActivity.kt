package ir.milad.androidexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.data.observe(this, Observer {
            Toast.makeText(this,it[0].login,Toast.LENGTH_SHORT).show()
        })

        userViewModel.loadingState.observe(this, Observer {
            Toast.makeText(this,it.status.toString(),Toast.LENGTH_SHORT).show()
        })


    }
}
