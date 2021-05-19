package ru.vood.grpc.demo.client

import org.springframework.boot.SpringApplication

object DemoClientApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(DemoClientApplication::class.java, *args)
    }
}