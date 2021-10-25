package com.example.androidchallenge.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PixabayPhoto(
    val id: String,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String,
    val fullHDURL: String,
    val imageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user_id: String,
    val userImageURL: String,
    val user: String,
    val userImageUrl: String,
) : Parcelable {
}