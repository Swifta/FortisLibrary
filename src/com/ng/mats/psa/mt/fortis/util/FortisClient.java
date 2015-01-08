package com.ng.mats.psa.mt.fortis.util;

import java.util.logging.Logger;

import com.ng.mats.psa.mt.fortis.services.CashInManager;
import com.ng.mats.psa.mt.fortis.services.LoginManager;
import com.ng.mats.psa.mt.fortis.xmlprocessor.Response;

public class FortisClient {
	private static final Logger logger = Logger.getLogger(FortisClient.class
			.getName());

	public FortisClient(String serviceName, String txnName) {
		logger.info("-----------------------Inside instance of Fortis Client");
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		moneyTransfer.setChannelId(Constants.CHANNELID);
		moneyTransfer.setSourceMdn(Constants.agentNumber);
		moneyTransfer.setSourcePin(Constants.agentPIN);
		moneyTransfer.setService(serviceName);
		moneyTransfer.setTxnName(txnName);
		logger.info("-----------------------After setting attributes of money transfer");
		LoginManager loginManager = new LoginManager();
		CashInManager cashInManager = new CashInManager();
		logger.info("-----------------------after instantiating login manager");
		loginManager.initiateLogin(moneyTransfer);
		moneyTransfer.setSourcePocketCode(Constants.SOURCEPOCKETCODEWALLET);
		moneyTransfer.setDestMdn(Constants.customerNumber);
		moneyTransfer.setConfirmed("true");
		moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setAmount("100");
		Response initiateResponse = cashInManager.initiateCashIn(moneyTransfer);
		if (initiateResponse != null) {
			if (initiateResponse.getTransferID() != null)
				moneyTransfer.setTransferId(initiateResponse.getTransferID()
						.getValue());
			if (initiateResponse.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(initiateResponse.getParentTxnID()
						.getValue());
		}
		cashInManager.confirmCashIn(moneyTransfer);
		logger.info("-----------------------After initiating login");
	}

	public static void main(String args[]) {
		new FortisClient(Constants.account, Constants.TXNLOGIN);
	}
}
