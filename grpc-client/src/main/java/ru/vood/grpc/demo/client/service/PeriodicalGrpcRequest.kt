package ru.vood.grpc.demo.client.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.client.service.util.genSomeDataRq
import java.util.concurrent.ExecutorService

@Service
class PeriodicalGrpcRequest(
        private val someGrpcService: SomeServiceGrpc.SomeServiceBlockingStub,
        val executorService: ExecutorService
) {

    val logger = LoggerFactory.getLogger(PeriodicalGrpcRequest::class.java)

    var cnt = 0

    @Scheduled(fixedDelay = 100)
    fun periodicalRun() {
        if (cnt == 0) {
            cnt++
            Thread.sleep(20000)
        }

        executorService.submit {
            logger.info("run")
            someGrpcService.reqWithNoReturn(genSomeDataRq().invoke(PeriodicalGrpcRequest::class.java.toString()))
            logger.info("End")
        }
    }

}