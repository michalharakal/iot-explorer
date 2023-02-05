package org.plc4x.kmp.ui.platform


// for implementaion of FrameCLock for Desktop followe https://github.com/cashapp/redwood/blob/trunk/redwood-compose/src/jsMain/kotlin/app/cash/redwood/compose/WindowAnimationFrameClock.kt

/*
actual fun getPlatformMonotonicFrameClock() = WindowAnimationFrameClock


object WindowAnimationFrameClock : MonotonicFrameClock {
    override suspend fun <R> withFrameNanos(
        onFrame: (Long) -> R,
    ): R = suspendCoroutine { continuation: Continuation<R> ->
        val result = onFrame(100_000)
        continuation.resumeWith(result)
    }
}


 */