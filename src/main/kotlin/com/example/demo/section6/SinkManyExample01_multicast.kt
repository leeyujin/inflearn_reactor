package com.example.demo.section6

import com.example.demo.utils.Logger.Companion
import reactor.core.publisher.Sinks

fun main() {
    // unicast = 단 하나의 subscriber에게만 데이터를 emit 할 수 있다
    val unicastSink = Sinks.many().multicast().onBackpressureBuffer<Int>()
    val fluxView = unicastSink.asFlux()

    unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST)
    unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST)

    fluxView.subscribe{data -> Companion.onNext("Subscriber1", data) }

    // 1, 2 전달받지 않음. multicast - 핫시퀀스 , 첫번째 구독이 발생하는 시점에 데이터 emit이 시작되는 웜업 방식으로 동작
    fluxView.subscribe{data -> Companion.onNext("Subscriber2", data) }

    unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST)

}