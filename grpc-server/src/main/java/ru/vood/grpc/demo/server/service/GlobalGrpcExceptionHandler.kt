package ru.vood.grpc.demo.server.service

import io.grpc.*
import io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor
import org.slf4j.LoggerFactory

@GrpcGlobalServerInterceptor
class GlobalGrpcExceptionHandler : ServerInterceptor {
    val logger = LoggerFactory.getLogger(GlobalGrpcExceptionHandler::class.java)

    override fun <ReqT : Any?, RespT : Any?> interceptCall(call: ServerCall<ReqT, RespT>, reqHeders: Metadata, next: ServerCallHandler<ReqT, RespT>): ServerCall.Listener<ReqT> {
        logger.info("Calling ${call.methodDescriptor.fullMethodName} parameters ${call.attributes}")
        val delegate = next.startCall(call, reqHeders)

        return object : SimpleForwardingServerCallListener<ReqT>(delegate) {
            override fun onHalfClose() {
                try {
                    super.onHalfClose()
                } catch (e: Throwable) {
                    logger.error("Some error ", e)
                    call.close(Status.INTERNAL
                            .withCause(e)
                            .withDescription("Some error ")
                            , reqHeders)
                }

            }
        }
    }
}