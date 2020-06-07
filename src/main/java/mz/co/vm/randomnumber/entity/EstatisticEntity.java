package mz.co.vm.randomnumber.entity;

import java.io.Serializable;

/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
public final class EstatisticEntity implements Serializable {
	
 
	private static final long serialVersionUID = 1L;

	public EstatisticEntity(Long maximumWaitingTime, Long minimumWaitingTime, Integer totalPendingRequests) {
		 
		this.maximumWaitingTime = maximumWaitingTime;
		this.minimumWaitingTime = minimumWaitingTime;
		this.totalPendingRequests = totalPendingRequests;
	}

	private Long maximumWaitingTime;
	
	private Long minimumWaitingTime;
	
	private Integer totalPendingRequests;

	public Long getMaximumWaitingTime() {
		return this.maximumWaitingTime;
	}

	public Long getMinimumWaitingTime() {
		return this.minimumWaitingTime;
	}

	public Integer getTotalPendingRequests() {
		return this.totalPendingRequests;
	}
	
}
