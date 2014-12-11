package com.ng.mats.psa.mt.fortis.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class ConnectionTester {
	public static void main(String[] args) {

		try {
			URL url = new URL("https://62.173.32.122:8443/webapi/sdynamic");
			String username = "test1", pswd = "test1";
			byte[] encryptionBytes = username.concat(":").concat(pswd)
					.getBytes();
			String encoding = new String(new Base64().encode(encryptionBytes));
			// String encoding = (String) Base64.encode("test1:test1");

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			InputStream content = (InputStream) connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					content));

			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
