package com.android.gamecommerce.viewModel.base

import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.text.isNotEmpty
import kotlin.text.startsWith
import kotlin.text.trim

abstract class BaseViewModel : ViewModel() {

    protected fun parseHttpError(httpException: HttpException): String {
        val response = httpException.response()
        return if (response != null && response.errorBody() != null) {
            try {
                val errorBodyString = response.errorBody()!!.string()

                if (errorBodyString.isNotEmpty() && errorBodyString.trim().startsWith("{")) {
                    // Valid JSON object
                    JSONObject(errorBodyString).optString("message", "Something went wrong")
                } else {
                    // Probably HTML or plain text
                    "Server error: $errorBodyString"
                }

            } catch (e: IOException) {
                Log.e("BaseViewModel", "Error reading error body", e)
                "Error reading server response"
            } catch (e: JSONException) {
                Log.e("BaseViewModel", "Error parsing error JSON", e)
                "Invalid server response format"
            }
        } else {
            "HTTP error: ${httpException.code()}"
        }
    }

    protected fun parseThrowable(error: Throwable): String {
        return when (error) {
            is HttpException -> parseHttpError(error)
            is UnknownHostException -> "Network error: Cannot connect to server"
            is SocketTimeoutException -> "Network timeout: Server is taking too long to respond"
            else -> {
                Log.e("BaseViewModel", "Unexpected error", error)
                "Unexpected error: ${error.message ?: error::class.simpleName}"
            }
        }
    }
}
