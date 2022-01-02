package com.uwaisalqadri.mangaku.data.souce.local.dao

import com.uwaisalqadri.mangaku.db.MangaEntity

interface MangaDao {
    suspend fun getAllManga(): List<MangaEntity>
    suspend fun getMangaById(id: String): List<MangaEntity>
    suspend fun addManga(item: MangaEntity)
    suspend fun deleteManga(id: String)
}