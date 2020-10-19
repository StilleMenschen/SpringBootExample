package tech.tystnad.works.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 基于HttpClient的小工具<br/>
 * 支持: GET, POST, PUT, DELETE 请求<br/>
 * 支持表单(application/x-www-form-urlencoded)形式<br/>
 * 支持文件上传(multipart/form-data)形式
 */
public class HttpUtils {

    private static final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * 发送GET请求
     *
     * @param url           请求地址
     * @param headers       请求头,可以为null
     * @param urlParameters 请求url参数,可以为null
     * @return 响应的请求体字符串
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, String> urlParameters) {
        HttpGet httpGet = new HttpGet(buildUri(url, urlParameters));
        fillHeaders(httpGet, headers);
        return executeRequest(httpGet);
    }

    /**
     * 发送GET请求,获得字节数据
     *
     * @param url           请求地址
     * @param headers       请求头,可以为null
     * @param urlParameters 请求url参数,可以为null
     * @return 响应的请求体字节数据
     */
    public static byte[] doGetToByte(String url, Map<String, String> headers, Map<String, String> urlParameters) {
        HttpGet httpGet = new HttpGet(buildUri(url, urlParameters));
        fillHeaders(httpGet, headers);
        return executeRequestToByte(httpGet);
    }

    /**
     * 发送一般POST请求
     *
     * @param url           请求地址
     * @param headers       请求头,可以为null
     * @param urlParameters 请求url参数,可以为null
     * @param body          请求体字符串,可以为null
     * @return 响应的请求体字符串
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> urlParameters, String body) {
        HttpPost httpPost = new HttpPost(buildUri(url, urlParameters));
        fillHeaders(httpPost, headers);
        if (body != null && !"".equals(body.trim())) {
            httpPost.setEntity(new StringEntity(body, "UTF-8"));
        }
        return executeRequest(httpPost);
    }

    /**
     * 发送一般表单(application/x-www-form-urlencoded)请求
     *
     * @param url            请求地址
     * @param headers        请求头,可以为null
     * @param urlParameters  请求url参数,可以为null
     * @param bodyParameters 请求表单参数
     * @return 响应的请求体字符串
     */
    public static String doFormPost(String url, Map<String, String> headers, Map<String, String> urlParameters, Map<String, String> bodyParameters) {
        HttpPost httpPost = new HttpPost(buildUri(url, urlParameters));
        if (headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put("Content-type", "application/x-www-form-urlencoded");
        fillHeaders(httpPost, headers);
        if (bodyParameters != null && bodyParameters.size() > 0) {
            final List<NameValuePair> body = new LinkedList<>();
            bodyParameters.forEach((k, v) -> body.add(new BasicNameValuePair(k, v)));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(body, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return executeRequest(httpPost);
    }

    /**
     * 发送多媒体表单(multipart/form-data)请求
     *
     * @param url            请求地址
     * @param headers        请求头,可以为null
     * @param urlParameters  请求url参数,可以为null
     * @param fileParameters 请求文件参数,可以为null
     * @param bodyParameters 请求表单参数,可以为null
     * @return
     */
    public static String doMultipartPost(String url, Map<String, String> headers, Map<String, String> urlParameters,
                                         Map<String, File> fileParameters, Map<String, String> bodyParameters) {
        HttpPost httpPost = new HttpPost(buildUri(url, urlParameters));
        fillHeaders(httpPost, headers);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (fileParameters != null && fileParameters.size() > 0) {
            fileParameters.forEach((k, v) -> {
                FileBody fileBody = new FileBody(v);
                builder.addPart(k, fileBody);  //addPart上传文件
            });
        }
        if (bodyParameters != null && bodyParameters.size() > 0) {
            bodyParameters.forEach(builder::addTextBody);
        }
        builder.setCharset(Charset.forName("UTF-8"));
        httpPost.setEntity(builder.build());
        return executeRequest(httpPost);
    }

    /**
     * 发送PUT请求
     *
     * @param url           请求地址
     * @param headers       请求头,可以为null
     * @param urlParameters 请求url参数,可以为null
     * @param body          请求体字符串,可以为null
     * @return 响应的请求体字符串
     */
    public static String doPut(String url, Map<String, String> headers, Map<String, String> urlParameters, String body) {
        HttpPut httpPut = new HttpPut(buildUri(url, urlParameters));
        fillHeaders(httpPut, headers);
        if (body != null && !"".equals(body.trim())) {
            httpPut.setEntity(new StringEntity(body, "UTF-8"));
        }
        return executeRequest(httpPut);
    }

    /**
     * 发送DELETE请求
     *
     * @param url           请求地址
     * @param headers       请求头,可以为null
     * @param urlParameters 请求url参数,可以为null
     * @return 响应的请求体字符串
     */
    public static String doDelete(String url, Map<String, String> headers, Map<String, String> urlParameters) {
        HttpDelete httpDelete = new HttpDelete(buildUri(url, urlParameters));
        fillHeaders(httpDelete, headers);
        return executeRequest(httpDelete);
    }

    /**
     * 构建URI
     *
     * @param url           请求地址
     * @param urlParameters 请求url参数
     * @return
     */
    private static URI buildUri(String url, Map<String, String> urlParameters) {
        try {
            final URIBuilder urlBuilder = new URIBuilder(url);
            if (urlParameters != null && urlParameters.size() > 0) {
                urlParameters.forEach(urlBuilder::addParameter);
            }
            return urlBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 构建请求头
     *
     * @param request
     * @param headers
     */
    private static void fillHeaders(final HttpUriRequest request, Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            headers.forEach(request::setHeader);
        }
    }

    /**
     * 执行请求并获取响应体
     *
     * @param request
     * @return 响应体字符串
     */
    private static String executeRequest(HttpUriRequest request) {
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 执行请求并获取响应字节数据
     *
     * @param request
     * @return 响应体字节数据
     */
    private static byte[] executeRequestToByte(HttpUriRequest request) {
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            byte[] buffers = EntityUtils.toByteArray(response.getEntity());
            return buffers;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
