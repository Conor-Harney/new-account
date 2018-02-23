package com.qa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.qa.service.AccountService;

public class callerTester {
	
	@InjectMocks
	private AccountController endpoint;

	@Mock
	private AccountService service;
	
	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";

	@Test
	public void testGetAllAccounts() {
		Mockito.when(service.getAllAccounts()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAllAccounts());
	}
	
	/*@GET
	@Path("{id}")
	public Response getAccount(
			@PathParam("id") String id) {
		
		AccountController ac = new AccountController();
		ac.getAccount("root");
		return null;
	}*/

}
