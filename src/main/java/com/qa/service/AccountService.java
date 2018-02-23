package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.qa.businessLogic.BusinessLogic1;
import com.qa.businessLogic.BusinessLogicIFace;
import com.qa.domain.Account;
import com.qa.repository.MyRepo;
import com.qa.repository.MyRepoIFace;

public class AccountService {

	
	
	@Inject
	private MyRepoIFace repo;
	
	@Inject
	BusinessLogicIFace BusinessLogicLayer;
	

	public AccountService() {
		
	}
	
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	public String addAccount(String account) {
		if(BusinessLogicLayer.dochecks(account)) {
			return repo.createAccount(account);
		}
		else return "{\"message\": \"This account is blocked\"}";

	}

	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}

}
