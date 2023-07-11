package com.ewide.test.mikkel.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.mikkel.databinding.ItemCharacterBinding
import com.ewide.test.mikkel.model.ListCharacterResponse

class ItemAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: List<T?>? = listOf()
    internal var onClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderVideo(binding, onClick)
    }

    fun setCharacterData(data: List<T?>?) {
        mList = data
        notifyItemInserted(0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderVideo).bind(mList?.get(position))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolderVideo(
        private val itemBinding: ItemCharacterBinding,
        private val onClick: (String) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

        fun <T>bind(video: T) {
            when(video) {
                is ListCharacterResponse -> {
                    itemBinding.customViewHolder.setCharacterName(video.external.orEmpty())
                    itemBinding.customViewHolder.setCharacterAlias(video.internalName.orEmpty())
                    itemBinding.customViewHolder.setCharacterLogo(video.thumb.orEmpty())

                    itemBinding.root.setOnClickListener {
                        onClick(video.gameID.orEmpty())
                    }
                }
            }
        }
    }
}