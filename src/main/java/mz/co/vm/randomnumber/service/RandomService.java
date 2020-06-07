package mz.co.vm.randomnumber.service;
/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

import javax.ejb.Remote;

import mz.co.vm.randomnumber.entity.EstatisticEntity;
import mz.co.vm.randomnumber.entity.PendingEntity;
import mz.co.vm.randomnumber.entity.RandomNumberEntity;
/**
 * 
 * @author cfaife
 *
 */
@Remote
public interface RandomService {
	
	RandomNumberEntity generateNewRandomNumber(Long xMaxWait) throws InterruptedException, ExecutionException, TimeoutException;
	
	List<RandomNumberEntity> getHistory();
	
	boolean cancelRandomRequest(UUID uuid);
	
	EstatisticEntity getStats();
	
	List<PendingEntity> getPendingRequest();
	
	void changePoolThreadSize(int size);
	
	ExecutorService getExecutorService();
	

}
