package com.qa.businessLogic;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class BusinessLogic1 implements BusinessLogicIFace {

	@Inject
	private JSONUtil util;
	
	@Override
	public boolean dochecks(String account) {
		boolean ChecksPass = true;
		if(checkLockedAccount(account))return false;
		return ChecksPass;
	}
	
	private boolean checkLockedAccount(String account)
	{
		if(util.getObjectForJSON(account, Account.class).getAccountNumber().equals("9999"))
		{
			return false;
		}
		else 
			return true;
	
	}

	

}
