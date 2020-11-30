package com.cts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.model.Status;
import com.cts.model.Users;
import com.cts.service.VotingService;

@SpringBootTest
class CastVoteControllerTest {
	@Mock
	VotingService votingService;

	@Test
	void test() {
		when(votingService.castVote(Long.valueOf(1), Long.valueOf(2))).thenReturn(new Status(200, "success"));
		CastVoteController cvc = new CastVoteController();
		cvc.setVotingService(votingService);
		Users user = new Users();
		user.setUserId(Long.valueOf(1));
		assertEquals(200, cvc.castVote(user, Long.valueOf(2)).getStatusCode());
	}

}
