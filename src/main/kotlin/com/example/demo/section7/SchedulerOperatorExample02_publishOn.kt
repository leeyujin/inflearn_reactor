package com.example.demo.section7

import com.example.demo.utils.Logger
import com.example.demo.utils.Logger.Companion
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

fun main() {

    Flux.fromArray( arrayOf(1,3,5,7) )
        .doOnNext { data -> Companion.doOnNext("fromArray", data) }
        /**
         * publishOn() 호출 이후의 Operator 체인은
         * 다음 publishOn()을 만나기 전까지, 지정한 스레드에서 실행됨
         */
        .publishOn( Schedulers.parallel() )
        // (s) 별도스레드로
        .filter{ data -> data > 3 }
        .doOnNext { data -> Companion.doOnNext("filter", data) }
        .map { data -> data * 10 }
        .doOnNext { data -> Companion.doOnNext("map", data) }
        .subscribe (Logger::onNext)
        // (e) subcription까지

    Thread.sleep(500L)
}