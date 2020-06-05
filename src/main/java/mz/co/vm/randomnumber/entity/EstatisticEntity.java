package mz.co.vm.randomnumber.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class EstatisticEntity implements Serializable {
	
 
	private static final long serialVersionUID = 1L;

	public EstatisticEntity(Long maximumWaitingTime, Long minimumWaitingTime, Integer totalPendingRequests) {
		super();
		this.maximumWaitingTime = maximumWaitingTime;
		this.minimumWaitingTime = minimumWaitingTime;
		this.totalPendingRequests = totalPendingRequests;
	}

	private Long maximumWaitingTime;
	
	private Long minimumWaitingTime;
	
	private Integer totalPendingRequests;

	public Long getMaximumWaitingTime() {
		return maximumWaitingTime;
	}

	public Long getMinimumWaitingTime() {
		return minimumWaitingTime;
	}

	public Integer getTotalPendingRequests() {
		return totalPendingRequests;
	}
	
	
	
	


}
