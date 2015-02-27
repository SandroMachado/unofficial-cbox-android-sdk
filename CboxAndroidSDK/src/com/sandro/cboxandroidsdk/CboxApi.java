package com.sandro.cboxandroidsdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class CboxApi {
	
	private String boxId;
	private String boxTagId;
	
	public CboxApi(String boxId, String boxTagId) {
		super();
		this.boxId = boxId;
		this.boxTagId = boxTagId;
	}
	
	public String performALogin(String username, String password) throws UnsupportedEncodingException{
		String urlRequest = CboxConfigurations.SERVER_URL+CboxConfigurations.SERVER_URL_REQUEST+"?boxid="+boxId+"&boxtag="+boxTagId+"&sec=profile&n="+URLEncoder.encode(username, "UTF-8");
	
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("logpword", password));
		pairs.add(new BasicNameValuePair("sublog", "Login"));
		
		HttpResponse response = HTTPUtils.doPost(urlRequest, pairs);
		
		Header[] cookie= response.getHeaders("Set-Cookie");
		
		try {
			if(EntityUtils.toString(response.getEntity()).contains("protege-lo com uma password e para activar o seu perfil")){
				return null;
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		String result = "";
		try{
			for(Header value : cookie){
				if(value.getValue().contains("key_"+boxId)){
					result = value.getValue().substring(value.getValue().indexOf("key_"+boxId) + 4 + boxId.length() + 1, value.getValue().indexOf(";"));
					break;
				}
			}
		}catch(Exception e){
			result = null;
		}
		Log.d("RESULT", result);
		return result;
	}
	
	public HttpResponse postAMessage(String username, String email, String key, String message){
		String urlRequest = CboxConfigurations.SERVER_URL+CboxConfigurations.SERVER_URL_REQUEST+"?boxid="+boxId+"&boxtag="+boxTagId+"&sec=submit";
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("nme", username));
		if(email==null){
			pairs.add(new BasicNameValuePair("eml", "E-mail%20%2F%20url"));
		}else{
			pairs.add(new BasicNameValuePair("eml", email));
		}
		pairs.add(new BasicNameValuePair("key", key));
		pairs.add(new BasicNameValuePair("pst", message));

		return HTTPUtils.doPost(urlRequest, pairs);
	}
	
	public HttpResponse deleteAMessage(String username, String key, String messageId){
		String urlRequest = CboxConfigurations.SERVER_URL+CboxConfigurations.SERVER_URL_REQUEST+"?boxid="+boxId+"&boxtag="+boxTagId+"&sec=delban"+"&del="+messageId;
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		return HTTPUtils.doPost(urlRequest, pairs);
	}

}
