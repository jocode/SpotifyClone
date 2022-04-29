package com.crexative.spotifyclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.crexative.spotifyclone.core.BaseRecyclerAdapter
import com.crexative.spotifyclone.core.BaseViewHolder
import com.crexative.spotifyclone.data.models.search.Item
import com.crexative.spotifyclone.databinding.AlbumItemBinding

class ArtistAdapter : BaseRecyclerAdapter<BaseViewHolder<Item>>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            try {
                oldItem.hashCode() == newItem.hashCode()
            } catch (e: Exception) {
                false
            }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<Item>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Item> {
        val binding = AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Item>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ArtistHolder(private val binding: AlbumItemBinding) :
        BaseViewHolder<Item>(binding.root) {

        override fun bind(item: Item) = with(binding) {

            tvAlbumName.text = item.name
            tvArtistName.text = item.genres.joinToString(" - ")

            Glide.with(root.context)
                .load(item.images.firstOrNull()?.url)
                .circleCrop()
                .into(imgAlbum)

            handleClick(item)
        }

        private fun handleClick(item: Item) {
            binding.root.setOnClickListener {
                onItemClickListener?.let { onItemClick ->
                    onItemClick(item)
                }
            }
        }
    }

    private var onItemClickListener: ((Item) -> Unit)? = null

    fun setOnItemClickListener(onItemClick: (Item) -> Unit) {
        this.onItemClickListener = onItemClick
    }
}