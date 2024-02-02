package com.example.demo.section7.class03

import com.example.demo.utils.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

fun main() {

    val mono = Mono.just(1)
        .subscribeOn(Schedulers.newParallel("Parallel Thread", 4, true))


    mono.subscribe { data ->
        Thread.sleep(5000)
        Logger.onNext("subscribe 1 $data")
    }

    mono.subscribe { data ->
        Thread.sleep(4000)
        Logger.onNext("subscribe 4 $data")
    }

    mono.subscribe { data ->
        Thread.sleep(3000)
        Logger.onNext("subscribe 3 $data")
    }

    mono.subscribe { data ->
        Thread.sleep(2000)
        Logger.onNext("subscribe 2 $data")
    }

    Thread.sleep(6000)
}