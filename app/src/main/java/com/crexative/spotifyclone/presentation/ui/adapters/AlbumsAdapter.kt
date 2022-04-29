package com.crexative.spotifyclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crexative.spotifyclone.data.models.Item
import com.crexative.spotifyclone.databinding.AlbumItemBinding

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<Item>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val binding = AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class AlbumHolder(private val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) = with(binding) {

            tvAlbumName.text = item.name
            tvArtistName.text = item.artists.first().name

            Glide.with(root.context)
                .load(item.images.first().url)
                .fitCenter()
                // .circleCrop()
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