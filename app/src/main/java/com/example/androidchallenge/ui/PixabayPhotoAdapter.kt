package com.example.androidchallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.androidchallenge.R
import com.example.androidchallenge.data.PixabayPhoto
import com.example.androidchallenge.databinding.ItemPixabayPhotoBinding

class PixabayPhotoAdapter (private val listener: OnItemClickListener) : PagingDataAdapter<PixabayPhoto, PixabayPhotoAdapter.PhotoViewHolder>(
    PHOTO_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemPixabayPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPixabayPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if(item!=null){
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: PixabayPhoto) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.imageURL)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                textViewUsername.text = photo.user
                textViewTags.text = photo.tags
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClick(photo: PixabayPhoto)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PixabayPhoto>() {
            override fun areItemsTheSame(oldItem: PixabayPhoto, newItem: PixabayPhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PixabayPhoto, newItem: PixabayPhoto) =
                oldItem == newItem
        }
    }
}