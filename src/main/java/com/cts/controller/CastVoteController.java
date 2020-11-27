package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cts.model.Status;
import com.cts.model.Users;
import com.cts.service.VotingService;

@RestController
public class CastVoteController {

	@Autowired
	private VotingService votingService;

	@PostMapping("/vote/{user_id}")
	public Status castVote(@RequestBody Users candidate, @PathVariable("user_id") Long userId) {
		return votingService.castVote(candidate.getUserId(), userId);
	}
	
	


}
