package com.qa.repository;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public interface MyRepoIFace {


	public String getAllAccounts();

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String createAccount(String accountStr);

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String updateAccount(Long id, String accountStr);

	@Transactional(javax.transaction.Transactional.TxType.REQUIRED)
	public String deleteAccount(Long id) ;
	
	Account findAccount(Long id);

}
