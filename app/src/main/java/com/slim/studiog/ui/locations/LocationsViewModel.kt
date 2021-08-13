package com.slim.studiog.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slim.studiog.network.StudioGhibliApi
import com.slim.studiog.network.StudioGhibliApiStatus
import com.slim.studiog.network.models.Location
import kotlinx.coroutines.*
import timber.log.Timber

class LocationsViewModel: ViewModel() {

    private val _status = MutableLiveData<StudioGhibliApiStatus>()
    val status : LiveData<StudioGhibliApiStatus> get() = _status

    private val _locations = MutableLiveData<List<Location>>()

    val locations: LiveData<List<Location>> get() = _locations

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        Timber.d("getLocations")
        getLocations()
    }

    private fun getLocations() {
        _status.value = StudioGhibliApiStatus.LOADING
        coroutineScope.launch {
            try{
                var listResult = withContext(Dispatchers.IO){
                    StudioGhibliApi.retrofitService.getLocations()
                }
                _status.value = StudioGhibliApiStatus.DONE
                _locations.value = listResult
            } catch (t: Throwable) {
                _status.value = StudioGhibliApiStatus.ERROR
                _locations.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun refresh() {
        getLocations()
    }
}