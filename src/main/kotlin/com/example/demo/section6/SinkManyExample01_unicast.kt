package com.example.demo.section6

import com.example.demo.utils.Logger.Companion
import reactor.core.publisher.Sinks

fun main() {
    // unicast = 단 하나의 subscriber에게만 데이터를 emit 할 수 있다
    val unicastSink = Sinks.many().unicast().onBackpressureBuffer<Int>()
    val fluxView = unicastSink.asFlux()

    unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST)
    unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST)

    fluxView.subscribe{data -> Companion.onNext("Subscriber1", data) }

    unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST)

    // Sinks.many().unicast() sinks only allow a single Subscriber
//    fluxView.subscribe{data -> Companion.onNext("Subscriber1", data) }
}