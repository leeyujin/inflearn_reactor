package com.example.demo.utils
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class Logger {


    companion object {
        val logger = LoggerFactory.getLogger(Logger::class.java)
        fun info(data: Any?) {

            logger.info("{}", data)
        }

        fun info(msg: String?, data: Any?) {
            logger.info(msg, data)
        }

        fun doOnNext(data: Any?) {
            logger.info("# doOnNext(): {}", data)
        }

        fun doOnNext(operator: String?, data: Any?) {
            logger.info("# doOnNext() {}: {}", operator, data)
        }

        fun doOnNext(taskName: String?, operator: String?, data: Any?) {
            logger.info("# doOnNext() {} {}: {}", taskName, operator, data)
        }

        fun doOnRequest(data: Any?) {
            logger.info("# doOnRequest(): {}", data)
        }

        fun onNext(data: Any?) {
            logger.info("# onNext(): {}", data)
        }

        fun onNext(data1: Any?, data2: Any?) {
            logger.info("# onNext(): {} : {}", data1, data2)
        }

        fun onError(error: Throwable?) {
            logger.error("error happened: ", error)
        }

        fun onComplete() {
            logger.error("# onComplete()")
        }

        fun onComplete(data: Any?) {
            logger.info("# onComplete(): {}", data)
        }

        fun onNext(message: String?, data: Any?) {
            logger.info("# {} onNext(): {}", message, data)
        }

        fun filter(data: Any?) {
            logger.info("# filter(): {}", data)
        }

        fun map(data: Any?) {
            logger.info("# map(): {}", data)
        }
    }
}