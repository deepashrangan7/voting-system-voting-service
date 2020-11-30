package com.cts.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.model.Party;
import com.cts.model.Users;
import com.cts.model.VotesCountDetail;
import com.cts.repository.UserRepository;

@SpringBootTest
class VotingDetailsServiceTest {

	@Mock
	UserRepository userRepository;

	@Test
	void isVoteCastedTest() {
		Users user = new Users();

		when(userRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(user));

		VotingDetailServiceImpl vd = new VotingDetailServiceImpl();
		vd.setUserRepository(userRepository);
		assertNull(vd.isVoteCasted(Long.valueOf(1)));
	}

	@Test
	void isVoteCastedTestNull() {

		when(userRepository.findById(null)).thenReturn(null);

		VotingDetailServiceImpl vd = new VotingDetailServiceImpl();
		vd.setUserRepository(userRepository);
		assertNull(vd.isVoteCasted(null));
	}

	@Test
	void isVoteCastedTestNull2() {

		when(userRepository.findById(Long.valueOf(1))).thenReturn(null);

		VotingDetailServiceImpl vd = new VotingDetailServiceImpl();
		vd.setUserRepository(userRepository);
		assertNull(vd.isVoteCasted(null));
	}

	@Test
	void getVotersOfACandidate() {
		Users user = new Users();

		when(userRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(user));

		VotingDetailServiceImpl vd = new VotingDetailServiceImpl();
		vd.setUserRepository(userRepository);
		assertNull(vd.getVotersOfACandidate(Long.valueOf(1)));

	}

	@Test
	void voteCastedPercentageTest() {
		VotesCountDetail vcd = new VotesCountDetail(Long.valueOf(10), Long.valueOf(10));
		VotingDetailServiceImpl vd = new VotingDetailServiceImpl();
		assertEquals(Double.compare(100, vd.voteCastedPercentage(vcd)),
				Double.compare(100, vd.voteCastedPercentage(vcd)));
	}

	@Test
	void voteCastedPercentageTestNull() {
		VotesCountDetail vcd = null;
		VotingDetailServiceImpl vd = new VotingDetailServiceImpl();
		assertNull(vd.voteCastedPercentage(vcd));
	}

	@Test
	void getAllCandidatesTest() {
		Party party = new Party(Long.valueOf(1), "A", null);
		List<Users> allUsers = new ArrayList<Users>();
		Party party2 = new Party(Long.valueOf(2), "B", null);
		Users user = new Users();

		user.setParty(party);
		allUsers.add(user);
		user = new Users();
		user.setParty(party2);
		allUsers.add(user);

		when(userRepository.findByRole(1)).thenReturn(allUsers);
		VotingDetailServiceImpl vds = new VotingDetailServiceImpl();
		vds.setUserRepository(userRepository);
		assertArrayEquals(allUsers.toArray(), vds.getAllCandidates().toArray());
	}

	@Test
	void getAllCandidatesTest2() {
		Party party = null;

		List<Users> allUsers = new ArrayList<Users>();

		Party party2 = new Party(Long.valueOf(2), "B", null);

		Users user = new Users();

		user.setParty(party);
		allUsers.add(user);
		user = new Users();
		user.setParty(party2);
		allUsers.add(user);

		when(userRepository.findByRole(1)).thenReturn(null);
		VotingDetailServiceImpl vds = new VotingDetailServiceImpl();
		vds.setUserRepository(userRepository);
		assertNull(vds.getAllCandidates());
	}

	@Test
	void getTotalVotesCastedTest() {
		List<Users> voted = new ArrayList<Users>();
		List<Users> notVoted = new ArrayList<Users>();

		Users user = new Users();

		voted.add(user);
		user = new Users();
		notVoted.add(user);
		when(userRepository.findByIsVotedIsNull()).thenReturn(notVoted);

		when(userRepository.findByIsVotedNotNull()).thenReturn(voted);

		VotingDetailServiceImpl vds = new VotingDetailServiceImpl();
		vds.setUserRepository(userRepository);
		assertEquals(Long.valueOf(2), vds.getTotalVotesCasted().getTotalNoOfVoters());
	}

	@Test
	void getTotalVotesCastedTest2() {

		when(userRepository.findByIsVotedIsNull()).thenReturn(null);

		when(userRepository.findByIsVotedNotNull()).thenReturn(null);

		VotingDetailServiceImpl vds = new VotingDetailServiceImpl();
		vds.setUserRepository(userRepository);
		assertEquals(Long.valueOf(0), vds.getTotalVotesCasted().getTotalNoOfVoters());
	}

	@Test
	void getAllCandidatesVotes() {
		List<Users> users = new ArrayList<Users>();
		Users user1 = new Users("deepash1", "dee1pash@gmail.com", "12341", 1, new Party(Long.valueOf(2), "B", null));

		List<Users> voter = new ArrayList<Users>();
		voter.add(user1);
		Users user = new Users("deepash", "deepash@gmail.com", "1234", 1, new Party(Long.valueOf(1), "A", null));
		user.setVoters(voter);
		users.add(user);
		when(userRepository.findByRole(1)).thenReturn(users);
		VotingDetailServiceImpl vds = new VotingDetailServiceImpl();
		vds.setUserRepository(userRepository);

		assertEquals(1, vds.getAllCandidatesVotes().size());
	}

	@Test
	void getAllCandidatesVotes2() {
		List<Users> users = new ArrayList<Users>();

		Users user = new Users("deepash", "deepash@gmail.com", "1234", 1, new Party(Long.valueOf(1), "A", null));
		user.setVoters(null);
		users.add(user);
		when(userRepository.findByRole(1)).thenReturn(users);
		VotingDetailServiceImpl vds = new VotingDetailServiceImpl();
		vds.setUserRepository(userRepository);

		assertEquals(1, vds.getAllCandidatesVotes().size());
	}
}