package com.example.desafiosetup.aplicacao.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class DynamoDBAutoConfiguration {

    @Value("\${amazon.dynamodb.endpoint:http://localhost:4566}")
    private lateinit var amazonDynamoDBEndpoint: String

    @Value("\${amazon.region:us-east-1}")
    private lateinit var amazonRegion: String

    @Value("\${amazon.aws.accesskey:desafio}")
    private lateinit var amazonAWSAccessKey: String

    @Value("\${amazon.aws.secretkey:desafio}")
    private lateinit var amazonAWSSecretKey: String

    @Bean
    fun mapper(amazonDynamoDb: AmazonDynamoDB) = DynamoDBMapper(amazonDynamoDb)

    @Bean
    @Profile("!production")
    fun amazonDynamoDBLocal(): AmazonDynamoDB = defaulAmazonDynamoDBClienteBuilder()
        .withCredentials(credentialsProvider())
        .withEndpointConfiguration(endpointConfiguration())
        .build()

    @Bean
    @Profile("production")
    fun amazonDynamoDBProduction(): AmazonDynamoDB = defaulAmazonDynamoDBClienteBuilder().build()

    private fun credentialsProvider() = AWSStaticCredentialsProvider(
            BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey)
    )
    private fun endpointConfiguration() = EndpointConfiguration(amazonDynamoDBEndpoint, amazonRegion)
    private fun defaulAmazonDynamoDBClienteBuilder() = AmazonDynamoDBAsyncClientBuilder.standard()
}