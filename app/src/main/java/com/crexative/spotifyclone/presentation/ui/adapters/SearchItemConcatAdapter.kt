package com.crexative.spotifyclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crexative.spotifyclone.core.BaseConcatHolder
import com.crexative.spotifyclone.core.BaseRecyclerAdapter
import com.crexative.spotifyclone.databinding.SearchItemBinding

class SearchItemConcatAdapter(
    private val categoryTitle: String,
    private val adapter: BaseRecyclerAdapter<*>
) : RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSearchHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemSearchHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ItemSearchHolder -> holder.bind(adapter)
        }
    }

    override fun getItemCount(): Int = 1

    inner class ItemSearchHolder(private val binding: SearchItemBinding) :
        BaseConcatHolder<BaseRecyclerAdapter<*>>(binding.root) {

        override fun bind(adapter: BaseRecyclerAdapter<*>) = with(binding) {
            tvTitle.text = categoryTitle
            rvItems.adapter = adapter
        }
    }
}