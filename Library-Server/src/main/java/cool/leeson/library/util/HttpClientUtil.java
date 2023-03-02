package cool.leeson.library.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpClientUtil {
    public static String getRequest(String url) throws Exception {
        //HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            //响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //格式化响应体
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            response.close();
            httpClient.close();
        }
        return null;
    }

    public static String postRequest(String url, Map<String, Object> data, Map<String, String> headers) throws Exception {
        //HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            //添加请求头
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key, headers.get(key));
                }
            }
            //封装请求参数，将map集合转换成json格式
            if (data != null) {
                JSONObject jsonString = new JSONObject(data);
                //使用StringEntity转换成实体类型
                StringEntity entity = new StringEntity(jsonString.toString());
                System.out.println(jsonString.toJSONString());
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");//发送json数据需要设置contentType
                //将封装的参数添加到Post请求中
                httpPost.setEntity(entity);
            }

            response = httpClient.execute(httpPost);
            //响应体
            HttpEntity entity1 = response.getEntity();
            if (entity1 != null) {
                //格式化响应体
                return EntityUtils.toString(entity1);
            }
        } catch (IOException e) {
            return null;
        } finally {
            response.close();
            httpClient.close();
        }
        return null;
    }
}

