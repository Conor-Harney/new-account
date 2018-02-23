package com.qa.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class EntityManagerTester {
	
	

	@Mock
	private List<Account> accs;
	
	@Mock
	private Query query;
	
	@Mock
	private EntityManager em;
	
	@InjectMocks//anything that has the mock implementation, put in here
	private MyRepo m_entityManager;

	@Test
	public void testGetAll() {
		
		accs.add(new Account("John", "Blogs", "1234"));
		accs.add(new Account("jane", "Blogs", "1235"));
		accs.add(new Account("John", "Gorden", "1236"));
		//Mockito.when(m_entityManager.getAllAccounts()).thenReturn(accs);//when I call this db, return this string.
		Assert.assertEquals(accs, m_entityManager.getAllAccounts());
		//"0{'firstName':'John', 'secondName' : 'Bloggs', 'accountNumber' : '1234'}, 1{'firstName':'Jane', 'secondName' : 'Bloggs', 'accountNumber' : '1234'}, 2{'firstName':'John', 'secondName' : 'Gorden', 'accountNumber' : '1236'}"
		Mockito.verify(accs.get(0)).getFirstName();
		//Mockito.verify(movieService, Mockito.never()).deleteMovie(1L);
	}


}
