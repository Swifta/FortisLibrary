package com.ng.mats.psa.mt.fortis.util;

import java.util.logging.Logger;

import com.ng.mats.psa.mt.fortis.services.AirtimeManager;
import com.ng.mats.psa.mt.fortis.services.BalanceManager;
import com.ng.mats.psa.mt.fortis.services.BillPaymentManager;
import com.ng.mats.psa.mt.fortis.services.CashInManager;
import com.ng.mats.psa.mt.fortis.services.CashOutManager;
import com.ng.mats.psa.mt.fortis.services.LoginManager;
import com.ng.mats.psa.mt.fortis.services.ThirdPartyPaymentManager;
import com.ng.mats.psa.mt.fortis.services.Wallet2BankManager;
import com.ng.mats.psa.mt.fortis.xmlprocessor.Response;

public class FortisClient {
	private static final Logger logger = Logger.getLogger(FortisClient.class
			.getName());
	public MoneyTransfer moneyTransfer = new MoneyTransfer();

	public FortisClient(String serviceName, String txnName) {
		logger.info("-----------------------Inside instance of Fortis Client");
		moneyTransfer.setChannelId(Constants.CHANNELID);
		moneyTransfer.setSourceMdn(Constants.agentNumber);
		moneyTransfer.setSourcePin(Constants.agentPIN);
		moneyTransfer.setService(serviceName);
		moneyTransfer.setTxnName(txnName);
		logger.info("-----------------------After setting attributes of money transfer");
		LoginManager loginManager = new LoginManager();

		logger.info("-----------------------after instantiating login manager");
		loginManager.initiateLogin(moneyTransfer);

	}

	public Response performCashin(MoneyTransfer moneyTransfer) {
		CashInManager cashInManager = new CashInManager();

		Response response = cashInManager.initiateCashIn(moneyTransfer); // Response

		if (response != null) {
			if (response.getTransferID() != null)
				moneyTransfer
						.setTransferId(response.getTransferID().getValue());
			if (response.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(response.getParentTxnID()
						.getValue());
		}
		cashInManager.confirmCashIn(moneyTransfer);
		return response;
	}

	public Response performCashout(MoneyTransfer moneyTransfer) {
		CashOutManager cashOutManager = new CashOutManager();
		Response response = cashOutManager.initiateCashOut(moneyTransfer);
		if (response != null) {
			if (response.getTransferID() != null)
				moneyTransfer
						.setTransferId(response.getTransferID().getValue());
			if (response.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(response.getParentTxnID()
						.getValue());
		}

		cashOutManager.confirmCashout(moneyTransfer);
		return response;
	}

	public Response performCashoutUnregistered(MoneyTransfer moneyTransfer) {
		CashOutManager cashOutManager = new CashOutManager();
		Response response = cashOutManager
				.initiateUnregisteredCashOut(moneyTransfer);
		if (response != null) {
			if (response.getTransferID() != null)
				moneyTransfer
						.setTransferId(response.getTransferID().getValue());
			if (response.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(response.getParentTxnID()
						.getValue());
		}
		cashOutManager.confirmUnregisteredCashout(moneyTransfer);
		return response;
	}

	public Response performAirtimeSales(MoneyTransfer moneyTransfer) {
		String companyName = "MTN", companyId = "";
		AirtimeManager airtimeManager = new AirtimeManager();
		switch (companyName) {
		case "MTN":
			companyId = "01";
			break;
		case "GLO":
			companyId = "02";
			break;
		case "AIRTEL":
			companyId = "03";
			break;
		case "ETISALAT":
			companyId = "04";
			break;

		case "VISAFONE":
			companyId = "05";
			break;

		}
		moneyTransfer.setCompanyId(companyId);
		Response response = airtimeManager.initiateAirtimeSale(moneyTransfer);
		if (response != null) {
			if (response.getTransferID() != null)
				moneyTransfer
						.setTransferId(response.getTransferID().getValue());
			if (response.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(response.getParentTxnID()
						.getValue());
		}
		airtimeManager.confirmAirtimeSale(moneyTransfer);
		return response;
	}

	public Response getBalance(MoneyTransfer moneyTransfer) {
		BalanceManager balanceManager = new BalanceManager();
		Response response = balanceManager.checkBalance(moneyTransfer);
		return response;
	}

	public Response performBillPayment(MoneyTransfer moneyTransfer) {
		BillPaymentManager billPaymentManager = new BillPaymentManager();
		Response response = billPaymentManager
				.initiateBillPayment(moneyTransfer);
		if (response != null) {
			if (response.getTransferID() != null)
				moneyTransfer
						.setTransferId(response.getTransferID().getValue());
			if (response.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(response.getParentTxnID()
						.getValue());
		}
		billPaymentManager.confirmBillPayment(moneyTransfer);
		return response;
	}

	public Response performThirdPartyPayment(MoneyTransfer moneyTransfer) {

		ThirdPartyPaymentManager thirdPartyPaymentManager = new ThirdPartyPaymentManager();
		Response response = thirdPartyPaymentManager
				.initiateThirdPartyPayment(moneyTransfer);
		if (response != null) {
			if (response.getTransferID() != null)
				moneyTransfer
						.setTransferId(response.getTransferID().getValue());
			if (response.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(response.getParentTxnID()
						.getValue());
		}
		thirdPartyPaymentManager.confirmThirdPartyPayments(moneyTransfer);
		return response;
	}

	public Response performW2B(MoneyTransfer moneyTransfer) {
		Wallet2BankManager wallet2BankManager = new Wallet2BankManager();
		Response response = wallet2BankManager
				.initiateWalletToBank(moneyTransfer);
		if (response != null) {
			if (response.getTransferID() != null)
				moneyTransfer
						.setTransferId(response.getTransferID().getValue());
			if (response.getParentTxnID() != null)
				moneyTransfer.setParentTxnId(response.getParentTxnID()
						.getValue());
		}
		wallet2BankManager.confirmWalletToBank(moneyTransfer);
		return response;
	}

	public void finalizeFortisClient() {
		moneyTransfer.setSourcePocketCode(Constants.SOURCEPOCKETCODEWALLET);
		// moneyTransfer.setDestMdn(Constants.customerNumber);
		moneyTransfer.setDestMdn(Constants.unregisteredCustomerNumber);
		moneyTransfer.setDestMdn(Constants.bankAccountNumber);
		moneyTransfer.setConfirmed("true");
		moneyTransfer.setAgentCode(Constants.agentCode);
		moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setAmount("480");
		moneyTransfer.setCompanyId("");
		moneyTransfer.setBillerCode("20702");
		moneyTransfer.setBillNo("12345678");
		moneyTransfer.setPartnerCode("MER287");
		moneyTransfer.setSecreteCode("384138407");
		moneyTransfer.setTransferId("475688");
		moneyTransfer.setNaration("This is transfer to bank from fortis");
		moneyTransfer.setBenOpCode("00001");
		// Response response = performBillPayment(moneyTransfer);
		// Response response = performThirdPartyPayment(moneyTransfer);
		// Response response = performCashin(moneyTransfer);
		Response response = getBalance(moneyTransfer);
		// Response response = performAirtimeSales(moneyTransfer);
		// Response response = performW2B(moneyTransfer);
		// Response response = performCashoutUnregistered(moneyTransfer);
		logger.info("-----------------------After initiating login"
				+ response.toString());
	}

	public static void main(String args[]) {
		FortisClient fortisClient = new FortisClient(Constants.account,
				Constants.TXNLOGIN);
		fortisClient.finalizeFortisClient();
	}
}
