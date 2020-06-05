package mz.co.vm.randomnumber.service;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import mz.co.vm.randomnumber.entity.RandomNumberEntity;

//@RunWith()
public class RandomServiceTest {

	
	private RandomService randomService;

	@Before
	public void setup() throws NamingException {
		
		  Properties jndiProperties = new Properties();
          jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, 
            "org.jboss.naming.remote.client.InitialContextFactory");
          jndiProperties.put(Context.URL_PKG_PREFIXES, 
            "org.jboss.ejb.client.naming");
          jndiProperties.put(Context.PROVIDER_URL, 
             "http-remoting://localhost:8080");
          jndiProperties.put("jboss.naming.client.ejb.context", true);
 		
		InitialContext ic  = new InitialContext(jndiProperties);
		randomService = (RandomService) ic.lookup("java:global/random-numbers/randomService");
	}

	//@Ignore
	@Test
	public void testGenerateNewNumberWithNullXMaxWait_shouldPass() throws NamingException {

		RandomNumberEntity randomNumber = randomService.generateNewNumber(null);

		assertNotNull(randomNumber);

	}

}
