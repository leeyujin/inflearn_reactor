package com.example.demo.section7

import com.example.demo.utils.Logger
import com.example.demo.utils.Logger.Companion
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * subscribeOn()은 최상위 Upstream Publisher가 데이터를 emit하기 위한 쓰레드를 지정한다
 * 즉, 원본 Publisher의 실행 쓰레드를 subscribeOn()에서 지정한 쓰레드로 지정한다.
 */
fun main() {

    // 최상위 Uptream Publisher = fromArray
    Flux.fromArray( arrayOf(1,3,5,7) )
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext { data -> Companion.doOnNext("fromArray", data) }
        .filter{ data -> data > 3 }
        .doOnNext { data -> Companion.doOnNext("filter", data) }
        .map { data -> data * 10 }
        .doOnNext { data -> Companion.doOnNext("map", data) }
        .subscribe (Logger::onNext)

    Thread.sleep(500L)
}