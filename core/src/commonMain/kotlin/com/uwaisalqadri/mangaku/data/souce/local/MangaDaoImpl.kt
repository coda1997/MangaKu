package com.uwaisalqadri.mangaku.data.souce.local

import com.uwaisalqadri.mangaku.data.base.BaseDao
import com.uwaisalqadri.mangaku.data.souce.local.dao.MangaDao
import com.uwaisalqadri.mangaku.db.MangaDatabase
import com.uwaisalqadri.mangaku.db.MangaEntity
import com.uwaisalqadri.mangaku.db.MangaEntityQueries

class MangaDaoImpl(private val database: MangaDatabase): MangaDao, BaseDao() {

    override val queries: MangaEntityQueries by lazy { database.mangaEntityQueries }

    override suspend fun getAllManga(): List<MangaEntity> {
        return queries.selectAll().executeAsList()
    }

    override suspend fun getMangaById(id: String): List<MangaEntity> {
        return queries.selectById(id = id).executeAsList()
    }

    override suspend fun addManga(item: MangaEntity) {
        queries.insertItem(
            id = item.id,
            type = item.type,
            attributes = item.attributes
        )
    }

    override suspend fun deleteManga(id: String) {
        queries.deleteItem(id = id)
    }
}