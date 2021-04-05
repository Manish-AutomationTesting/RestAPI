package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	
	//RestClient Constructor
	public RestClient(){
		System.out.println("This is RestClient Constructor");
	}

	//Get Method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpsResponse = httpClient.execute(httpGet);// Hit the get URL
		return closeableHttpsResponse;
	}

	//Get Method
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpsResponse = httpClient.execute(httpGet);// Hit the get URL																			
		return closeableHttpsResponse;
	}
	// POST Method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// Http Post Request
		httpPost.setEntity(new StringEntity(entityString));// For PayLoad

		// For Headers
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpsResponse = httpClient.execute(httpPost);
		return closeableHttpsResponse;
	}

}
