package com.androidbasedata.exceptions;

import org.json.JSONObject;

public class WebServiceException extends Exception {

	// region Properties

	private static final long serialVersionUID = 4765261434583198702L;

	private Exception exceptionOrigin;
	private String url;
	private JSONObject jsonObject;

	// endregion

	// region Constructors

	public WebServiceException(Exception exceptionOrigin, String url) {
		this.exceptionOrigin = exceptionOrigin;
		this.url = url;
	}

	public WebServiceException(Exception exceptionOrigin, String url,
			JSONObject jsonObject) {
		this.exceptionOrigin = exceptionOrigin;
		this.url = url;
		this.jsonObject = jsonObject;
	}

	// endregion

	// region Get / Set

	public Exception getExceptionOrigin() {
		return exceptionOrigin;
	}

	public String getUrl() {
		return url;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	// endregion

}
