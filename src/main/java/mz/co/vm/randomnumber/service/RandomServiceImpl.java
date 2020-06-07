package mz.co.vm.randomnumber.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.util.concurrent.TimeoutException;
 
import java.util.stream.Stream;

 
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import mz.co.vm.randomnumber.entity.EstatisticEntity;
import mz.co.vm.randomnumber.entity.PendingEntity;
import mz.co.vm.randomnumber.entity.RandomNumberEntity;
 
import mz.co.vm.randomnumber.util.RandomNumberFactory;

/**
 * Implements all the logic for the  <{@link RandomService}>
 * 
 * @author Clerio Alfredo Faife
 *
 */
@Stateless(name="randomService")
public class RandomServiceImpl implements RandomService {
	
	 
	private List<RandomNumberEntity> tasks = new ArrayList<RandomNumberEntity>(); 
	
	private ExecutorService executor =  Executors.newScheduledThreadPool(1);

	 
	
	@Override
	public RandomNumberEntity generateNewRandomNumber(Long xMaxWait) throws InterruptedException, ExecutionException, TimeoutException {
		
		
		Callable<RandomNumberEntity> callable =  this.createThread(xMaxWait);
		
		Future<RandomNumberEntity> future =   executor.submit(callable);
		 
		LocalTime endTime = LocalTime.now();
		
		
		long duration = endTime.toSecondOfDay() - future.get().getTimeCreated().toSecondOfDay();

		if(xMaxWait!=null ) {
				if( !(future.isDone()) 
						&& duration>=xMaxWait 
						&& xMaxWait>31) {
					
					RandomNumberEntity randomNumber = future.get();
					
					randomNumber.setGenerated(false);
					randomNumber.setNumber(null);
					
					tasks.add(randomNumber);
					return randomNumber;
			}
		}
		tasks.add(future.get());
		return future.get();
		
	}
	
	@Override
	public List<RandomNumberEntity> getHistory() {
		return this.tasks;
	}
	
	@Override
	public void cancelRandomRequest(UUID uuid) {
		 if(uuid ==null) {
			 throw new IllegalArgumentException("uuid parameter can not be  nuul");
		 }
		 this.executor.shutdownNow();
	}


	@Override
	public EstatisticEntity getStats() {
 		if(tasks.size()==1) {
 			return new EstatisticEntity(tasks.get(0).getTimeSecs(), tasks.get(0).getTimeSecs(), 1);
 		}
		Stream<RandomNumberEntity> minStream= tasks.stream();
		Optional<Long> min = minStream
					.map(x -> x.getTimeSecs())
					.min((x,y)->x.intValue()-y.intValue());
		
		Stream<RandomNumberEntity> maxStream = tasks.stream();
		Optional<Long> max = maxStream
					.map(x -> x.getTimeSecs())
					.min((x,y)->x.intValue()-y.intValue());

		Stream<RandomNumberEntity> totalStream = tasks.stream();
		Long totalPending  = totalStream
					.filter(x ->!x.isGenerated()).count();
				
		
		return new EstatisticEntity(max.get(), min.get(), totalPending.intValue());
		
	}


	@Override
	public List<PendingEntity> getPendingRequest() {
		
		List<PendingEntity> pendings = new  ArrayList<>();
		LocalTime  time = LocalTime.now();
		tasks.stream().filter(x->!x.isGenerated()).forEach(x->{
			PendingEntity pending = new PendingEntity(
					x.getRequestID(), 
					x.getTimeCreated(), 
					ChronoUnit.SECONDS.between(time, x.getTimeCreated())
					);
			pendings.add(pending);
		});
		
		 
		return pendings;
	}

	@Override
	public void changePoolThreadSize(int size) {
		if(size<1 || size>10) {
			throw new IllegalArgumentException("Maximum supported value should be 10 and minimum value should be 1 the the thread Pool size");
		}
		executor =  Executors.newScheduledThreadPool(size);
	}

	private Callable<RandomNumberEntity> createThread(Long xMaxWait){
		Callable<RandomNumberEntity> callable = () ->{
			RandomNumberEntity randomNumberEntity = new RandomNumberEntity();
			randomNumberEntity.setTimeCreated(LocalTime.now());
			randomNumberEntity.setRequestID(UUID.randomUUID());
			
			 	if(xMaxWait == null) {
					Thread.sleep(30000);
				}else {
					Thread.sleep(xMaxWait);
				}
			 
			
			randomNumberEntity.setNumber(RandomNumberFactory.get());
			randomNumberEntity.setTimeSecs(Long.valueOf(LocalTime.now().toSecondOfDay()));
			return randomNumberEntity;
		};
		return callable;
	}
	
	public  ExecutorService getExecutorService() {
		return this.executor;
	}
	

}
