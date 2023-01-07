package taskexample.task.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    //LiveData имени
    private val _nameLiveData = MutableLiveData<String>()
    val nameLiveData: LiveData<String>
        get() = _nameLiveData

    //LiveData фамилии
    private val _serNameLiveData = MutableLiveData<String>()
    val serNameLiveData: LiveData<String>
        get() = _serNameLiveData

    // кладем данные в liveData
    fun updateSerName(result: String): String {
        _serNameLiveData.value = result
        return result
    }

    fun updateName(result: String): String {
        _nameLiveData.value = result
        return result
    }
}