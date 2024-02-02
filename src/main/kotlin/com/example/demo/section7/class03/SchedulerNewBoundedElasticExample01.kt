package com.example.demo.section7.class03

import com.example.demo.utils.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

fun main() {
    // 2개의 스레드, 각 스레드 내 size 2 Queue
    val newBoundedElastic = Schedulers.newBoundedElastic(2, 2, "I/O-thread")
    val mono = Mono.just(1)
        .subscribeOn(newBoundedElastic)

    mono.subscribe{data ->
        Logger.onNext("subscribe 1 doing ${data}")
        Thread.sleep(3000L)
        Logger.onNext("subscribe 1 done ${data}")
    }

    mono.subscribe{data ->
        Logger.onNext("subscribe 2 doing ${data}")
        Thread.sleep(3000L)
        Logger.onNext("subscribe 2 done ${data}")
    }

    mono.subscribe{data ->
        Logger.onNext("subscribe 3 doing ${data}")
    }

    mono.subscribe{data ->
        Logger.onNext("subscribe 4 doing ${data}")
    }

    mono.subscribe{data ->
        Logger.onNext("subscribe 5 doing ${data}")
    }

    mono.subscribe{data ->
        Logger.onNext("subscribe 6 doing ${data}")
    }
}