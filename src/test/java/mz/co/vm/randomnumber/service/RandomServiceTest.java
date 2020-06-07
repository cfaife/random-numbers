package mz.co.vm.randomnumber.service;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.TimeoutException;


import javax.naming.NamingException;

import org.junit.Before;

import org.junit.Test;
 

import mz.co.vm.randomnumber.entity.EstatisticEntity;
import mz.co.vm.randomnumber.entity.RandomNumberEntity;

/**
 * This class  only tests the  logic   of {@link RandomService}  
 * 
 * @author Clerio Alfredo Faife
 *
 */
public class RandomServiceTest {
	
	private RandomService randomService;

	@Before
	public void setup() throws NamingException {
		randomService  = new RandomServiceImpl();
	}

	@Test
	public void testGenerateNewNumberWithNullXMaxWait_shouldTake30Sec() throws InterruptedException, ExecutionException, TimeoutException {

		RandomNumberEntity randomNumber = randomService.generateNewRandomNumber(null);
		assertNotNull(randomService);

		assertNotNull(randomNumber.getNumber());

	}
	
	@Test
	public void testGenerateNewNumberWithNullXMaxWait_shouldPass() throws InterruptedException, ExecutionException, TimeoutException {

		RandomNumberEntity randomNumber = randomService.generateNewRandomNumber(1000L);
		assertNotNull(randomService);

		assertNotNull(randomNumber.getNumber());

	}
	@Test
	public void generateNewRandomNumber() throws InterruptedException, ExecutionException, TimeoutException{
		this.randomService.generateNewRandomNumber(1600L);
	}
	@Test
	public void	testGetHistory_shouldPass() throws InterruptedException, ExecutionException, TimeoutException {
		
		randomService.generateNewRandomNumber(2000L);
		randomService.generateNewRandomNumber(2000L);
		randomService.generateNewRandomNumber(2000L);
		
		Set<RandomNumberEntity> randomNumbers = randomService.getHistory();
		
		assertFalse(randomNumbers.isEmpty());
		assertEquals(3, randomNumbers.size());
	}
	 
	@Test
	public void testGetStats_shouldPass() throws InterruptedException, ExecutionException, TimeoutException {
		this.randomService.generateNewRandomNumber(1200L);
		this.randomService.generateNewRandomNumber(1200L);


		EstatisticEntity estatisticEntity =  this.randomService.getStats();
		assertNotNull(estatisticEntity);
		assertNotNull(estatisticEntity.getMaximumWaitingTime());

		assertNotNull(estatisticEntity.getMinimumWaitingTime());

		assertNotNull(estatisticEntity.getTotalPendingRequests());

	}
	
}
