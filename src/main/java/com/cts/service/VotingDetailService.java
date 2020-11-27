package com.cts.service;

import java.util.List;

import com.cts.model.Users;
import com.cts.model.VotesCountDetail;
import com.cts.model.VotesDetail;

public interface VotingDetailService {
	
	public VotesCountDetail getTotalVotesCasted();

	public Double voteCastedPercentage(VotesCountDetail votesDetail);

	public List<Users> getVotersOfACandidate(Long candidateId);
	
	public List<VotesDetail> getAllCandidatesVotes();
}
