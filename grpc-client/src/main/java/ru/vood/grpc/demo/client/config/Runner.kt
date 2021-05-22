package ru.vood.grpc.demo.client.config

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import ru.vood.grpc.demo.client.service.RunInterface

@Service
class Runner(private val services: List<RunInterface>) : CommandLineRunner {
    val logger = LoggerFactory.getLogger(Runner::class.java)

    override fun run(vararg args: String?) {
        services.forEach {
            logger.info("=================== run ${it.javaClass}================================")
            it.run()
        }
    }
}