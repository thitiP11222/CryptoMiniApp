package com.example.cryptoapplication

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import coil.load
import coil.decode.SvgDecoder

class ListView(private val coins: List<Coin>) : RecyclerView.Adapter<ListView.CoinViewHolder>() {

    override fun getItemViewType(position: Int) = if ((position + 1) % 5 == 0) TYPE_HIGHLIGHT else TYPE_NORMAL

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CoinViewHolder(LayoutInflater.from(parent.context).inflate(
            if (viewType == TYPE_HIGHLIGHT) R.layout.item_coin_highlight else R.layout.item_coin,
            parent, false
        ), viewType)

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) = holder.bind(coins[position])

    override fun getItemCount() = coins.size

    class CoinViewHolder(view: View, private val viewType: Int) : RecyclerView.ViewHolder(view) {
        private val symbol: TextView = view.findViewById(if (viewType == TYPE_HIGHLIGHT) R.id.coinSymbol5 else R.id.coinSymbol)
        private val icon: ImageView = view.findViewById(if (viewType == TYPE_HIGHLIGHT) R.id.coinIcon5 else R.id.coinIcon)
        private val url: TextView? = view.findViewById(R.id.coinUrl5)
        private val name: TextView? = view.findViewById(R.id.coinName)

        fun bind(coin: Coin) {
            symbol.text = coin.symbol
            name?.text = coin.name
            url?.apply {
                text = coin.coinrankingUrl
                setOnClickListener { itemView.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(coin.coinrankingUrl))) }
            }
            icon.loadImage(coin.iconUrl)
        }

        private fun ImageView.loadImage(url: String) =
            if (url.endsWith(".svg")) load(url) { decoderFactory(SvgDecoder.Factory()); crossfade(true) }
            else Glide.with(context).load(url).into(this)
    }

    companion object {
        private const val TYPE_HIGHLIGHT = 0
        private const val TYPE_NORMAL = 1
    }
}