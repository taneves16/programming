package com.jpmorgan.messagingapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.jpmorgan.messagingapplication.MessageProcesser;
import com.jpmorgan.messagingapplication.entities.Sale;
import com.jpmorgan.messagingapplication.messagetype.MessagingType;

public class LinkedBlockingQueueConsumer implements Runnable{

    protected LinkedBlockingQueue<MessagingType> blockingQueue;
    HashMap<String, ArrayList<Sale>> productSales;

    public LinkedBlockingQueueConsumer(HashMap<String, ArrayList<Sale>> productSales, LinkedBlockingQueue<MessagingType> queue) {
        this.blockingQueue = queue;
        this.productSales = productSales;
    }

    @Override
    public void run() {
        while (true) {
            try {
                while(!blockingQueue.isEmpty()){
                    MessagingType msg = blockingQueue.take();
                    MessageProcesser mp = new MessageProcesser();
                    mp.processMessage(productSales,msg);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}