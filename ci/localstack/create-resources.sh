export DEFAULT_REGION=us-east-1
export AWS_DEFAULT_REGION=us-east-1
#!/bin/bash
echo "[INFO] - DESAFIO - SETUP LOCALSTACK"

## SNS

echo "[INFO] - CRIAÇÃO SNS"
awslocal --no-verify-ssl sns create-topic \
  --region=us-east-1 \
  --name transfer-completed-SNS

echo "[INFO] - CRIAÇÃO SQS"
awslocal --no-verify-ssl sqs create-queue \
  --region=us-east-1 \
  --queue-name data-sqs

echo "[INFO] - CRIAÇÃO SUBSCRIBE"
awslocal --no-verify-ssl sns subscribe \
  --region=us-east-1 \
  --topic-arn arn:aws:sns:us-east-1:000000000000:transfer-completed-SNS \
  --protocol sqs \
  --notification-endpoint arn:aws:sqs:us-east-1:000000000000:data-sqs \
  --attributes '{"RawMessageDelivery": "true"}'

echo "[INFO] - CRIAÇÃO DO DYNAMODB"
aws --endpoint-url http://localhost:4566 --region us-east-1 dynamodb create-table --cli-input-json file:///files/dynamo-table.json
