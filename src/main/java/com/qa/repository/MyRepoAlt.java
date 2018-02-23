package com.qa.repository;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;

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


@Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Alternative
public class MyRepoAlt implements MyRepoIFace {
	//repo
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	@Inject
	private JSONUtil util;
	
	private static final Logger LOGGER = Logger.getLogger(MyRepoAlt.class);


	//@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	@Override
	public String getAllAccounts() {
		LOGGER.info("Alt Repo!-------------------------------------------------------------------");
		//util.getObjectFromJson
		//query = em.createQuery("SELECT *");
		Query query;
		query = em.createQuery("Select a FROM Account a");
		
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		
		//List colOfAccs = query.getResultList();
		//List<Account> listOfAccs = new ArrayList();
		
		return  util.getJSONForObject(accounts);
        //TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m ORDER     	 BY m.title DESC", Movie.class);
        //return query.getResultList();

	}

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String createAccount(String accountStr) {
		LOGGER.info("Alt Repo!-------------------------------------------------------------------");
		LOGGER.info("body------:" + accountStr);
		Account account = util.getObjectForJSON(accountStr, Account.class);
		em.persist(account);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String updateAccount(Long id, String accountStr) {
		LOGGER.info("Alt Repo!-------------------------------------------------------------------");
		LOGGER.info("body------:" + accountStr);
		Account updatedAccount = util.getObjectForJSON(accountStr, Account.class);
		Account accountFromDB = em.find(Account.class, id);
		LOGGER.info("db name = " + accountFromDB.getSecondName());
		LOGGER.info("updated name = " + updatedAccount.getSecondName());
		if (accountStr != null) {
			findAccount(id).setFirstName(updatedAccount.getFirstName());
			findAccount(id).setSecondName(updatedAccount.getSecondName());
			findAccount(id).setAccountNumber(updatedAccount.getAccountNumber()); 
			//accountFromDB = updatedAccount;
			//accountFromDB = em.merge(accountFromDB);
			//accountStr = util.getJSONForObject(accountFromDB);
			LOGGER.info("db name = " + accountFromDB.getSecondName());
			LOGGER.info("updated name = " + updatedAccount.getSecondName());
			
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String deleteAccount(Long id) {
		LOGGER.info("Alt Repo!-------------------------------------------------------------------");
		//return "{\"message\": \"account sucessfully deleted\"}";
		LOGGER.info("id---------:" + id);
		Account accountInDB = em.find(Account.class, id);
		if (accountInDB != null) {
			em.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	public Account findAccount(Long id)
	{
		return em.find(Account.class, id);
	}

}
