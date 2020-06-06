package mz.co.vm.randomnumber.service.unit;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import mz.co.vm.randomnumber.util.RandomNumberFactory;

public class RandomTest {
	
	@Test
	public  void testRandom_shouldBeHigherThen3() {
 		int generated = RandomNumberFactory.get();

		System.out.println(generated);
		assertTrue(generated>3);
		
	}
	
}
