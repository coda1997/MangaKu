package com.uwaisalqadri.mangaku.utils

import com.squareup.sqldelight.ColumnAdapter
import com.uwaisalqadri.mangaku.data.souce.remote.response.AttributesItem
import com.uwaisalqadri.mangaku.db.MangaEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object MangaAdapter {
    val mangaAdapter = MangaEntity.Adapter(attributesAdapter = AttributesAdapter)
}

object AttributesAdapter: ColumnAdapter<AttributesItem, String> {
    override fun decode(databaseValue: String): AttributesItem {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: AttributesItem): String {
        return Json.encodeToString(value)
    }
}














