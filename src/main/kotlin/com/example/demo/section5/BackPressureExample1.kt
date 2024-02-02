package com.example.demo.section5

import com.example.demo.utils.Logger
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

fun main(){

    Flux.range(1, 5)
        .doOnRequest(Logger::doOnRequest)
        .doOnNext(Logger::doOnNext)
        .subscribe(object : BaseSubscriber<Int>(){

            // 구독 시점에 1개 데이터 요청
            override fun hookOnSubscribe(subscription: Subscription) {
                request(1)
            }

            // data emit 이후, 데이터 처리가 끝나면 1개 데이터 요청
            override fun hookOnNext(value: Int) {
                Thread.sleep(2000)
                Logger.onNext(value)
                request(1)
            }
        })



}