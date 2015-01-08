package com.ng.mats.psa.mt.fortis.util;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.NameValuePair;

public class UrlProcessor {
	private static final Logger logger = Logger.getLogger(UrlProcessor.class
			.getName());
	static String webPage = "https://62.173.32.122:8443/webapi/sdynamic?";

	// String serviceName =
	// "serviceName=activation&sourceMDN=621234567890&sourcePIN=123456&";

	public static String generateUrl(List<NameValuePair> nameValuePairList) {
		String url = webPage;
		if (nameValuePairList != null) {
			logger.info("-----------------Name value pair list is not null");
			NameValuePair nameValuePair;
			Iterator<NameValuePair> nameValueListIte = nameValuePairList
					.iterator();
			while (nameValueListIte.hasNext()) {
				nameValuePair = new NameValuePair();
				nameValuePair = nameValueListIte.next();
				if (nameValuePair != null) {
					url = url.concat(nameValuePair.getName() + "=").concat(
							nameValuePair.getValue() + "&");
				}
			}
			url = url.substring(0, url.length() - 1);
		} else {
			logger.info("-----------------Name value pair list is null");
		}
		logger.info("-----------------Populated url is ::::" + url);
		return url;
	}

}
