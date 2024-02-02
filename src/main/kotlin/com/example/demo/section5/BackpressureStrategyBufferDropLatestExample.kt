package com.example.demo.section5

import com.example.demo.utils.Logger
import reactor.core.publisher.BufferOverflowStrategy
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

fun main()
{
    Flux
        .interval(Duration.ofMillis(300))
        .doOnNext {data -> Logger.info("# emitted by original Flux : $data")}
        .onBackpressureBuffer(
            2,
            {dropped -> Logger.info("# Overflow & dropped : $dropped") },
            BufferOverflowStrategy.DROP_LATEST
        )
        // buffer에서 subscriber로 emit하는 시점에 찍히는 로그. 해당 로그가 찍히면 버퍼가 비게되므로, 새로운 데이터가 인입됨
        .doOnNext {data -> Logger.info("# emitted by Buffer : $data")}
        .publishOn( Schedulers.parallel(), false, 1 ) // prefetch : 추가되는 스레드의 버퍼
        .subscribe( { data ->
            Thread.sleep(1000L)
            // 0,1,2 
            Logger.onNext(data)
        },
            { error ->
                Logger.onError(error)
            }
        )
    Thread.sleep(3000L)
}