package com.ng.mats.psa.mt.fortis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.NameValuePair;

import com.ng.mats.psa.mt.fortis.model.ConnectionManager;
import com.ng.mats.psa.mt.fortis.util.Constants;
import com.ng.mats.psa.mt.fortis.util.MoneyTransfer;
import com.ng.mats.psa.mt.fortis.util.UrlProcessor;
import com.ng.mats.psa.mt.fortis.xmlprocessor.Response;
import com.ng.mats.psa.mt.fortis.xmlprocessor.ResponseProcessor;

public class TransactionManager {
	private static final Logger logger = Logger
			.getLogger(TransactionManager.class.getName());

	public Response getTransactionStatus(MoneyTransfer moneyTransfer) {
		moneyTransfer.setTxnName(Constants.TXNHISTORY);
		moneyTransfer.setService(Constants.bank);
		logger.info("-----------------------Before initiating namve value pair transaction status");
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
		nameValuePairList.add(new NameValuePair("sourcePIN", moneyTransfer
				.getSourcePin()));
		logger.info("-----------------------after setting Source PIN");
		nameValuePairList.add(new NameValuePair("txnName", moneyTransfer
				.getTxnName()));
		logger.info("-----------------------After setting txnName");

		nameValuePairList.add(new NameValuePair("sourcePocketCode",
				moneyTransfer.getSourcePocketCode()));
		logger.info("-----------------------After setting sourcePocketCode");
		logger.info("-----------------------Before generating url");
		String url = UrlProcessor.generateUrl(nameValuePairList);
		logger.info("-----------------------After initiating connection manager");
		return ResponseProcessor.unMarshal(ConnectionManager.connectToUrl(url));

	}

}
