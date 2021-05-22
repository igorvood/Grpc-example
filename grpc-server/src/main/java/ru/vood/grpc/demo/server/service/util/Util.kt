package ru.vood.grpc.demo.server.service.util

import org.apache.commons.lang3.RandomStringUtils

fun genRndString(): (Int)-> String = { RandomStringUtils.randomAlphanumeric(it)}