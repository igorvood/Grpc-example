package ru.vood.grpc.demo.client.config

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc.SomeServiceBlockingStub

@Configuration
open class GrpcCallConfig {

    @Bean
    @GrpcClient("srv")
    open fun someServiceBlockingStub(someServiceBlockingStub: SomeServiceBlockingStub): SomeServiceBlockingStub = someServiceBlockingStub

}