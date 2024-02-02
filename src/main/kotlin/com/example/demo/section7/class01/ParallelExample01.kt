package com.example.demo.section7.class01

import com.example.demo.utils.Logger
import reactor.core.publisher.Flux

/**
 * parallel()만 사용 시 병렬 작업 수행 X
 */
fun main() {
    // 돌려보면 전부 main 스레드에서만 수행됨
    Flux.fromArray(arrayOf(1, 3, 5, 7, 9, 11, 13, 15))
        .parallel()
        .subscribe(Logger::onNext)
}