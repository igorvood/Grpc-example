package ru.vood.grpc.demo.server.service

import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import ru.vood.grpc.demo.api.v1.SomeDataRq
import ru.vood.grpc.demo.api.v1.SomeDataRs
import ru.vood.grpc.demo.api.v1.SomeServiceGrpc

@GrpcService
class GrpcService : SomeServiceGrpc.SomeServiceImplBase() {

    val logger = LoggerFactory.getLogger(GrpcService::class.java)

    override fun reqWithEmpty(request: SomeDataRq, responseObserver: StreamObserver<Empty>) {
        logger.info(" Input request $request")

        responseObserver.onNext(Empty.newBuilder().build())
        responseObserver.onCompleted()
    }

    override fun reqWithOne(request: SomeDataRq, responseObserver: StreamObserver<SomeDataRs>) {
        responseObserver.onNext(SomeDataRs.newBuilder().build())
    }

    override fun reqWithStream(request: SomeDataRq, responseObserver: StreamObserver<SomeDataRs>) {
        responseObserver.onNext(SomeDataRs.newBuilder().build())
        responseObserver.onNext(SomeDataRs.newBuilder().build())
        responseObserver.onNext(SomeDataRs.newBuilder().build())
        responseObserver.onCompleted()
    }
}