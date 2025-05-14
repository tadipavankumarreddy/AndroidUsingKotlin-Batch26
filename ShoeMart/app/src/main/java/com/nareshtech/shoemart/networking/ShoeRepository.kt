package com.nareshtech.shoemart.networking

import com.nareshtech.shoemart.data.Shoe
import kotlinx.serialization.json.Json
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object ShoeRepository{
    suspend fun fetchShoes(): List<Shoe>{
        val url = URL("https://raw.githubusercontent.com/tadipavankumarreddy/AndroidUsingKotlin-Batch26/refs/heads/master/shoesdata.json")
        val connection = url.openConnection() as HttpsURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        connection.disconnect()
        return Json { ignoreUnknownKeys = true}.decodeFromString<List<Shoe>>(data)
    }
}