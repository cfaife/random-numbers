package mz.co.vm.randomnumber.util;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RandomNumberFactoryTest {
	
	@Test
	public  void testRandom_shouldBeHigherThen3() {
 		
		int generated = RandomNumberFactory.get();

		assertTrue(generated>3);
		
	}
	
	@Test
	public  void testMultipleRandomNumebers_shouldAllNumbersBeHigherThen3() {
 		
		Set<Integer> numbers = new HashSet<>();
		
 		for (int i=0;i<50;i++) {
			int generated = RandomNumberFactory.get();
			numbers.add(generated);
 		}
		
		numbers.forEach(num->{assertTrue(num>3);});
		
	}
	
}
