package com.ng.mats.psa.mt.fortis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.NameValuePair;

import com.ng.mats.psa.mt.fortis.model.ConnectionManager;
import com.ng.mats.psa.mt.fortis.util.MoneyTransfer;
import com.ng.mats.psa.mt.fortis.util.UrlProcessor;

public class LoginManager {
	private static final Logger logger = Logger.getLogger(LoginManager.class
			.getName());

	public void initiateLogin(MoneyTransfer moneyTransfer) {
		logger.info("-----------------------Inside initiate Login");
		moneyTransfer.setAuthenticationString(moneyTransfer.getSourceMdn());
		moneyTransfer.setAppType(moneyTransfer.getService());
		logger.info("-----------------------Before initiating namve value pair");
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new NameValuePair("channelID", moneyTransfer
				.getChannelId()));
		logger.info("-----------------------After setting channel ID");
		nameValuePairList.add(new NameValuePair("service", moneyTransfer
				.getService()));
		logger.info("-----------------------After setting service");
		nameValuePairList.add(new NameValuePair("sourceMDN", moneyTransfer
				.getSourceMdn()));
		logger.info("-----------------------After setting source MDN");
		// nameValuePairList.add(new NameValuePair("sourcePIN", moneyTransfer
		// .getSourcePin()));
		logger.info("-----------------------after setting Source PIN");
		nameValuePairList.add(new NameValuePair("txnName", moneyTransfer
				.getTxnName()));
		logger.info("-----------------------After setting txnName");
		nameValuePairList.add(new NameValuePair("authenticationString",
				moneyTransfer.getSourcePin()));
		logger.info("-----------------------After setting authenticationString");
		// nameValuePairList.add(new NameValuePair("apptype", moneyTransfer
		// .getAppType()));
		logger.info("-----------------------After setting apptype");
		logger.info("-----------------------Before generating url");
		String url = UrlProcessor.generateUrl(nameValuePairList);
		ConnectionManager con = new ConnectionManager();
		logger.info("-----------------------After initiating connection manager");
		con.connectToUrl(url);
		logger.info("-----------------------After initiating connect to URL");
	}
}
