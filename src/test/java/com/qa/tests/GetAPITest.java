package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.testBase.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase tetsBase;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {

		tetsBase = new TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		url = serviceURL + apiURL;
	}

	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		// Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code " + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->" + responseJson);

		// Single Assertion
		// Per Page Value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Per page value is--->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// Total Value
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total is--->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// Get the value from JSON Array
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		System.out.println("ID-->" + id);
		System.out.println("email-->" + email);
		System.out.println("First_name-->" + first_name);
		System.out.println("Last_name-->" + last_name);
		System.out.println("Avatar-->" + avatar);
//		Assert.assertEquals(Integer.parseInt(id), 7, "ID is not 7");
//		Assert.assertEquals(email, "michael.lawson@reqres.in");
//		Assert.assertEquals(first_name, "Michael");
//		Assert.assertEquals(last_name, "Lawson");
//		Assert.assertEquals(avatar, "https://reqres.in/img/faces/7-image.jpg");

		// All Headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority=2)
	public void getAPITestWithHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test@gmail.com");
		// headerMap.put("Password", "test@123");
		// headerMap.put("Auth-Token", "212345");
		
		closeableHttpResponse = restClient.get(url);
		// Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code " + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->" + responseJson);

		// Single Assertion
		// Per Page Value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Per page value is--->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// Total Value
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total is--->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// Get the value from JSON Array
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		System.out.println("ID-->" + id);
		System.out.println("email-->" + email);
		System.out.println("First_name-->" + first_name);
		System.out.println("Last_name-->" + last_name);
		System.out.println("Avatar-->" + avatar);
//		Assert.assertEquals(Integer.parseInt(id), 7, "ID is not 7");
//		Assert.assertEquals(email, "michael.lawson@reqres.in");
//		Assert.assertEquals(first_name, "Michael");
//		Assert.assertEquals(last_name, "Lawson");
//		Assert.assertEquals(avatar, "https://reqres.in/img/faces/7-image.jpg");

		// All Headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
}
