package com.example.demo.section7.class01

import com.example.demo.utils.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * parallel()만 사용 시 병렬 작업 수행 X
 * -> runOn()을 사용해서 Scheduelr를 할당해주어야 병렬로 작업을 수행한다
 * -> 논리적인 코어 개수 범위 안에서 , worker thread 수 강제 할당 가능
 */
fun main() {
    Flux.fromArray(arrayOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 35))
        // 스레드 4개 강제 할당
        .parallel(4)
        .runOn(Schedulers.parallel())
        .subscribe(Logger::onNext)

    Thread.sleep(100L)
}