package com.example.demo.runner;

import com.example.demo.dto.StockPrice;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
/**
 * Created by yl1794 on 2018/4/27.
 */
// 订阅者
public class Client implements MessageListener<StockPrice>{

    public Client(String topicName) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ITopic<StockPrice> topic = hzInstance.getTopic(topicName);
        topic.addMessageListener(this);
    }

    /**
     * @see com.hazelcast.core.MessageListener#onMessage(com.hazelcast.core.Message)
     */
    @Override
    public void onMessage(Message<StockPrice> arg0) {
        System.out.println("Received: " + arg0.getMessageObject().toString());
    }

    public static void main(String[] args) {

        new Client("STOCKS");
    }

}