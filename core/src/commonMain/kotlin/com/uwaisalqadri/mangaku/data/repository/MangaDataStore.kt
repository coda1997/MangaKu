package com.uwaisalqadri.mangaku.data.repository

import com.uwaisalqadri.mangaku.data.base.MangaDaoHelper
import com.uwaisalqadri.mangaku.data.souce.remote.MangaApiClient
import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaku.db.MangaEntity
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository

class MangaDataStore(
    private val mangaApiClient: MangaApiClient,
    private val mangaDaoHelper: MangaDaoHelper
): MangaRepository {

    override suspend fun getManga(): List<MangaItem> {
        val response = mangaApiClient.fetchManga()
        return response.data
    }

    override suspend fun getTrendingManga(): List<MangaItem> {
        val response = mangaApiClient.fetchTrendingManga()
        return response.data
    }

    override suspend fun getSearchManga(query: String): List<MangaItem> {
        val response = mangaApiClient.fetchSearchManga(query = query)
        return response.data
    }

    override suspend fun getDetailManga(mangaId: String): MangaItem? {
        val response = mangaApiClient.fetchDetailManga(id = mangaId)
        return response.data
    }

    override suspend fun getFavoriteManga(): List<MangaEntity> {
        return mangaDaoHelper.getMangaDao().getAllManga()
    }

    override suspend fun getFavoriteMangaById(mangaId: String): List<MangaEntity> {
        return mangaDaoHelper.getMangaDao().getMangaById(id = mangaId)
    }

    override suspend fun addMangaFavorite(manga: MangaEntity) {
        mangaDaoHelper.getMangaDao().addManga(item = manga)
    }

    override suspend fun deleteMangaFavorite(mangaId: String) {
        mangaDaoHelper.getMangaDao().deleteManga(id = mangaId)
    }

}