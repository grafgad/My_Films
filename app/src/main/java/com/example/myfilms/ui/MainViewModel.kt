package com.example.myfilms.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.myfilms.data.repository.FilmRepository
import com.example.myfilms.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val repository: FilmRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _films = MutableLiveData<PagingData<Film>>()
    val films: LiveData<PagingData<Film>> get() = _films

    init {
        getNowPlayingFilms()
    }

    private fun getNowPlayingFilms() {
        compositeDisposable.add(
            repository.getNowPlayingFilms()
                .cachedIn(viewModelScope)
                .subscribe {
                    _films.value = it
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}