package mz.co.vm.randomnumber.service;

import java.util.Random;
import java.util.UUID;

import mz.co.vm.randomnumber.entity.RandomNumberEntity;
/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
public class InnerTread implements Runnable{
	
	private RandomNumberEntity  randomNumber;
	
	InnerTread(RandomNumberEntity  randomNumber){
		this.randomNumber = randomNumber;
	};

	@Override
	public void run() {
	
		Random random  = new Random();
		this.randomNumber.setNumber(random.nextInt(900) + 3);
		this.randomNumber.setRequestID(UUID.randomUUID());		
	}
	
	public RandomNumberEntity getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(RandomNumberEntity randomNumber) {
		this.randomNumber = randomNumber;
	}
	

}
