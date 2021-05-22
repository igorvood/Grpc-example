package ru.vood.grpc.demo.server.service

import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import ru.vood.grpc.demo.api.v1.SomeDataRq
import ru.vood.grpc.demo.api.v1.SomeDataRs
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc
import ru.vood.grpc.demo.server.service.util.genRndString

@GrpcService
class GrpcServerService : SomeServiceGrpc.SomeServiceImplBase() {

    val logger = LoggerFactory.getLogger(GrpcServerService::class.java)

    override fun reqWithEmpty(request: SomeDataRq, responseObserver: StreamObserver<Empty>) {
        logger.info("reqWithEmpty Input request $request")

        responseObserver.onNext(Empty.newBuilder().build())
        responseObserver.onCompleted()
    }

    override fun reqWithOne(request: SomeDataRq, responseObserver: StreamObserver<SomeDataRs>) {
        val randomString = genRndString().invoke(10)

        logger.info("reqWithOne Input request $request")
        responseObserver.onNext(SomeDataRs.newBuilder().setReqId(randomString).build())
    }

    override fun reqWithStream(request: SomeDataRq, responseObserver: StreamObserver<SomeDataRs>) {
        logger.info("reqWithStream Input request $request")
        responseObserver.onNext(SomeDataRs.newBuilder().build())
        responseObserver.onNext(SomeDataRs.newBuilder().build())
        responseObserver.onNext(SomeDataRs.newBuilder().build())
        responseObserver.onCompleted()
    }
}