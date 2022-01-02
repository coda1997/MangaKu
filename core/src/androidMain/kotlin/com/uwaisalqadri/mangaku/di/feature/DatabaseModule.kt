package com.uwaisalqadri.mangaku.di.feature

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.uwaisalqadri.mangaku.data.base.MangaDaoHelper
import com.uwaisalqadri.mangaku.db.MangaDatabase
import com.uwaisalqadri.mangaku.utils.MangaAdapter
import org.koin.dsl.module

actual fun databaseModule() = module {
    single {
        val driver = AndroidSqliteDriver(MangaDatabase.Schema, context = get(), "app.db")
        MangaDaoHelper(MangaDatabase(driver, MangaAdapter.mangaAdapter))
    }
}
