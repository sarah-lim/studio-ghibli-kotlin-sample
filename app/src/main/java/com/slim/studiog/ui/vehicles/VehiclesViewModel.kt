package com.slim.studiog.ui.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slim.studiog.network.StudioGhibliApi
import com.slim.studiog.network.StudioGhibliApiStatus
import com.slim.studiog.network.models.Vehicle
import kotlinx.coroutines.*
import timber.log.Timber

class VehiclesViewModel : ViewModel() {

    private val _status = MutableLiveData<StudioGhibliApiStatus>()
    val status : LiveData<StudioGhibliApiStatus> get() = _status

    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicles: LiveData<List<Vehicle>> get() = _vehicles

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        Timber.d("getVehicles")
        getVehicles()
    }

    private fun getVehicles() {
        _status.value = StudioGhibliApiStatus.LOADING
         coroutineScope.launch {
             try {
                 var listResult = withContext(Dispatchers.IO){
                     StudioGhibliApi.retrofitService.getVehicles() }
                 _status.value = StudioGhibliApiStatus.DONE
                 _vehicles.value = listResult
             } catch (t: Throwable) {
                 _status.value = StudioGhibliApiStatus.ERROR
                 _vehicles.value = ArrayList()
             }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}