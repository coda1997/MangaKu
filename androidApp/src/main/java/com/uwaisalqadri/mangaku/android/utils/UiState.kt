package com.uwaisalqadri.mangaku.android.utils

import androidx.compose.runtime.Composable
import com.uwaisalqadri.mangaku.android.utils.ResultType.Companion.default
import com.uwaisalqadri.mangaku.domain.model.Manga

@Composable
fun <U> ResultType<U>.getResult(): Result<U> {
    val result: Result<U> = Result()
    when (this) {
        is ResultType.Loading -> {
            result.loading = true
        }

        is ResultType.Success -> {
            result.loading = false
            result.data = data
        }

        is ResultType.Failure -> {
            result.loading = false
            throwable?.let { result.error = it }
        }
    }

    return result
}


data class Result<T>(
    var data: T? = null,
    var loading: Boolean = false,
    var error: Throwable? = null
)

data class UiState(
    val listManga: List<Manga> = emptyList(),
    val isLoading: Boolean = false,
    val refreshError: Boolean = false
)

sealed class ResultType<T> {
    class Loading<T> : ResultType<T>()
    class Default<T> : ResultType<T>()
    class Empty<T> : ResultType<T>()
    data class Success<T>(val data: T) : ResultType<T>()
    data class Failure<T>(val throwable: Throwable?, val message: String?) : ResultType<T>()

    companion object {
        fun <T> loading(): ResultType<T> = Loading()
        fun <T> default(): ResultType<T> = Default()
        fun <T> success(data: T): ResultType<T> = Success(data)
        fun <T> empty(): ResultType<T> = Empty()
        fun <T> fail(throwable: Throwable, message: String?): ResultType<T> = Failure(throwable, message)
        fun <T> fail(message: String?): ResultType<T> = Failure(null, message)
    }
}
