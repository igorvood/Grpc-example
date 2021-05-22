package ru.vood.grpc.demo.server.service

import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import ru.vood.grpc.demo.api.v1.SomeDataRq
import ru.vood.grpc.demo.api.v1.SomeDataRs
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.server.service.util.genSomeDataRs
import java.lang.IllegalStateException
import kotlin.random.Random

@GrpcService
class GrpcServerService : SomeServiceGrpc.SomeServiceImplBase() {

    val logger = LoggerFactory.getLogger(GrpcServerService::class.java)

    override fun reqWithEmpty(request: SomeDataRq, responseObserver: StreamObserver<Empty>) {
        logger.info("reqWithEmpty Input request \n $request")

        responseObserver.onNext(Empty.newBuilder().build())
        responseObserver.onCompleted()
    }

    override fun reqWithOne(request: SomeDataRq, responseObserver: StreamObserver<SomeDataRs>) {
        logger.info("reqWithOne Input request \n $request")
        responseObserver.onNext(genSomeDataRs(19))
        responseObserver.onCompleted()
    }

    override fun reqWithStream(request: SomeDataRq, responseObserver: StreamObserver<SomeDataRs>) {
        val endInclusive = (5 until 10).random()
        logger.info("reqWithStream Input request repeat $endInclusive for \n $request")

        IntRange(1, endInclusive).forEach {
            logger.info("before send $it")
            if (it % 2 == 0) Thread.sleep(10000L)
            responseObserver.onNext(genSomeDataRs(19))
            logger.info("after send $it")
        }
        responseObserver.onCompleted()
        logger.info("Complit send")
    }

    override fun reqWithError(request: SomeDataRq, responseObserver: StreamObserver<Empty>) {
        logger.info("reqWithError Input request repeat for \n $request")
        responseObserver.onError(IllegalStateException("Error on the server side"))
    }
}