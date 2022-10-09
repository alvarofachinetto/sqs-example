package com.bank.sqsexample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SqsSend(private val queueMessagingTemplate: QueueMessagingTemplate) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Value("\${cloud.aws.end-point.uri}")
    private lateinit var endpoint: String

    @GetMapping("/send/{message}")
    fun sendMessageToQueue(@PathVariable message: String){
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build())
    }

    @SqsListener("Sqs-Ex")
    fun consumeQueue(message: String){
        logger.info("Message from sqs service $message")
    }

}