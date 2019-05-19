package com.mindvalley.assignment.downloader

class FetchData(url: String) : Downloader<String>(url) {
    //converts ByteArray to string
    override fun transform(content: DownloadableContent): String {
        return content.toString(Charsets.UTF_8)
    }
}