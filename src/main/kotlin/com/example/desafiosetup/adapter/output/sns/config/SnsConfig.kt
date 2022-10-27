package com.example.desafiosetup.adapter.output.sns.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SnsConfig(val amazonSnsProperties: AmazonSnsProperties) {

    @Bean
    fun amazonSNSLocal(): AmazonSNS =
        defaultAmazonSNSClient()
            .withCredentials(amazonSnsProperties.credentialProvider())
            .withEndpointConfiguration(amazonSnsProperties.credentialConfiguration())
            .build()

    private fun defaultAmazonSNSClient() = AmazonSNSClientBuilder.standard()

}

@ConstructorBinding
@ConfigurationProperties(prefix = "amazon.sns")
data class AmazonSnsProperties(
    val endpoint: String = "http://localhost:4575",
    val region: String = "us-east-1",
    val accessKey: String = "key",
    val secretKey: String = "secret",
    val arn: String = ""
) {
    fun credentialProvider() = AWSStaticCredentialsProvider(BasicAWSCredentials(this.accessKey, this.secretKey))

    fun credentialConfiguration() = AwsClientBuilder.EndpointConfiguration(this.accessKey, this.secretKey)
}

@Configuration
@EnableConfigurationProperties(AmazonSnsProperties::class)
class SNSAutoConfiguration