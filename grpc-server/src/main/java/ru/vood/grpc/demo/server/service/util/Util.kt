package ru.vood.grpc.demo.server.service.util

import org.apache.commons.lang3.RandomStringUtils
import ru.vood.grpc.demo.api.v1.SomeDataRs

//специально так применил, знаю можно проще
fun genRndString(): (Int) -> String = { RandomStringUtils.randomAlphanumeric(it) }

fun genSomeDataRs(fnum: Int, f: (Int) -> String = genRndString()): SomeDataRs =
        SomeDataRs.newBuilder().setReqId(f.invoke(fnum)).build()