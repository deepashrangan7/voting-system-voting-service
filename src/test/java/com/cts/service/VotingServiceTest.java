package com.cts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.model.Users;
import com.cts.repository.UserRepository;

@SpringBootTest
class VotingServiceTest {

	@Mock
	UserRepository userRepository;

	@Test
	void castVoteTestSuccess() {
		Users user = new Users();
		Users user2 = new Users();

		when(userRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(user));
		when(userRepository.findById(Long.valueOf(2))).thenReturn(Optional.of(user2));
		when(userRepository.save(user)).thenReturn(user);
		VotingServiceImpl vs = new VotingServiceImpl();
		vs.setUserRepository(userRepository);
		assertEquals(200, vs.castVote(Long.valueOf(1), Long.valueOf(2)).getStatusCode());
	}

	@Test
	void castVoteTestFailure() {
		Users user = new Users();
		Users user2 = new Users();
		user2.setIsVoted(user);
		when(userRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(user));
		when(userRepository.findById(Long.valueOf(2))).thenReturn(Optional.of(user2));
		when(userRepository.save(user)).thenReturn(user);
		VotingServiceImpl vs = new VotingServiceImpl();
		vs.setUserRepository(userRepository);
		assertEquals(500, vs.castVote(Long.valueOf(1), Long.valueOf(2)).getStatusCode());
	}

	@Test
	void castVoteTestFailure2() {
		Users user = new Users();
		Users user2 = new Users();
		user2.setIsVoted(user);
		when(userRepository.findById(null)).thenThrow(new IllegalArgumentException());
		when(userRepository.findById(Long.valueOf(2))).thenReturn(Optional.of(user2));
		when(userRepository.save(user)).thenReturn(user);
		VotingServiceImpl vs = new VotingServiceImpl();
		vs.setUserRepository(userRepository);
		assertEquals(500, vs.castVote(Long.valueOf(1), Long.valueOf(2)).getStatusCode());
	}
}
