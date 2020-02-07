const AWS = require('aws-sdk');
let sqs = new AWS.SQS();

const SQS_URL = '<specify-your-queue-url-here>';

exports.handler = async (event, context) => {

    debugger;
    let message = event['message'] || "No message found";

    console.log("Publishing message", message, "to SQS queue", SQS_URL);
    try {
        let result = await sqs.sendMessage({
            MessageBody: message,
            QueueUrl: SQS_URL
        }).promise();
        console.log("Message sent successfully", result);
        return `Message sent successfully with ID: ${result.MessageId}`;

    } catch (e) {
        console.log("SQS message publishing failed", e);
        throw new Error("Failed message sending");
    }
};

require('slappforge-lambda-debug-proxy');
