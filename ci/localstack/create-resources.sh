export DEFAULT_REGION=us-east-1
export AWS_DEFAULT_REGION=us-east-1

#!/bin/bash

echo "[INFO] - DESAFIO - SETUP LOCALSTACK"

## SNS
awslocal --no-verify-ssl sns create-topic \
  --region=us-east-1 \
  --name data-ingestion

awslocal --no-verify-ssl sqs create-queue \
  --region=us-east-1 \
  --queue-name FakeQueue

awslocal --no-verify-ssl sns subscribe \
  --region=us-east-1 \
  --topic-arn arn:aws:sns:us-east-1:000000000000:data-ingestion \
  --protocol sqs \
  --notification-endpoint arn:aws:sqs:us-east-1:000000000000:FakeQueue \
  --attributes '{"RawMessageDelivery": "true"}'