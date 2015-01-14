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

public class BillPaymentManager {
	private static final Logger logger = Logger
			.getLogger(BillPaymentManager.class.getName());

	public Response initiateBillPayment(MoneyTransfer moneyTransfer) {
		moneyTransfer.setTxnName(Constants.TXNAIRTIMEPURCHASEINQUIRY);
		moneyTransfer.setService(Constants.agent);
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

		nameValuePairList.add(new NameValuePair("billerCode", moneyTransfer
				.getBillerCode()));
		logger.info("-----------------------After setting billerCode");
		nameValuePairList.add(new NameValuePair("billNo", moneyTransfer
				.getCompanyId()));
		logger.info("-----------------------After setting billNo");
		nameValuePairList.add(new NameValuePair("amount", moneyTransfer
				.getBillNo()));
		logger.info("-----------------------After setting amount");
		nameValuePairList.add(new NameValuePair("destMDN", moneyTransfer
				.getDestMdn()));
		logger.info("-----------------------After setting destMDN");

		logger.info("-----------------------Before generating url");
		String url = UrlProcessor.generateUrl(nameValuePairList);
		logger.info("-----------------------After initiating connection manager");
		return ResponseProcessor.unMarshal(ConnectionManager.connectToUrl(url));

	}

	public Response confirmBillPayment(MoneyTransfer moneyTransfer) {
		moneyTransfer.setTxnName(Constants.TXNAIRTIMEPURCHASE);
		moneyTransfer.setService(Constants.agent);
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
		nameValuePairList.add(new NameValuePair("sourcePIN", moneyTransfer
				.getSourcePin()));
		logger.info("-----------------------after setting Source PIN");
		nameValuePairList.add(new NameValuePair("txnName", moneyTransfer
				.getTxnName()));
		logger.info("-----------------------After setting txnName");

		nameValuePairList.add(new NameValuePair("transferID", moneyTransfer
				.getTransferId()));
		logger.info("-----------------------After setting transferID");
		nameValuePairList.add(new NameValuePair("confirmed", moneyTransfer
				.getConfirmed()));
		logger.info("-----------------------After setting confirmed");
		nameValuePairList.add(new NameValuePair("sourcePocketCode",
				moneyTransfer.getSourcePocketCode()));
		logger.info("-----------------------After setting sourcePocketCode");

		nameValuePairList.add(new NameValuePair("billerCode", moneyTransfer
				.getBillerCode()));
		logger.info("-----------------------After setting billerCode");
		nameValuePairList.add(new NameValuePair("billNo", moneyTransfer
				.getCompanyId()));
		logger.info("-----------------------After setting billNo");
		nameValuePairList.add(new NameValuePair("destMDN", moneyTransfer
				.getDestMdn()));
		logger.info("-----------------------After setting destMDN");
		nameValuePairList.add(new NameValuePair("parentTxnID", moneyTransfer
				.getParentTxnId()));
		logger.info("-----------------------After setting parentTxnID");
		logger.info("-----------------------Before generating url");
		String url = UrlProcessor.generateUrl(nameValuePairList);
		logger.info("-----------------------After initiating connection manager");
		return ResponseProcessor.unMarshal(ConnectionManager.connectToUrl(url));

	}

}
