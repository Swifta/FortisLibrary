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

public class ThirdPartyPaymentManager {
	private static final Logger logger = Logger.getLogger(BalanceManager.class
			.getName());

	public Response initiateThirdPartyPayment(MoneyTransfer moneyTransfer) {
		moneyTransfer.setTxnName(Constants.TXNPURCHASEINQUIRY);
		moneyTransfer.setService(Constants.shopping);
		logger.info("-----------------------Before initiating namve value pair for check balance");
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

		nameValuePairList.add(new NameValuePair("partnerCode", moneyTransfer
				.getPartnerCode()));
		logger.info("-----------------------After setting partnerCode");
		nameValuePairList.add(new NameValuePair("billNo", moneyTransfer
				.getBillNo()));
		logger.info("-----------------------After setting billNo");
		nameValuePairList.add(new NameValuePair("amount", moneyTransfer
				.getAmount()));
		logger.info("-----------------------After setting amount");

		logger.info("-----------------------Before generating url");
		String url = UrlProcessor.generateUrl(nameValuePairList);
		logger.info("-----------------------After initiating connection manager");
		return ResponseProcessor.unMarshal(ConnectionManager.connectToUrl(url));

	}

	public Response confirmThirdPartyPayments(MoneyTransfer moneyTransfer) {
		moneyTransfer.setTxnName(Constants.TXNPURCHASE);
		moneyTransfer.setService(Constants.shopping);
		logger.info("-----------------------Before initiating namve value pair");
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

		nameValuePairList.add(new NameValuePair("service", moneyTransfer
				.getService()));
		logger.info("-----------------------After setting service");
		nameValuePairList.add(new NameValuePair("txnName", moneyTransfer
				.getTxnName()));
		logger.info("-----------------------After setting txnName");
		nameValuePairList.add(new NameValuePair("sourceMDN", moneyTransfer
				.getSourceMdn()));
		logger.info("-----------------------After setting source MDN");
		nameValuePairList.add(new NameValuePair("sourcePIN", moneyTransfer
				.getSourcePin()));
		logger.info("-----------------------after setting Source PIN");

		nameValuePairList.add(new NameValuePair("channelID", moneyTransfer
				.getChannelId()));
		logger.info("-----------------------After setting channel ID");
		nameValuePairList.add(new NameValuePair("sourcePocketCode",
				moneyTransfer.getSourcePocketCode()));
		logger.info("-----------------------After setting sourcePocketCode");
		nameValuePairList.add(new NameValuePair("partnerCode", moneyTransfer
				.getPartnerCode()));
		logger.info("-----------------------After setting partnerCode");
		nameValuePairList.add(new NameValuePair("amount", moneyTransfer
				.getAmount()));
		logger.info("-----------------------After setting amount");
		nameValuePairList.add(new NameValuePair("parentTxnID", moneyTransfer
				.getParentTxnId()));
		logger.info("-----------------------After setting parentTxnID");

		nameValuePairList.add(new NameValuePair("transferID", moneyTransfer
				.getTransferId()));
		logger.info("-----------------------After setting transferID");
		nameValuePairList.add(new NameValuePair("confirmed", moneyTransfer
				.getConfirmed()));
		logger.info("-----------------------After setting confirmed");

		logger.info("-----------------------Before generating url");
		String url = UrlProcessor.generateUrl(nameValuePairList);
		logger.info("-----------------------After initiating connection manager");
		return ResponseProcessor.unMarshal(ConnectionManager.connectToUrl(url));

	}
}
