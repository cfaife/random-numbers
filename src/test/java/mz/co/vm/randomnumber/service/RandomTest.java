package mz.co.vm.randomnumber.service;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class RandomTest {
	
	@Test
	public  void testRandom_shouldBeHigherThen3() {
		Random random  = new Random();
		int generated = random.nextInt(900) + 3;
		System.out.println(generated);
		assertTrue(generated>3);
		

	}
	

}
