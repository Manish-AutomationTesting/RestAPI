package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.testBase.TestBase;

public class PostAPITest extends TestBase {
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

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test@gmail.com");
		// headerMap.put("Password", "test@123");
		// headerMap.put("Auth-Token", "212345");

		// Jackson Object
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("morpheus", "leader");// Expected Users Object

		// Object to json file
		mapper.writeValue(
				new File("D:\\Workspacedate13month4_Selenium\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"),
				user);

		// Object to Json in String
		String userJsonString = mapper.writeValueAsString(user);
		System.out.println(userJsonString);
		// Call The API
		closeableHttpResponse = restClient.post(url, userJsonString, headerMap);

		// Validate Response from API
		// 1.Get Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, tetsBase.RESPONSE_STATUS_CODE_201);
		// Josn String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is-->" + responseJson);

		// Json to Java Object
		Users usersResObject = mapper.readValue(responseString, Users.class);// Actual
																				// Users
																				// Object
		System.out.println(usersResObject);
		Assert.assertTrue(user.getName().equals(usersResObject.getName()));
		Assert.assertTrue(user.getJob().equals(usersResObject.getJob()));

		System.out.println(usersResObject.getId());
		System.out.println(usersResObject.getCreatedAt());

	}
}
