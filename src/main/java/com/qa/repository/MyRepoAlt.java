package com.qa.repository;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Alternative
public class MyRepoAlt implements MyRepoIFace {
	//repo
	
	public MyRepoAlt() {
		accountMap = new HashMap<Integer, Account>();
	}
	
	private Map<Integer, Account> accountMap;

	private static int count = 0;
	
	//@PersistenceContext(unitName = "primary")
    //private EntityManager em;
	
	@Inject
	private JSONUtil util;
	
	private static final Logger LOGGER = Logger.getLogger(MyRepoAlt.class);


	@Override
	public String getAllAccounts() {//
		return  util.getJSONForObject(accountMap);
	}

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String createAccount(String accountStr) {//
		accountMap.put(count, util.getObjectForJSON(accountStr, Account.class));
		count++;
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String updateAccount(Long id, String accountStr) {//
		Account updatedAccount = util.getObjectForJSON(accountStr, Account.class); 
		Account accountFromMap = accountMap.get(id);
		if (accountStr != null) {
			accountFromMap.setFirstName(updatedAccount.getFirstName());
			accountFromMap.setSecondName(updatedAccount.getSecondName());
			accountFromMap.setAccountNumber(updatedAccount.getAccountNumber()); 
			
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String deleteAccount(Long id) {
		boolean countExists = accountMap.containsKey(findAccount(id));
		if (countExists) {
			accountMap.remove(findAccount(id));
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	public Account findAccount(Long id)
	{
		return accountMap.get(id);
	}
	
	public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
	}

}
