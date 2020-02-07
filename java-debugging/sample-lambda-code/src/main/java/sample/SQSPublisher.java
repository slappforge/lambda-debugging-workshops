package sample;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class SQSPublisher {

    final String SQS_URL = "asankha-java-demo";

    public String handleRequest(String input, Context context) {

        String message = input == null ? "No message found" : input;

        System.out.println("Publishing message -> " + message + " <- to SQS queue : " + SQS_URL);
        try {
            final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

            SendMessageRequest send_msg_request = new SendMessageRequest()
                    .withQueueUrl(SQS_URL)
                    .withMessageBody(message)
                    .withDelaySeconds(5);
            SendMessageResult result = sqs.sendMessage(send_msg_request);
            System.out.println("Message sent successfully : " + result);
            return "Message sent successfully with ID: " + result.getMessageId();

        } catch (Exception e) {
            System.out.println("SQS message publishing failed for Queue : " + SQS_URL + " with : " + e.getMessage());
            return "Failed message sending";
        }
    }
}