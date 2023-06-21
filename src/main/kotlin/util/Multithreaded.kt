package util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

fun <T> Iterable<T>.filterMultithreaded(chunkSize: Int, predicate: (T) -> Boolean): List<List<T>> {
    return runBlocking(Dispatchers.Default) {
        chunked(chunkSize).map {
            async {
                it.filter(predicate)
            }
        }.awaitAll()
    }
}