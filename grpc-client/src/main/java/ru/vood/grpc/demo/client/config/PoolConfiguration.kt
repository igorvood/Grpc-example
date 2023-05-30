package ru.vood.grpc.demo.client.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Configuration
open class PoolConfiguration {

    @Bean
    open fun executorService(): ExecutorService = Executors.newFixedThreadPool(10000)
}