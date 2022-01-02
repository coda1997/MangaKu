package com.uwaisalqadri.mangaku.android.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.ResultType
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailUseCase: DetailUseCase
) : ViewModel() {

    private val _detailManga: MutableLiveData<ResultType<Manga>> = MutableLiveData()
    val detailManga: LiveData<ResultType<Manga>> = _detailManga

    init {
        _detailManga.value = ResultType.default()
    }

    fun getDetailManga(mangaId: String) = viewModelScope.launch {
        _detailManga.value = ResultType.loading()
        detailUseCase.getDetailManga(mangaId = mangaId)
            .catch { cause: Throwable ->
                _detailManga.value = ResultType.fail(cause.message)
            }
            .collect { result ->
                result?.let { _detailManga.value = ResultType.success(it) }
            }

    }

}

