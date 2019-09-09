package com.fanreact.shoppingcartpricecalculator.utilities

import android.util.LruCache

open class BaseCache<T: BaseDatum>(cacheSize: Int = 8 * 1024 * 1024) {
    private val cache = LruCache<String, T>(cacheSize)

    open fun put(purchase: T) {
        synchronized(this) {
            if (cache.get(purchase.id) == null) {
                cache.put(purchase.id, purchase)
            }
        }
    }

    fun get(id: String) = cache.get(id)

    fun remove(id: String) {
        synchronized(cache) {
            if (cache.get(id) != null) {
                cache.remove(id)
            }
        }
    }

    fun values() = cache.snapshot().values.toList()

    fun clear() = cache.evictAll()
}