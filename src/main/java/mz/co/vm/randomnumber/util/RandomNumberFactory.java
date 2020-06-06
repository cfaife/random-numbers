package mz.co.vm.randomnumber.util;

import java.util.Random;

public class RandomNumberFactory {
	
	public  static Integer get() {
		Random random  = new Random();
		Integer number  =random.nextInt(20000) + 3;
		return  number;
	}

}
