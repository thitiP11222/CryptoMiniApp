package com.example.cryptoapplication

import retrofit2.http.Url

data class CoinResponse (
    val data: CoinData?
)

data class CoinData(
    val coins: List<Coin>
)

data class Coin(
    val symbol: String,
    val name: String,
    val price: String,
    var iconUrl: String,
    val coinrankingUrl: String
) {
    init {
        if (!iconUrl.startsWith("https://")) {
            iconUrl = "https://$iconUrl"
        }
    }
}
