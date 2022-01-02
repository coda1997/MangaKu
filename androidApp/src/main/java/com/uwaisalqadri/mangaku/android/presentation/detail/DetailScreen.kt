package com.uwaisalqadri.mangaku.android.presentation.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.android.presentation.detail.composables.FavoriteDialog
import com.uwaisalqadri.mangaku.android.presentation.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.BackButton
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerDetail
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import com.uwaisalqadri.mangaku.android.utils.ResultType
import com.uwaisalqadri.mangaku.android.utils.getResult
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.Constants
import com.uwaisalqadri.mangaku.utils.Extensions
import com.uwaisalqadri.mangaku.utils.formatDate
import org.koin.androidx.compose.getViewModel
import com.uwaisalqadri.mangaku.android.utils.Result as Result

data class DetailScreen(
    val navigator: Navigator,
    val mangaId: String
): Screen {

    @Composable
    override fun Content() {
        DetailContent()
    }

    @Composable
    fun DetailContent(
        viewModel: DetailViewModel = getViewModel(),
        mangaViewModel: MyMangaViewModel = getViewModel(),
        extension: Extensions = Extensions
    ) {
        viewModel.getDetailManga(mangaId)
        mangaViewModel.checkFavorite(mangaId)

        val value by viewModel.detailManga.observeAsState()
        val favState by mangaViewModel.favState.observeAsState()

        val manga: Result<Manga> = value!!.getResult()

        val (isFavorite, setFavorite) = remember { mutableStateOf(false) }
        val (isShowDialog, setShowDialog) = remember { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colors.primary)
        ) {
            FavoriteDialog(
                message = if (isFavorite) "Added to Favorite" else "Removed from Favorite",
                isShowDialog = isShowDialog,
                setShowDialog = setShowDialog,
                modifier = Modifier.size(134.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 25.dp, end = 25.dp)
            ) {
                BackButton {
                    navigator.pop()
                }

                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            setShowDialog(true)
                            if (manga.loading) {
                                if (isFavorite) mangaViewModel.deleteMyManga(manga.data?.id ?: "")
                                else mangaViewModel.addMyManga(manga.data ?: Manga(id = "", type = ""))
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            TopBar(name = "Detail")

            Kermit(CommonLogger()).d { "whats here $manga, and value $value" }

            if (manga.loading) {
                ShimmerDetail()
            } else {

                when {
                    favState?.favMangaFound == true -> setFavorite(true)
                    favState?.addFavorite == true -> setFavorite(true)
                    favState?.removeFavorite == true -> setFavorite(false)
                    else -> setFavorite(false)
                }

                MangaDetail(
                    manga = manga.data ?: Manga(id = "", type = ""),
                    extension = extension
                )

                Spacer(modifier = Modifier.height(200.dp))
            }
        }
    }

    @Composable
    fun MangaDetail(
        manga: Manga,
        extension: Extensions
    ) {
        Card(
            shape = RoundedCornerShape(9.dp),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 16.dp)
                .height(200.dp)
        ) {
            Image(
                painter = rememberCoilPainter(request = extension.getCoverImage(manga)),
                contentDescription = "cover image",
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = extension.getTitle(manga),
            color = MaterialTheme.colors.secondary,
            style = MangaTypography.h1,
            fontSize = 23.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        Text(
            text = manga.attributes?.slug ?: "",
            color = MaterialTheme.colors.secondary,
            style = MangaTypography.h3,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, bottom = 10.dp)
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp)
        ) {
            Card(
                elevation = 0.dp,
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = formatDate(manga.attributes?.startDate ?: "", Constants.casualDateFormat),
                    color = Color.White,
                    style = MangaTypography.h1,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(horizontal = 15.dp, vertical = 5.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )

                Text(
                    text = (manga.attributes?.averageRating ?: 0.0).toString(),
                    color = MaterialTheme.colors.secondary,
                    style = MangaTypography.h2,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Description",
            color = MaterialTheme.colors.secondary,
            style = MangaTypography.h2,
            fontSize = 21.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

        Text(
            text = manga.attributes?.synopsis ?: "",
            color = MaterialTheme.colors.secondary,
            style = MangaTypography.h3,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 15.dp, start = 30.dp, end = 30.dp)
        )
    }
}