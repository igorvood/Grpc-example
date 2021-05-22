package ru.vood.grpc.demo.server.service

import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import ru.vood.grpc.demo.api.v1.SomeDataRq
import ru.vood.grpc.demo.api.v1.SomeDataRs
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.server.service.util.genRndString
import ru.vood.grpc.demo.server.service.util.genSomeDataRs

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
        logger.info("reqWithStream Input request \n $request")
        IntRange(1,3).forEach {
            logger.info("before send $it")
            responseObserver.onNext(genSomeDataRs(19))
            logger.info("after send $it")
        }
        responseObserver.onCompleted()
        logger.info("Complit send")
    }

}