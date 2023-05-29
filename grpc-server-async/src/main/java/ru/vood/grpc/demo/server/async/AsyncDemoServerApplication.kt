package ru.vood.grpc.demo.server.async

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
open class AsyncDemoServerApplication

fun main(args: Array<String>) {
    runApplication<AsyncDemoServerApplication>(*args)
}