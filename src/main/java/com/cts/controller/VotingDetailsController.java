package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Users;
import com.cts.model.VotesCountDetail;
import com.cts.model.VotesDetail;
import com.cts.service.VotingDetailService;
import com.cts.service.VotingDetailServiceImpl;

@RestController
public class VotingDetailsController {

	@Autowired
	private VotingDetailService votingDetailService;

	@GetMapping("/votecountdetails")
	public VotesCountDetail getVotingCountDetails() {
		return votingDetailService.getTotalVotesCasted();
	}

	@GetMapping("/votedetails/{candidate_id}")
	public List<Users> votersForACandidate(@PathVariable("candidate_id") Long candidateId) {
		return votingDetailService.getVotersOfACandidate(candidateId);
	}

	@GetMapping("/votedetails")
	public List<VotesDetail> getVotesDetails() {
		return votingDetailService.getAllCandidatesVotes();
	}

}
