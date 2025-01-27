package com.uwaisalqadri.mangaku.android.presentation.mymanga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.FavState
import com.uwaisalqadri.mangaku.android.utils.Result
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.MyMangaUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyMangaViewModel(
    private val myMangaUseCase: MyMangaUseCase
): ViewModel() {

    private val _myManga = MutableStateFlow<Result<List<Manga>>>(Result.loading())
    val myManga = _myManga.asStateFlow()

    private val _favState = MutableStateFlow<FavState>(FavState())
    val favState: StateFlow<FavState> = _favState.asStateFlow()


    fun addMyManga(manga: Manga) {
        myMangaUseCase.addManga(manga)
        _favState.value = FavState(addFavorite = true)
    }

    fun deleteMyManga(mangaId: String) {
        myMangaUseCase.deleteManga(mangaId)
        _favState.value = FavState(removeFavorite = true)
    }

    fun checkFavorite(mangaId: String) = viewModelScope.launch {
        myMangaUseCase.getMyMangaById(mangaId).collect { result ->
            result.forEach {
                _favState.value = FavState(favMangaFound = it.id == mangaId)
            }
        }
    }

    fun getMyManga() = viewModelScope.launch {
        myMangaUseCase.getMyManga()
            .catch { cause: Throwable ->
                _myManga.value = Result.failed(cause)
            }
            .collect { result ->
                if (result.isNotEmpty()) {
                    _myManga.value = Result.success(result)
                } else {
                    _myManga.value = Result.empty()
                }
            }
    }

}