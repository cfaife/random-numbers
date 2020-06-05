package mz.co.vm.randomnumber.service;
/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
import java.util.List;
import java.util.concurrent.ExecutorService;

import mz.co.vm.randomnumber.entity.EstatisticEntity;
import mz.co.vm.randomnumber.entity.PendingEntity;
import mz.co.vm.randomnumber.entity.RandomNumberEntity;

public interface RandomService {
	
	RandomNumberEntity generateNewNumber(Long xMaxWait);
	
	List<RandomNumberEntity> getHistory();
	
	void cancelRandomRequest(String UUID, ExecutorService executorService);
	
	EstatisticEntity getStats();
	
	List<PendingEntity> getPendingRequest();
	
	void setThreadPoolSize(int size, ExecutorService executorService);
	

}
