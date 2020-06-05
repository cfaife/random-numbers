package mz.co.vm.randomnumber.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import javax.ejb.Stateless;

import Repository.RandomNumberRepository;
import mz.co.vm.randomnumber.entity.EstatisticEntity;
import mz.co.vm.randomnumber.entity.PendingEntity;
import mz.co.vm.randomnumber.entity.RandomNumberEntity;

/**
 * 
 * @author Clerio Alfredo Faife
 *
 */

@Stateless
public class RandomServiceImpl implements RandomService {
	
	private ExecutorService executorService = Executors.newSingleThreadExecutor();


	@Override
	public RandomNumberEntity generateNewNumber(Long xMaxWait) {
		
		RandomNumberEntity randomNumber = new RandomNumberEntity();
		randomNumber.setTimeCreated(LocalTime.now());
		
		InnerTread innerTread =new InnerTread(randomNumber);
		long startTime = System.currentTimeMillis()*1000;

		executorService.execute(innerTread);
		
		long endTime = System.currentTimeMillis()*1000;
		
		long duration = endTime-startTime;
		
		if(xMaxWait!=null ) {
				if( !(executorService.isTerminated()) && duration>=xMaxWait) {
					
					executorService.shutdownNow();
					randomNumber.setGenerated(false);
			}
		}
		
		
		return randomNumber;
	}
	
	@Override
	public List<RandomNumberEntity> getHistory() {
		return RandomNumberRepository.getHistory();
	}

	@Override
	public EstatisticEntity getStats() {
		List<RandomNumberEntity> randomNumbers =RandomNumberRepository.findAll();
		
		Stream<RandomNumberEntity> minStream= randomNumbers.stream();
		Optional<Long> min = minStream
					.map(x -> x.getTimeSecs())
					.min((x,y)->x.intValue()-y.intValue());
		
		Stream<RandomNumberEntity> maxStream = randomNumbers.stream();
		Optional<Long> max = maxStream
					.map(x -> x.getTimeSecs())
					.min((x,y)->x.intValue()-y.intValue());

		Stream<RandomNumberEntity> totalStream = randomNumbers.stream();
		Long totalPending  = totalStream
					.filter(x ->!x.isGenerated()).count();
		
		return new EstatisticEntity(max.get(), min.get(), totalPending.intValue());
		
	}

	@Override
	public void cancelRandomRequest(String UUID, ExecutorService executorService) {
		if(!executorService.isTerminated()){
			
			executorService.shutdown();
		}		
	}

	@Override
	public List<PendingEntity> getPendingRequest() {
		
		
		List<PendingEntity> pendings = new  ArrayList<>();
		for(RandomNumberEntity rn: RandomNumberRepository.findAllPendings()) {
			LocalTime  time = LocalTime.now();
			PendingEntity pending = new PendingEntity(
					rn.getRequestID(), 
					rn.getTimeCreated(), 
					ChronoUnit.SECONDS.between(time, rn.getTimeCreated())
					);
					
			pendings.add(pending);
		}
		return pendings;
	}

	@Override
	public void setThreadPoolSize(int size, ExecutorService executorService) {
		if(size<1 || size>10) {
			throw new IllegalArgumentException("Maximum supported value should be 10 and minimum value should be 1 the the thread Pool size");
		}
	}

	
	
	

}