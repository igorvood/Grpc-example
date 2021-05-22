package ru.vood.grpc.demo.client.service.util

import com.google.protobuf.Timestamp
import ru.vood.grpc.demo.api.v1.SomeDataRq
import ru.vood.grpc.demo.api.v1.Type1
import kotlin.random.Random

//специально так применил, знаю можно проще
fun genSomeDataRq(): (String) -> SomeDataRq = {
    SomeDataRq.newBuilder()
            .setReqId(it)
            .setStatus(Random(100).nextInt())
            .setTime(Timestamp.getDefaultInstance())
            .setType(Type1.OK)
            .putAllIndexMap(mapOf("q" to "Q"))
            .addAllSomeList(listOf("q"))
            .build()
}