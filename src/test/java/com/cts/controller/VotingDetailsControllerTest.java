package com.cts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.model.VotesCountDetail;
import com.cts.service.VotingDetailService;

@SpringBootTest
class VotingDetailsControllerTest {

	@Mock
	VotingDetailService vds;

	@Test
	void getVotingCountDetailsTest() {
		VotingDetailsController vdc = new VotingDetailsController();
		VotesCountDetail vcd = new VotesCountDetail(Long.valueOf(1), Long.valueOf(1));
		when(vds.getTotalVotesCasted()).thenReturn(vcd);

		vdc.setVotingDetailService(vds);
		assertEquals(Long.valueOf(1), vdc.getVotingCountDetails().getTotalNoOfVoters());
	}

	@Test
	void votersForACandidateTest() {
		VotingDetailsController vdc = new VotingDetailsController();
		when(vds.getVotersOfACandidate(Long.valueOf(1))).thenReturn(null);
		vdc.setVotingDetailService(vds);
		assertNull(vdc.votersForACandidate(Long.valueOf(1)));
	}

	@Test
	void getVotesDetailsTest() {
		VotingDetailsController vdc = new VotingDetailsController();
		when(vds.getAllCandidatesVotes()).thenReturn(null);
		vdc.setVotingDetailService(vds);
		assertNull(vdc.getVotesDetails());
	}

	@Test
	void getAllCandidatesTest() {
		VotingDetailsController vdc = new VotingDetailsController();
		when(vds.getAllCandidates()).thenReturn(null);
		vdc.setVotingDetailService(vds);
		assertNull(vdc.getAllCandidates());

	}

	@Test
	void getVoteStatusTest() {
		VotingDetailsController vdc = new VotingDetailsController();
		when(vds.isVoteCasted(Long.valueOf(1))).thenReturn(null);
		vdc.setVotingDetailService(vds);
		assertNull(vdc.getVoteStatus(Long.valueOf(1)));

	}

}
