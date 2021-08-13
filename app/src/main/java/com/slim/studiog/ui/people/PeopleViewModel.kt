package com.slim.studiog.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slim.studiog.network.StudioGhibliApi
import com.slim.studiog.network.StudioGhibliApiStatus
import com.slim.studiog.network.models.People
import kotlinx.coroutines.*
import timber.log.Timber

class PeopleViewModel: ViewModel() {

    private val _status = MutableLiveData<StudioGhibliApiStatus>()
    val status: LiveData<StudioGhibliApiStatus> get() = _status

    private val _people = MutableLiveData<List<People>>()
    val people: LiveData<List<People>> get() = _people

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        Timber.d("getPeople")
        getPeople()
    }

    private fun getPeople() {
        _status.value = StudioGhibliApiStatus.LOADING
        coroutineScope.launch {
            try{
                var listResult = withContext(Dispatchers.IO){StudioGhibliApi.retrofitService.
                getPeople()}
                _status.value = StudioGhibliApiStatus.DONE
                _people.value = listResult
            } catch (t:Throwable) {
                _status.value = StudioGhibliApiStatus.ERROR
                _people.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun refresh(){
        getPeople()
    }
}