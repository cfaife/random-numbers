package mz.co.vm.randomnumber.util;

import java.util.Random;
/**
 * Factory for random numbers
 * @author cfaife
 *
 */
public class RandomNumberFactory {
	/**
	 * Every  the method is called, it generates a random  number higher than 3
	 * @return a random number
	 */
	public  static Integer get() {
		Random random  = new Random();
		Integer number  =random.nextInt(20000) + 3;
		return  number;
	}

}
