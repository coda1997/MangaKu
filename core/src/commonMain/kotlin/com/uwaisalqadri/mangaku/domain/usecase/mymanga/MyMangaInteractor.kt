package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.base.BaseInteractor
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class MyMangaInteractor(private val repository: MangaRepository): MyMangaUseCase, BaseInteractor() {

    override suspend fun getMyManga(): Flow<List<Manga>> {
        return execute {
            repository.getFavoriteManga().map()
        }
    }

    override suspend fun getMyMangaById(mangaId: String): Flow<List<Manga>> {
        return execute {
            repository.getFavoriteMangaById(mangaId = mangaId).map()
        }
    }

    override suspend fun addManga(manga: Manga) {
        val mapper = manga.map()
        repository.addMangaFavorite(manga = mapper)
    }

    override suspend fun deleteManga(mangaId: String) {
        repository.deleteMangaFavorite(mangaId = mangaId)
    }
}