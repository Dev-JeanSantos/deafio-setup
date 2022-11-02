package com.example.desafiosetup.adapter.output.dynamodb.helper

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.example.desafiosetup.adapter.output.dynamodb.helper.RepositoryDynamoDBHelper.Companion.GSIEntity

class RepositoryDynamoDBHelper {

    companion object {
        const val GSI = "DataIndex"
        const val GSIEntity = "Sk-EntityType-index"

        fun <T> construindoExpressao(entidade: T): DynamoDBQueryExpression<T> =
            DynamoDBQueryExpression<T>()
                .withHashKeyValues(entidade)
                .withConsistentRead(true)

        fun <T> construindoExpressaoGSI(

            expressao: String,
            valorMapp: Map<String, AttributeValue>

        ): DynamoDBQueryExpression<T> =
            DynamoDBGSIQueryExpression<T>()
                .withKeyConditionExpression(expressao)
                .withFilterExpression("Visible = :visible")
                .withConsistentRead(false)
    }
}
class DynamoDBGSIQueryExpression<T> : DynamoDBQueryExpression<T>() {
    init{
        super.withIndexName(GSIEntity)
        super.withConsistentRead(false)
    }

}