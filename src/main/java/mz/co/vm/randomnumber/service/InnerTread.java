package mz.co.vm.randomnumber.service;

import java.util.Random;
import java.util.UUID;

import mz.co.vm.randomnumber.entity.RandomNumberEntity;

public class InnerTread implements Runnable{
	
	private RandomNumberEntity  randomNumber;
	
	InnerTread(RandomNumberEntity  randomNumber){
		this.randomNumber = randomNumber;
	};

	@Override
	public void run() {
	
		Random r  = new Random();
		this.randomNumber.setNumber(r.nextInt());
		this.randomNumber.setRequestID(UUID.randomUUID());		
	}
	
	public RandomNumberEntity getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(RandomNumberEntity randomNumber) {
		this.randomNumber = randomNumber;
	}
	

}
