package com.bu.firstdoc;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * @author haizhuangbu
 * @date 9:45 下午 2022/2/17
 * @mark KlinTest
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    TransportClient client = null;

    public void client() {

        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("101.34.24.189"), 9200));
//            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cl1() {
        GetRequest request = new GetRequest("person", "doc", "1");
            client();
            GetResponse response = client.prepareGet("person", "doc", "1").get();
            Map<String, Object> source = response.getSource();
            source.forEach(System.out::printf);
            client.close();

    }

}
