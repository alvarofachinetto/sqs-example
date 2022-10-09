package com.bank.sqsexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration

@SpringBootApplication(exclude = [ContextStackAutoConfiguration::class])
class SqsExampleApplication

fun main(args: Array<String>) {
	runApplication<SqsExampleApplication>(*args)
}

