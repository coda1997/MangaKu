package com.uwaisalqadri.mangaku.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.uwaisalqadri.mangaku.android.R

private val Montserrat = FontFamily(
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

private val MaShanZheng = FontFamily(
    Font(R.font.mashanzheng_regular, FontWeight.Medium)
)

private val SedgwickAve = FontFamily(
    Font(R.font.sedgwickavedisplay_regular, FontWeight.Medium)
)

val MangaTypography = Typography(
    h1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold
    ),
    h2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold
    ),
    caption = TextStyle(
        fontFamily = MaShanZheng,
        fontWeight = FontWeight.Medium
    ),
    overline = TextStyle(
        fontFamily = SedgwickAve,
        fontWeight = FontWeight.Medium
    )
)










