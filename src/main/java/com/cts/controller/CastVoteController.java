package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cts.model.Status;
import com.cts.model.Users;
import com.cts.service.VotingService;

@RestController

@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CastVoteController {

	@Autowired
	private VotingService votingService;

	@PostMapping("/vote/{user_id}")
	public Status castVote(@RequestBody Users candidate, @PathVariable("user_id") Long userId) {
		System.out.println("casting vote try");
		return votingService.castVote(candidate.getUserId(), userId);
	}
	
	


}
