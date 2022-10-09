package com.bank.sqsexample

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class QueueConfiguration {

    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String;

    @Value("\${cloud.aws.credentials.access-key}")
    private lateinit var accessKey: String;

    @Value("\${cloud.aws.credentials.secret-key}")
    private lateinit var secretKey: String;


    @Bean
    fun queueMessageTemplate(): QueueMessagingTemplate{
        return QueueMessagingTemplate(amazonSqsAsync())
    }

    @Primary
    @Bean
    fun amazonSqsAsync(): AmazonSQSAsync? {
        return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()
    }

}

