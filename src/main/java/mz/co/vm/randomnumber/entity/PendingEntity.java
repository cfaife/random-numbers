package mz.co.vm.randomnumber.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

/**
 * 
 * @author Clerio Alfredo Faife
 *
 */

public final class PendingEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	public PendingEntity(UUID requestId, LocalTime timeCreated, Long waitingTime) {
		super();
		this.requestId = requestId;
		this.timeCreated = timeCreated;
		this.waitingTime = waitingTime;
	}

	private UUID requestId;
	
	private LocalTime timeCreated;
	
	private Long waitingTime;

	public UUID getRequestId() {
		return requestId;
	}

	public LocalTime getTimeCreated() {
		return timeCreated;
	}

	public Long getWaitingTime() {
		return waitingTime;
	}
	
	
	
	
	
	
	
	

}
