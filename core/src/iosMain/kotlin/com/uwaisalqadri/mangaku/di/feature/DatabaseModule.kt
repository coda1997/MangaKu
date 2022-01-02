package com.uwaisalqadri.mangaku.di.feature

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.uwaisalqadri.mangaku.data.base.MangaDaoHelper
import com.uwaisalqadri.mangaku.db.MangaDatabase
import com.uwaisalqadri.mangaku.utils.MangaAdapter
import org.koin.dsl.module

fun setupDaoHelperForIos(): MangaDaoHelper {
    val driver = NativeSqliteDriver(MangaDatabase.Schema, "app.db")
    return MangaDaoHelper(MangaDatabase(driver, MangaAdapter.mangaAdapter))
}

actual fun databaseModule() = module {
    single {
        setupDaoHelperForIos()
    }
}
