package com.crexative.spotifyclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.crexative.spotifyclone.core.BaseRecyclerAdapter
import com.crexative.spotifyclone.core.BaseViewHolder
import com.crexative.spotifyclone.data.models.search.ItemTrack
import com.crexative.spotifyclone.databinding.AlbumItemBinding

class TrackAdapter : BaseRecyclerAdapter<BaseViewHolder<ItemTrack>>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ItemTrack>() {
        override fun areItemsTheSame(oldItem: ItemTrack, newItem: ItemTrack): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ItemTrack, newItem: ItemTrack): Boolean =
            try {
                oldItem.hashCode() == newItem.hashCode()
            } catch (e: Exception) {
                false
            }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<ItemTrack>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemTrack> {
        val binding = AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemTrack>, position: Int) {
        when (holder) {
            is TrackHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    inner class TrackHolder(private val binding: AlbumItemBinding) :
        BaseViewHolder<ItemTrack>(binding.root) {

        override fun bind(item: ItemTrack) = with(binding) {
            tvAlbumName.text = item.name
            tvArtistName.text = item.artists.joinToString(", ") { it.name }

            Glide.with(root.context)
                .load(item.album.images.first().url)
                .fitCenter()
                .into(imgAlbum)

            handleClick(item)
        }

        private fun handleClick(item: ItemTrack) {
            binding.root.setOnClickListener {
                onItemClickListener?.let { onItemClick ->
                    onItemClick(item)
                }
            }
        }
    }

    private var onItemClickListener: ((ItemTrack) -> Unit)? = null

    fun setOnItemClickListener(onItemClick: (ItemTrack) -> Unit) {
        this.onItemClickListener = onItemClick
    }
}