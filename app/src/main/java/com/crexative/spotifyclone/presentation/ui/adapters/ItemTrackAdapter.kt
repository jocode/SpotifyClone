package com.crexative.spotifyclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.core.BaseRecyclerAdapter
import com.crexative.spotifyclone.core.BaseViewHolder
import com.crexative.spotifyclone.data.models.albums.AlbumItem
import com.crexative.spotifyclone.databinding.TrackItemBinding

class ItemTrackAdapter : BaseRecyclerAdapter<BaseViewHolder<AlbumItem>>() {

    private val diffCallback = object : DiffUtil.ItemCallback<AlbumItem>() {
        override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean =
            try {
                oldItem.hashCode() == newItem.hashCode()
            } catch (e: Exception) {
                false
            }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<AlbumItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AlbumItem> {
        val binding = TrackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<AlbumItem>, position: Int) {
        when (holder) {
            is TrackHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    inner class TrackHolder(private val binding: TrackItemBinding) :
        BaseViewHolder<AlbumItem>(binding.root) {

        override fun bind(item: AlbumItem) = with(binding) {

            tvTrackName.text = item.name
            tvArtistName.text = item.artists.joinToString(", ") { it.name }

            Glide.with(root.context)
                .load(item.image)
                .placeholder(R.drawable.ic_music_note)
                .into(imgTrack)

            handleClick(item)
        }

        private fun handleClick(item: AlbumItem) {
            binding.root.setOnClickListener {
                onItemClickListener?.let { onItemClick ->
                    onItemClick(item)
                }
            }
        }
    }

    private var onItemClickListener: ((AlbumItem) -> Unit)? = null

    fun setOnItemClickListener(onItemClick: (AlbumItem) -> Unit) {
        this.onItemClickListener = onItemClick
    }
}