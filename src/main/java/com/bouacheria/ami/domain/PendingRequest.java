package com.bouacheria.ami.domain;

import java.util.List;

import com.bouacheria.ami.domain.request.ServiceRequest;

public class PendingRequest {

		private List<ServiceRequest> requests;
		private List<ServiceRequest> statRequests;

		public List<ServiceRequest> getRequests() {
			return requests;
		}

		public void setRequests(List<ServiceRequest> requests) {
			this.requests = requests;
		}

		public List<ServiceRequest> getStatRequests() {
			return statRequests;
		}

		public void setStatRequests(List<ServiceRequest> statRequests) {
			this.statRequests = statRequests;
		}
}
