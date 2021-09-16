package com.uwaisalqadri.mangaku.android.ui.browse.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.domain.model.Manga

@Composable
fun MangaTrending(
    myMangaViewModel: MyMangaViewModel,
    trendingManga: List<Manga>,
    modifier: Modifier,
    onMangaClick: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        trendingManga.forEach { manga ->
            Manga(
                manga = manga,
                modifier = Modifier
                    .height(197.dp)
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .clickable { onMangaClick(manga.id) }
            ) {
                myMangaViewModel.addFavoriteManga(it)
            }
        }
    }
}