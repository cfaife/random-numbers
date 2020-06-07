package mz.co.vm.randomnumber.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
public class RandomNumberEntity  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private UUID requestID;
	
	private Integer  number;
	
	private Long timeSecs;
	
	private boolean generated;
	
	private LocalTime timeCreated;

	public UUID getRequestID() {
		return requestID;
	}

	public void setRequestID(UUID requestID) {
		this.requestID = requestID;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getTimeSecs() {
		return timeSecs;
	}

	public void setTimeSecs(Long timeSecs) {
		this.timeSecs = timeSecs;
	}

	public boolean isGenerated() {
		return generated;
	}

	public void setGenerated(boolean generated) {
		this.generated = generated;
	}

	public LocalTime getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(LocalTime timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	

}
