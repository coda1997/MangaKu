package com.uwaisalqadri.mangaku.domain.repository

import com.uwaisalqadri.mangaku.data.souce.remote.response.MangaItem
import com.uwaisalqadri.mangaku.db.MangaEntity

interface MangaRepository {
    suspend fun getManga(): List<MangaItem>
    suspend fun getTrendingManga(): List<MangaItem>
    suspend fun getSearchManga(query: String): List<MangaItem>
    suspend fun getDetailManga(mangaId: String): MangaItem?

    suspend fun getFavoriteManga(): List<MangaEntity>
    suspend fun getFavoriteMangaById(mangaId: String): List<MangaEntity>
    suspend fun addMangaFavorite(manga: MangaEntity)
    suspend fun deleteMangaFavorite(mangaId: String)
}