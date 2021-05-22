package ru.vood.grpc.demo.client.service

import io.grpc.StatusRuntimeException
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.client.service.util.genSomeDataRq

@Service
@Order(40)
class RunError(val someGrpcService: SomeServiceGrpc.SomeServiceBlockingStub) : RunInterface {

    val logger = LoggerFactory.getLogger(RunError::class.java)

    override fun run() {
        logger.info("run")
        try {
            val reqWithEmpty = someGrpcService.reqWithError(genSomeDataRq().invoke(RunError::class.java.toString()))
            error("Error Must here")
        } catch (t: StatusRuntimeException) {

            logger.info("reqWithError ${t}")
        }


    }
}