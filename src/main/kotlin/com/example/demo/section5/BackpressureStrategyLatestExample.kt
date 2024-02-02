package com.example.demo.section5

import com.example.demo.utils.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration


/**
 *  - DownStream 버퍼가 가득 찬 경우
 *    버퍼가 70~80% 빌 때 까지, 가장 최근(나중)에 인입된 데이터를 버퍼에 넣는 전략
 *
 *    - DROP 방식과의 차이 :
 *      - DROP : 버퍼가 가득찼을 때 데이터가 emit되면 그 즉시 drop
 *      - LATEST : 버퍼가 가득찼을 때 데이터가 emit되면, 다음 데이터가 들어왔을때 이전 데이터 폐기
 */

fun main(){

    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureLatest()
        .publishOn(Schedulers.parallel())
        .subscribe(
            { data ->
                Thread.sleep(5L)
                // 0~255 까지 찍힌 후, 버퍼가 가득참
                // 버퍼가 비워진 시점부터 1312~ 인입
                Logger.onNext(data)
            } ,
            { error ->
                Logger.onError(error)
            }
        )

    Thread.sleep(2000L)

}