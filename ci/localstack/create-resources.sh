export DEFAULT_REGION=us-east-1
export AWS_DEFAULT_REGION=us-east-1

#!/bin/bash

echo "[INFO] - DESAFIO - SETUP LOCALSTACK"

## SNS

echo "[INFO] - CRIAÇÃO SNS"
awslocal --no-verify-ssl sns create-topic \
  --region=us-east-1 \
  --name data-ingestion

echo "[INFO] - CRIAÇÃO SQS"
awslocal --no-verify-ssl sqs create-queue \
  --region=us-east-1 \
  --queue-name FakeQueue

echo "[INFO] - CRIAÇÃO SUBSCRIBE"
awslocal --no-verify-ssl sns subscribe \
  --region=us-east-1 \
  --topic-arn arn:aws:sns:us-east-1:000000000000:data-ingestion \
  --protocol sqs \

echo "[INFO] - CRIAÇÃO DO DYNAMODB"
aws --endpoint-url http://localhost:4566 --region us-east-1 dynamodb create-table --cli-input-json file:///ci/localstack/files/dynamo-table.json
