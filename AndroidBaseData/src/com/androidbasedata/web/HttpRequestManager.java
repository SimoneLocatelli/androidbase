package com.androidbasedata.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.util.Log;

import com.androidbase.annotations.AnnotationUtils;
import com.androidbase.annotations.FieldAnnotation;
import com.androidbasedata.exceptions.WebServiceException;
import com.androidbasedata.json.JsonProperty;

public class HttpRequestManager {

	// region Properties

	private static final String tag = "JSonRequestManager";

	private static int requestTimeOut = 10 * 1000; // 10 seconds

	private static int HttpResponseCode_OK = 200;

	// endregion

	// region Methods

	public static JSONObject sendGetRequest(String url)
			throws WebServiceException {
		InputStream data = null;
		String result = null;

		JSONObject jsonObject = null;

		try {
			HttpParams httpParameters = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(httpParameters,
					HttpRequestManager.requestTimeOut);

			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

			HttpGet request = new HttpGet(new URI(url));
			request.setParams(httpParameters);

			HttpResponse response = httpClient.execute(request);

			data = response.getEntity().getContent();

			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(data, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = buffReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			result = sb.toString();

			jsonObject = new JSONObject(result);

			buffReader.close();
		} catch (Exception ex) {
			Log.w(HttpRequestManager.tag, ex);

			throw new WebServiceException(ex, url);
		}

		if (data != null) {
			try {
				data.close();
			} catch (Exception ex) {
				Log.w(HttpRequestManager.tag, ex);

				throw new WebServiceException(ex, url);
			}
		}

		return jsonObject;

	}

	public static JSONObject sendPostRequest(String url)
			throws WebServiceException {

		return HttpRequestManager.sendPostRequest(url, null);
	}

	public static JSONObject sendPostRequest(String url, JSONObject jsonObject)
			throws WebServiceException {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpRequestManager.setJsonEntity(httpPost, jsonObject);

			HttpResponse response = httpclient.execute(httpPost);

			InputStream inputStream = response.getEntity().getContent();

			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(inputStream, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = buffReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			String result = sb.toString();

			Log.i(HttpRequestManager.tag, "result: " + result);

			buffReader.close();

			JSONObject outputJsonObject = new JSONObject(result);

			return outputJsonObject;

		} catch (Exception ex) {
			Log.w(HttpRequestManager.tag, ex);
			throw new WebServiceException(ex, url);
		}

	}

	public static JSONObject convertToJSONObject(Object object)
			throws Exception {

		JSONObject jsonObject = new JSONObject();

		ArrayList<FieldAnnotation<JsonProperty>> annotations = AnnotationUtils
				.findFields(object, JsonProperty.class);

		for (FieldAnnotation<JsonProperty> item : annotations) {

			JsonProperty jsonProperty = item.getAnnotation();

			jsonObject.put(jsonProperty.name(), item.getField().get(object));

		}

		return jsonObject;
	}

	// region Helpers

	private static void setJsonEntity(HttpPost httpPost, JSONObject jsonObject)
			throws UnsupportedEncodingException {
		if (httpPost == null || jsonObject == null) {
			return;
		}

		StringEntity stringEntity = new StringEntity(jsonObject.toString());

		stringEntity.setContentType("application/json;charset=UTF-8");
		stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json;charset=UTF-8"));
		httpPost.setEntity(stringEntity);
	}

	// endregion

	// endregion

}
