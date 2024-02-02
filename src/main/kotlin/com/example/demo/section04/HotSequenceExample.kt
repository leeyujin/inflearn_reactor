package com.example.demo.section04

import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

fun main() {

    val concertFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
        .delayElements(Duration.ofSeconds(1))
        .share() // 원본 Flux를 여러 Subscriber가 공유한다 ( cold를 hot으로 만들어줌 ) , 미작성 시 stream has already been operated upon or closed 에러 발생

    concertFlux.subscribe{ singer -> println("# Subscriber1 is watching ${singer}s song")  }

    Thread.sleep(2500)

    concertFlux.subscribe{ singer -> println("# Subscriber2 is watching ${singer}s song")  }

    Thread.sleep(3000)


}