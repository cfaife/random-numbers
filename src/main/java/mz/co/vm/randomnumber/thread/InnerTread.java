package mz.co.vm.randomnumber.thread;

import java.time.LocalTime;
import java.util.UUID;

import mz.co.vm.randomnumber.entity.RandomNumberEntity;
import mz.co.vm.randomnumber.util.RandomNumberFactory;

public class InnerTread implements Runnable{

	private RandomNumberEntity randomNumberEntity;
	
	private Long xMaxWait;
	
	public InnerTread(RandomNumberEntity randomNumberEntity,Long xMaxWait) {
		this.randomNumberEntity= randomNumberEntity;
		this.xMaxWait = xMaxWait;
	}
	
	@Override
	public void run() {
		
		randomNumberEntity.setTimeCreated(LocalTime.now());
		randomNumberEntity.setRequestID(UUID.randomUUID());
		
		try {
			if(xMaxWait == null) {
				Thread.sleep(30000);
			}else {
				Thread.sleep(xMaxWait);
			}
		} catch (InterruptedException e) {
			 
			e.printStackTrace();
		}
		
		randomNumberEntity.setNumber(RandomNumberFactory.get());
		
	}
	
	public RandomNumberEntity getRandomNumberEntity() {
		return this.randomNumberEntity;
	}
	
	public InnerTread getByUUID(UUID uuid ){
		if(uuid.equals(this.randomNumberEntity.getRequestID())) {
			return  this;	
		}
		return  null;
	}

}
