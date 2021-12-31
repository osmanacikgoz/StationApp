package com.osmanacikgoz.stationsapp.base

import android.content.res.AssetManager
import java.nio.charset.Charset

fun AssetManager.readJson(jsonFileName: String): String {
    val charset: Charset = Charsets.UTF_8
    val positionAsset = this.open(jsonFileName)
    val size = positionAsset.available()
    val buffer = ByteArray(size)
    positionAsset.read(buffer)
    positionAsset.close()
    return String(buffer, charset)
}