package com.uwaisalqadri.mangaku.android.presentation.search.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.Montserrat

@Composable
fun SearchField(
    query: String,
    placeholder: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        modifier = modifier
    ) {

        var text by remember { mutableStateOf(placeholder) }

        Text(
            text = if (query.isEmpty()) text else "",
            style = MangaTypography.h3,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxSize()
                .clickable { text = "" }
                .padding(
                    start = 60.dp,
                    end = 10.dp,
                    top = 11.dp,
                    bottom = 5.dp
                )
        )

        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.surface.copy(alpha = 0.3f))
        ) {
            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp, top = 5.dp)
            )

            BasicTextField(
                value = query,
                textStyle = TextStyle(
                    color = MaterialTheme.colors.secondary,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                ),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    imeAction = ImeAction.Search
                ),

                keyboardActions = KeyboardActions(
                    onSearch = {
                        onExecuteSearch()
                    }
                ),

                onValueChange = {
                    onQueryChanged(it)
                    text = ""
                },

                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 11.dp, bottom = 5.dp)
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }
    }
}















