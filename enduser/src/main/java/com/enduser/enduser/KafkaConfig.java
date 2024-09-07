package com.enduser.enduser;


import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {
    //Kafka listens to the topic
    @KafkaListener(topics = AppConstants.LOCATION_UPDATE_TOPIC, groupId = AppConstants.GROUP_ID)
    public void updatedLocation(String value){
        //now that you have received the data from producer
        //you can choose to store it in your db or anything you want
        System.out.println(value);
    }
}
