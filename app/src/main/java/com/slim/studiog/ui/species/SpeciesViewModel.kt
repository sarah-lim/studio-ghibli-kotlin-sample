package com.slim.studiog.ui.species

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slim.studiog.network.StudioGhibliApi
import com.slim.studiog.network.StudioGhibliApiStatus
import com.slim.studiog.network.models.Species
import kotlinx.coroutines.*
import timber.log.Timber

class SpeciesViewModel: ViewModel() {

    private val _status = MutableLiveData<StudioGhibliApiStatus>()
    val status : LiveData<StudioGhibliApiStatus> get() = _status

    private val _species = MutableLiveData<List<Species>>()
    val species : LiveData<List<Species>> get() = _species

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        Timber.d("getSpecies")
        getSpecies()
    }

    private fun getSpecies() {
        _status.value = StudioGhibliApiStatus.LOADING
        coroutineScope.launch {
            try{
                var listResult = withContext(Dispatchers.IO){
                    StudioGhibliApi.retrofitService.getSpecies()}
                _status.value = StudioGhibliApiStatus.DONE
                _species.value = listResult
            } catch (t: Throwable){
                _status.value = StudioGhibliApiStatus.ERROR
                _species.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}