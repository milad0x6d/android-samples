package ir.milad.androidexamples

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
        {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}