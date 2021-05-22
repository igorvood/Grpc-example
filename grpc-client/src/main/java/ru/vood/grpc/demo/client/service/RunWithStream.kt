package ru.vood.grpc.demo.client.service

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.client.service.util.genSomeDataRq

@Service
@Order(30)
class RunWithStream(val someGrpcService: SomeServiceGrpc.SomeServiceBlockingStub) : RunInterface {

    val logger = LoggerFactory.getLogger(RunWithStream::class.java)

    override fun run() {
        logger.info("run")
        val reqWithEmpty = someGrpcService.reqWithStream(genSomeDataRq().invoke(RunWithStream::class.java.toString()))
        logger.info("response fro server")
        var i = 0
        while (reqWithEmpty.hasNext()) {
            logger.info("reqWithStream \n ${reqWithEmpty.next()}")
//            Thread.sleep(10L)
            i++
        }
        logger.info("end run. Total Count $i")
    }
}