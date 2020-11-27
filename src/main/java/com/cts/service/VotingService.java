package com.cts.service;

import com.cts.model.Status;

public interface VotingService {
	public Status castVote(Long candidateId, Long userId);
}
