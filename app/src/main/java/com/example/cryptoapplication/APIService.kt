package com.example.cryptoapplication;

import com.example.cryptoapplication.Coin
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("coins")
    suspend fun getCoins(): Response<CoinResponse>
}
