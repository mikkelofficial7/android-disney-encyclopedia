package com.ewide.test.mikkel.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.mikkel.databinding.ItemCharacterBinding
import com.ewide.test.mikkel.model.ListCharacterResponse

class ItemAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: ArrayList<T?>? = arrayListOf()
    internal var onClick: (String) -> Unit = {}
    internal var onAddToFavorite: (T) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderVideo(binding, onClick, onAddToFavorite)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterData(data: List<T?>?) {
        mList?.clear()
        mList?.addAll(data ?: listOf())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderVideo<*>).bind(mList?.get(position))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolderVideo<T>(
        private val itemBinding: ItemCharacterBinding,
        private val onClick: (String) -> Unit,
        private val onAddToFavorite: (T) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun <S>bind(video: S) {
            val itemVideo = video as ListCharacterResponse

            itemBinding.customViewHolder.setCharacterName(itemVideo.external.orEmpty())
            itemBinding.customViewHolder.setCharacterAlias(itemVideo.internalName.orEmpty())
            itemBinding.customViewHolder.setCharacterLogo(itemVideo.thumb.orEmpty())

            itemBinding.root.setOnClickListener {
                onClick(itemVideo.gameID.orEmpty())
            }

            itemBinding.customViewHolder.clickOnFavorite {
                onAddToFavorite(video as T)
            }
        }
    }
}