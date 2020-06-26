import logging
import boto3
sqs = boto3.client("sqs")

SQS_URL = "<specify-your-queue-url-here>"

def handler(event, context):
    message = event.get("message", "No message found")
    logging.info("Publishing message", message, "to SQS queue", SQS_URL)
    try:
        result = sqs.send_message(
            MessageBody=message,
            QueueUrl=SQS_URL
        )
        logging.info("Message sent successfully", result)
        return "Message sent successfully with ID: %s" % result["MessageId"]
    except BaseException as e:
        logging.error("SQS message publishing failed: %s", e)
        raise(e)