package com.uwaisalqadri.mangaku.data.base

import com.uwaisalqadri.mangaku.data.souce.local.MangaDaoImpl
import com.uwaisalqadri.mangaku.data.souce.local.dao.MangaDao
import com.uwaisalqadri.mangaku.db.MangaDatabase

class MangaDaoHelper(val database: MangaDatabase) {
    fun getMangaDao(): MangaDao = MangaDaoImpl(database)
}
