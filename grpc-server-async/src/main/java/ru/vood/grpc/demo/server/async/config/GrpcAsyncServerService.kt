package ru.vood.grpc.demo.server.async.config

import com.google.protobuf.Empty
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import ru.vood.grpc.demo.api.v1.SomeDataRq
import ru.vood.grpc.demo.api.v1.SomeDataRs
import ru.vood.grpc.demo.api.v1.SomeServiceImplBase

@GrpcService
class GrpcAsyncServerService : SomeServiceImplBase(){

    val logger = LoggerFactory.getLogger(GrpcAsyncServerService::class.java)
    override suspend fun reqWithEmpty(request: SomeDataRq): Empty {
        logger.info("reqWithEmpty Input request \n $request")

        return Empty.newBuilder().build()
    }

    override suspend fun reqWithOne(request: SomeDataRq): SomeDataRs {
        logger.info("reqWithOne Input request \n $request")
        delay(10000)
        logger.info("reqWithOne out request \n $request")
        return genSomeDataRs(19)
    }

    override fun reqWithStream(request: SomeDataRq): ReceiveChannel<SomeDataRs> {
        logger.info("reqWithStream Input request \n $request")
        return super.reqWithStream(request)
    }

    override suspend fun reqWithError(request: SomeDataRq): Empty {
        return super.reqWithError(request)
    }

    override suspend fun reqWithNotCatchedError(request: SomeDataRq): Empty {
        return super.reqWithNotCatchedError(request)
    }

    override suspend fun reqWithNoReturn(request: SomeDataRq): Empty {
        return super.reqWithNoReturn(request)
    }
}