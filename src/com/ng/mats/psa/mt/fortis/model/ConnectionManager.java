package com.ng.mats.psa.mt.fortis.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;

public class ConnectionManager {
	private static final Logger logger = Logger
			.getLogger(ConnectionManager.class.getName());
	static {
		// for localhost testing only
		javax.net.ssl.HttpsURLConnection
				.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

					public boolean verify(String hostname,
							javax.net.ssl.SSLSession sslSession) {
						if (hostname.equals("62.173.32.122")) {
							return true;
						}
						return false;
					}
				});
	}

	public static void main(String[] args) {
		String webPage = "https://62.173.32.122:8443/webapi/sdynamic";
		connectToUrl(webPage);
	}

	public static String connectToUrl(String webPage) {
		String result = "";
		try {
			String name = "admin";
			String password = "admin";
			logger.info("-----------------------Inside connect to URL");
			NameValuePair nValue = new NameValuePair("", "");
			nValue.getName();
			String authString = name + ":" + password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);
			logger.info("-----------------------Before instantiating the URL");
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			logger.info("-----------------------After opening connection");
			urlConnection.setRequestProperty("Authorization", "Basic "
					+ authStringEnc);
			logger.info("-----------------------After setting authorization property");
			InputStream is = urlConnection.getInputStream();
			logger.info("-----------------------after getting input stream");
			InputStreamReader isr = new InputStreamReader(is);
			logger.info("-----------------------input stream reader");

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();

			System.out.println("*** BEGIN ***");
			System.out.println(result);
			System.out.println("*** END ***");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}