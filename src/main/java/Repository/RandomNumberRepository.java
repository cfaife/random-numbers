package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mz.co.vm.randomnumber.entity.RandomNumberEntity;
/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
public class RandomNumberRepository {
	
	private static List<RandomNumberEntity> randomNumberEntities = new ArrayList<>();
	
	public static RandomNumberEntity save(RandomNumberEntity randomNumberEntity){
		randomNumberEntities.add(randomNumberEntity);
		return randomNumberEntity;
	}
	/**
	 * Returns a List of Generated Random Numbers.
	 * This includes the time that was taken to process
	 * each random number
	 * @return list of history
	 */
	public static List<RandomNumberEntity>  getHistory(){
		return  randomNumberEntities.stream()
					.filter(random -> random.isGenerated())
					.collect(Collectors.toList());
	}
	
	public static List<RandomNumberEntity>  findAll(){
		return  randomNumberEntities;
	}
	
	public static List<RandomNumberEntity> findAllPendings(){
		return  randomNumberEntities.stream()
					.filter(random -> !random.isGenerated())
					.collect(Collectors.toList());
	}

}
