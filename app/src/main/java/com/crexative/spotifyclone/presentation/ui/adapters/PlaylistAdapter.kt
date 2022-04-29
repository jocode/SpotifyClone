package com.crexative.spotifyclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.core.BaseRecyclerAdapter
import com.crexative.spotifyclone.core.BaseViewHolder
import com.crexative.spotifyclone.data.models.playlist.Item
import com.crexative.spotifyclone.databinding.PlaylistItemBinding

class PlaylistAdapter : BaseRecyclerAdapter<BaseViewHolder<Item>>() {

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
        val binding =
            PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Item>, position: Int) {
        when (holder) {
            is TrackHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    inner class TrackHolder(private val binding: PlaylistItemBinding) :
        BaseViewHolder<Item>(binding.root) {

        override fun bind(item: Item) = with(binding) {
            tvPlaylistName.text = item.name
            tvPlaylistContent.text = item.description

            Glide.with(root.context)
                .load(item.images.firstOrNull()?.url)
                .placeholder(R.drawable.ic_music_note)
                .fitCenter()
                .into(imageView)

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