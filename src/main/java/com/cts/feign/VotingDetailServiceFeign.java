package com.cts.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.model.Users;
import com.cts.model.VotesCountDetail;
import com.cts.model.VotesDetail;

@FeignClient(name="voting-system-voting-service")
public interface VotingDetailServiceFeign {
	
	@GetMapping("/votecountdetails")
	public VotesCountDetail getVotingCountDetails();

	@GetMapping("/votedetails/{candidate_id}")
	public List<Users> votersForACandidate(@PathVariable("candidate_id") Long candidateId);

	@GetMapping("/votedetails")
	public List<VotesDetail> getVotesDetails();
}
