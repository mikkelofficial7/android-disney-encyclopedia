package com.ewide.test.mikkel.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.mikkel.databinding.ItemCharacterBinding
import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.local.ListCharacter

class ItemAdapter<T>(val data: Class<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: ArrayList<T?>? = arrayListOf()
    internal var onClick: (String) -> Unit = {}
    internal var onAddToFavorite: (T) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderVideoGame(binding, onClick, onAddToFavorite)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterData(data: List<T?>?) {
        mList?.clear()
        mList?.addAll(data ?: listOf())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(data) {
            ListCharacterResponse::class.java -> (holder as ViewHolderVideoGame<*>).bindApi(mList?.get(position))
            ListCharacter::class.java -> (holder as ViewHolderVideoGame<*>).bindLocal(mList?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolderVideoGame<T>(
        private val itemBinding: ItemCharacterBinding,
        private val onClick: (String) -> Unit,
        private val onAddToFavorite: (T) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun <S>bindApi(game: S) {
            val itemVideoGame = game as ListCharacterResponse

            itemBinding.customViewHolder.setCharacterName(itemVideoGame.external.orEmpty())
            itemBinding.customViewHolder.setCharacterAlias(itemVideoGame.internalName.orEmpty())
            itemBinding.customViewHolder.setCharacterLogo(itemVideoGame.thumb.orEmpty())
            itemBinding.customViewHolder.setFavoriteHeartIcon(itemVideoGame.isFavorite)

            itemBinding.root.setOnClickListener {
                onClick(itemVideoGame.gameID.orEmpty())
            }

            itemBinding.customViewHolder.clickOnFavorite {
                onAddToFavorite(game as T)
            }
        }

        fun <S>bindLocal(game: S) {
            val itemVideoGame = game as ListCharacter

            itemBinding.customViewHolder.setCharacterName(itemVideoGame.external.orEmpty())
            itemBinding.customViewHolder.setCharacterAlias(itemVideoGame.internalName.orEmpty())
            itemBinding.customViewHolder.setCharacterLogo(itemVideoGame.thumb.orEmpty())
            itemBinding.customViewHolder.setFavoriteHeartIcon(true)

            itemBinding.root.setOnClickListener {
                onClick(itemVideoGame.gameID.orEmpty())
            }

            itemBinding.customViewHolder.clickOnFavorite {
                onAddToFavorite(game as T)
            }
        }
    }
}