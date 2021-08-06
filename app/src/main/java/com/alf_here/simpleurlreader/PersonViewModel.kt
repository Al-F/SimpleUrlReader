package com.alf_here.simpleurlreader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class PersonViewModel : ViewModel() {
    var person = MutableLiveData<Person?>()

    init {
        runBlocking { retrieveData() }
    }

    private suspend fun retrieveData() {
        person.value = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            makePersonRequest().getOrNull()
        }
    }

    private fun makePersonRequest(): Result<Person> {
        val url = URL("https://tools.learningcontainer.com/sample-json.json")
        (url.openConnection() as? HttpURLConnection)?.run {
            val response = URL("https://tools.learningcontainer.com/sample-json.json").readText()
            val person = Gson().fromJson(response, Person::class.java)
            return Result.success(person)
        }
        return Result.failure(Exception("Cannot open HttpURLConnection"))
    }
}