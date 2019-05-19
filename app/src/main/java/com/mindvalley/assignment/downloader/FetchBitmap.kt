package com.mindvalley.assignment.downloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class FetchBitmap(url: String) : Downloader<Bitmap>(url) {
    override fun transform(content: DownloadableContent): Bitmap {
        return BitmapFactory.decodeByteArray(content, 0, content.size)
    }
}