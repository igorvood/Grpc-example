package ru.vood.grpc.demo.client.service

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.client.service.util.genSomeDataRq

@Service
@Order(1)
class RunOne(val someGrpcService: SomeServiceGrpc.SomeServiceBlockingStub) : RunInterface {

    val logger = LoggerFactory.getLogger(RunOne::class.java)

    override fun run() {
        val reqWithEmpty = someGrpcService.reqWithEmpty(genSomeDataRq().invoke(RunOne::class.java.toString()))
        logger.info("reqWithEmpty = $reqWithEmpty")
    }
}