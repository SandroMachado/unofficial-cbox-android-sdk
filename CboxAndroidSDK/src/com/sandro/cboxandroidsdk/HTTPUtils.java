package com.sandro.cboxandroidsdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class HTTPUtils {

	
	public static HttpResponse doPost(String urlRequest, List<NameValuePair> pairs){

		HttpResponse response = null;
		
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlRequest);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs,"UTF-8");
			post.setEntity(entity);
			
			response = client.execute(post);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
}
