package ru.vood.grpc.demo.client.async

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class AsyncDemoClientApplication

fun main(args: Array<String>) {
    runApplication<AsyncDemoClientApplication>(*args)
}