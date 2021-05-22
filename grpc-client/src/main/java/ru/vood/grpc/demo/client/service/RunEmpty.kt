package ru.vood.grpc.demo.client.service

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.client.service.util.genSomeDataRq

@Service
@Order(10)
class RunEmpty(val someGrpcService: SomeServiceGrpc.SomeServiceBlockingStub) : RunInterface {

    val logger = LoggerFactory.getLogger(RunEmpty::class.java)

    override fun run() {
        logger.info("run")
        val reqWithEmpty = someGrpcService.reqWithEmpty(genSomeDataRq().invoke(RunEmpty::class.java.toString()))
        logger.info("reqWithEmpty \n $reqWithEmpty")
    }
}