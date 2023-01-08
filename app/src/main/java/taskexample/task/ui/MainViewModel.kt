package taskexample.task.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    //LiveData имени
    private val _nameLiveData = MutableLiveData<String>()
    val nameLiveData: MutableLiveData<String>
        get() = _nameLiveData

    //LiveData фамилии
    private val _serNameLiveData = MutableLiveData<String>()
    val serNameLiveData: MutableLiveData<String>
        get() = _serNameLiveData

    // кладем данные в liveData
    fun updateData(name: String, serName: String) {
        serNameLiveData.value = serName
        nameLiveData.value = name
    }
}