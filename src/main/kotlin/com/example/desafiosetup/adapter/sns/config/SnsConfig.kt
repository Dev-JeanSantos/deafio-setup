package com.example.desafiosetup.adapter.sns.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
data class SnsConfig(val amazonSnsProperties: AmazonSnsProperties) {

    @Bean
    fun amazonSNSLocal(): AmazonSNS = defaultAmazonSNSClientBuilder()
        .withCredentials(amazonSnsProperties.credentialProvider())
        .withEndpointConfiguration(amazonSnsProperties.enpointConfiguration())
        .build()
}
    private fun defaultAmazonSNSClientBuilder() = AmazonSNSClientBuilder.standard()

@ConstructorBinding
@Configuration
data class AmazonSnsProperties(
    val endpoint: String = "http://localhost:4566",
    val region: String = "us-east-1",
    val accessKey: String = "jean",
    val secretKey: String = "jean",
    val arn: String = ""
){

    fun credentialProvider() = AWSStaticCredentialsProvider(BasicAWSCredentials(this.accessKey, this.secretKey))

    fun enpointConfiguration() = AwsClientBuilder.EndpointConfiguration(this.endpoint, this.region)

}
