package ru.vood.grpc.demo.server.async.config

import net.devh.boot.grpc.server.service.GrpcService
import ru.vood.grpc.demo.api.v1.SomeServiceImplBase

@GrpcService
class GrpcAsyncServerService : SomeServiceImplBase()