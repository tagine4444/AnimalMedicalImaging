package com.bouacheria.ami.domain;

import java.util.List;

import com.bouacheria.ami.domain.request.ServiceRequest;

public class AMIInterpretation  {

	// Add search criteria.
	
		private List<ServiceRequest> statRequestCompleted;
		private List<ServiceRequest> requestCompleted ;
		
		
		public List<ServiceRequest> getStatRequestCompleted() {
			return statRequestCompleted;
		}
		public void setStatRequestCompleted(List<ServiceRequest> statRequestCompleted) {
			this.statRequestCompleted = statRequestCompleted;
		}
		public List<ServiceRequest> getRequestCompleted() {
			return requestCompleted;
		}
		public void setRequestCompleted(List<ServiceRequest> requestCompleted) {
			this.requestCompleted = requestCompleted;
		}
		
}
