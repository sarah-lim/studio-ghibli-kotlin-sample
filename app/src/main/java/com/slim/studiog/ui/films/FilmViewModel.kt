package com.slim.studiog.ui.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slim.studiog.network.StudioGhibliApi
import com.slim.studiog.network.StudioGhibliApiStatus
import com.slim.studiog.network.models.Film
import kotlinx.coroutines.*
import timber.log.Timber


class FilmViewModel : ViewModel(){

    private val _status = MutableLiveData<StudioGhibliApiStatus>()

    val status: LiveData<StudioGhibliApiStatus> get() = _status

    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>> get() = _films

    private var viewModelJob = Job()
    private val coroutineScopeUI = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        Timber.d("getFilms")
        getFilms()
    }

    private fun getFilms() {
        _status.value = StudioGhibliApiStatus.LOADING
        coroutineScopeUI.launch {
            try {
                var listResult = withContext(Dispatchers.IO){StudioGhibliApi.retrofitService.getFilms()}
                _status.value = (StudioGhibliApiStatus.DONE)
                _films.value = listResult
            } catch (t:Throwable) {
                Timber.d("error: ${t.message}")
                _status.value = StudioGhibliApiStatus.ERROR
                _films.value = ArrayList()
            }
        }
    }

    fun refresh() {
        getFilms()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}