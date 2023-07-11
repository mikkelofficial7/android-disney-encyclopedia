package com.ewide.test.mikkel.customui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.ewide.test.mikkel.customui.databinding.CustomToolbarBinding

@SuppressLint("Recycle")
class CustomToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    private var binding: CustomToolbarBinding

    init {
        binding = CustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        LayoutInflater.from(binding.root.context)
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomToolbar)

            binding.tvToolbarTitle.setText(resources.getText(typedArray.getResourceId(R.styleable.CustomToolbar_toolbarTitle, R.string.defaultToolbarTitle))).toString()
            binding.tvToolbarTitle.setTextColor(resources.getColor(typedArray.getResourceId(R.styleable.CustomToolbar_textColor, R.color.white), resources.newTheme()))
            binding.toolbar.setBackgroundColor(resources.getColor(typedArray.getResourceId(R.styleable.CustomToolbar_backgroundColor, R.color.purple_200), resources.newTheme()))
            binding.tvToolbarFavorite.isVisible = typedArray.getBoolean(R.styleable.CustomToolbar_showFavoriteButton, true)

            typedArray.recycle()
        }
    }

    fun getToolbar() : Toolbar {
        return binding.toolbar
    }

    fun setToolbarTitle(title: String) {
        binding.tvToolbarTitle.text = title
    }

    fun clickOnFavoriteButton(invoke: () -> Unit) {
        if(!binding.tvToolbarFavorite.isVisible) return
        binding.tvToolbarFavorite.setOnClickListener {
            invoke()
        }
    }
}