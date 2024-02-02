package com.example.demo.section8

import com.example.demo.utils.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context
import reactor.util.context.ContextView

/**
 * Context 개념 예제 코드
 * - contextWrite()으로 Context에 값을 쓸 수 있고, ContextView.get()을 통해 Context에 저장된 값을  read 할 수 있다.
 * - ContxtView는 deferContextual() 또는 transformDeferredContextual()을 통해 제공된다.
 */
fun main() {

    val key = "message"


    val mono = Mono
        .deferContextual { ctx: ContextView ->
            // Hello Reactor
            Mono.just("Hello " + ctx.get(key)).doOnNext(Logger::doOnNext)
        }
        .subscribeOn(Schedulers.boundedElastic())
        .publishOn(Schedulers.parallel())
        .transformDeferredContextual {
                mono2: Mono<String>, ctx: ContextView ->
            mono2.map { data -> data + " " + ctx.get(key) }
        }.contextWrite { context: Context ->
            context.put(key, "Reactor")
        }

    // Hello Reactor Reactor
    mono.subscribe(Logger::doOnNext)

    Thread.sleep(1000)

}