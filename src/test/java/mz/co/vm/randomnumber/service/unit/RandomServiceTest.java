package mz.co.vm.randomnumber.service.unit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import mz.co.vm.randomnumber.entity.EstatisticEntity;
import mz.co.vm.randomnumber.entity.PendingEntity;
import mz.co.vm.randomnumber.entity.RandomNumberEntity;
import mz.co.vm.randomnumber.service.RandomService;
import mz.co.vm.randomnumber.service.RandomServiceImpl;

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
		
		List<RandomNumberEntity> randomNumbers = randomService.getHistory();
		
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
	@Test
	public void testGetPendingRequest_shouldPass() throws InterruptedException, ExecutionException, TimeoutException {
		this.randomService.generateNewRandomNumber(1200L);
		this.randomService.generateNewRandomNumber(1200L);

		
		List<PendingEntity> pendingEntities = this.randomService.getPendingRequest();
		assertNotNull(pendingEntities);
		 
		assertEquals(2, pendingEntities.size());
	}
	
	 

}
