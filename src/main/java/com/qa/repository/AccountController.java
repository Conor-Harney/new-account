package com.qa.repository;

import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.qa.service.AccountService;

@Path("/AccountControllerPath")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {
	
	@Inject
	private AccountService accountService;
	
	
	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	
	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addAccount(String account) {
		return accountService.addAccount(account);
	}

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		return accountService.updateAccount(id, account);
	}

	
	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") Long id) {
		return accountService.deleteAccount(id);

	}
	

	
}

