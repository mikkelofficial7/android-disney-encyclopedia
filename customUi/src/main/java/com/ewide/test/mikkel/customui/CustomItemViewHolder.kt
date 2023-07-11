package com.ewide.test.mikkel.customui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.ewide.test.mikkel.customui.databinding.CustomViewHolderBinding

@SuppressLint("Recycle")
class CustomItemViewHolder @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    private var binding: CustomViewHolderBinding

    init {
        binding = CustomViewHolderBinding.inflate(LayoutInflater.from(context), this, true)
        LayoutInflater.from(binding.root.context)
    }

    fun setCharacterName(name: String) {
        binding.characterName.text = name
    }

    fun setCharacterAlias(alias: String) {
        binding.characterAlias.text = alias
    }

    fun setCharacterLogo(url: String) {
       Glide.with(context).load(url).placeholder(R.drawable.ic_empty_image).into(binding.characterLogo)
    }

    fun setFavoriteHeartIcon(isFavorite: Boolean = true) {
        val icon = if(isFavorite) R.drawable.ic_heart else R.drawable.ic_heart_border
        binding.btnAddFavorite.setImageResource(icon)
    }

    fun clickOnFavorite(invoke : () -> Unit) {
        binding.btnAddFavorite.setOnClickListener {
            invoke()
        }
    }
}