package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.repository.MyRepo;

public class AccountService {

	private Map<Integer, Account> accountMap;

	private int count = 0;
	
	@Inject
	private MyRepo repo;

	public AccountService() {
		accountMap = new HashMap<Integer, Account>();
	}
	
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	public void addAccountFromMap(Account userAccount) {
		accountMap.put(count, userAccount);
		count++;
	}

	public void removeAccountFromMap(Integer accountToRemove) {
		boolean countExists = accountMap.containsKey(accountToRemove);
		if (countExists) {
			accountMap.remove(accountToRemove);
		}
	}

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}
	
	public Account getAccount(int id) {
		return accountMap.get(id);
	}

	public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
	}

	public String addAccount(String account) {
		return repo.createAccount(account);
	}

	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}

}
