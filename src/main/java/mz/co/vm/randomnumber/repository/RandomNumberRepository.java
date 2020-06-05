package mz.co.vm.randomnumber.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import mz.co.vm.randomnumber.entity.RandomNumberEntity;
/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
@Singleton
@Startup
public class RandomNumberRepository {
	
	private   List<RandomNumberEntity> randomNumberEntities = new ArrayList<>();
	
	public   RandomNumberEntity save(RandomNumberEntity randomNumberEntity){
		randomNumberEntities.add(randomNumberEntity);
		return randomNumberEntity;
	}
	/**
	 * Returns a List of Generated Random Numbers.
	 * This includes the time that was taken to process
	 * each random number
	 * @return list of history
	 */
	public   List<RandomNumberEntity>  getHistory(){
		return  randomNumberEntities.stream()
					.filter(random -> random.isGenerated())
					.collect(Collectors.toList());
	}
	
	public   List<RandomNumberEntity>  findAll(){
		return  randomNumberEntities;
	}
	
	public   List<RandomNumberEntity> findAllPendings(){
		return  randomNumberEntities.stream()
					.filter(random -> !random.isGenerated())
					.collect(Collectors.toList());
	}

}
