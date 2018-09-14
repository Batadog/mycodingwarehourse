package day11.ealsticserach;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.net.InetAddress;

public class ElasticSearchTest {

    private Client client;

/**
 * 获取Transport client
 *
 */
public void getClient() throws  Exception {
    client = TransportClient.builder().build()
            .addTransportAddress(
                    new InetSocketTransportAddress(
                            InetAddress.getByName("localhost"),9300));
}

//使用json，创建文档，自动创建索引，自动和粗昂见映射
    @Test
    public void createDocument_1(){
    //json数据
        String source ="{" +
                "\"id\":\"1\","+
                "\"title\":\"ElasticSearch是一个搜索服务器\","+
                "\"content\":\"它提供了一个分布式多用户能力的全文搜索引擎\""+
                "}";

        //创建文档并提交，定义索引名称，文档类型，逐渐唯一标识id
        IndexRequestBuilder indexRequestBuilder =
                client.prepareIndex("blog","article","1").setSource(source);
        // 也可以调用。execute（）.actionget()
        IndexResponse indexResponse =indexRequestBuilder.get();

        //获取响应信息
        System.out.println("索引名称:" + indexResponse.getIndex());
        System.out.println("文档类型："+indexResponse.getType());
        System.out.println("文档id:" + indexResponse.getId());
        System.out.println("文档版本:" + indexResponse.getVersion());
        System.out.println("是否创建成功:" + indexResponse.isCreated());
        client.close();
    }


 //使用map 创建文档。


    /**
     * 使用es的帮助类，创建文档
     *
     */
@Test
    public void createDocument_3() throws  Exception {
    XContentBuilder source=XContentFactory.jsonBuilder()
            .startObject()
            .field("id","3")
            .field("title","添加搜索功能")
            .field("content","我这有个搜索方案")
            .endObject();
    System.out.println(source.toString());

    IndexRequestBuilder indexRequestBuilder =
            client.prepareIndex("blog","article","1").setSource(source);
    IndexResponse indexResponse =indexRequestBuilder.get();
    //获取响应信息
    System.out.println("索引名称:" + indexResponse.getIndex());
    System.out.println("文档类型："+indexResponse.getType());
    System.out.println("文档id:" + indexResponse.getId());
    System.out.println("文档版本:" + indexResponse.getVersion());
    System.out.println("是否创建成功:" + indexResponse.isCreated());

    client.close();
}
/**
 * 搜索文档（单个索引）
 */
@Test
    public void testGetData_1(){
    MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
            .add("blog","article","1")
            .add("blog","article","2")
            .get();
    //遍历获取到的值
    for (MultiGetItemResponse itemResponse:multiGetItemResponses){
        GetResponse response =itemResponse.getResponse();
        if (response.isExists()){
            String source =response.getSourceAsString();
            System.out.println(source);
        }
    }
    client.close();
}
/**
 * 更新文档数据
 */
public void testUpdata_1() throws  Exception {
    UpdateRequest request = new UpdateRequest();
    request.index("bolg");
    request.type("article");
    request.id("1");
    request.doc(XContentFactory.jsonBuilder().startObject()
            .field("id","1")
            .field("content","更新，分布式搜索系统")
            .endObject()
    );
    UpdateResponse updateResponse = client.update(request).get();
    client.close();
};



















}
