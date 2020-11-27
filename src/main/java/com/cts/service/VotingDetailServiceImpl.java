package com.cts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Users;
import com.cts.model.VotesCountDetail;
import com.cts.model.VotesDetail;
import com.cts.repository.UserRepository;

@Service
public class VotingDetailServiceImpl implements VotingDetailService {

	@Autowired
	private UserRepository userRepository;

	public VotesCountDetail getTotalVotesCasted() {

		List<Users> notVoted = userRepository.findByIsVotedIsNull();
		List<Users> voted = userRepository.findByIsVotedNotNull();

		Long notVotedCount = Long.valueOf(0);

		if (notVoted != null)
			notVotedCount = Long.valueOf(notVoted.size());

		Long totalNoOfVoters = Long.valueOf(0);
		Long totalNoOfVotersVoted = Long.valueOf(0);

		if (voted != null)
			totalNoOfVotersVoted = Long.valueOf(voted.size());

		totalNoOfVoters = notVotedCount + totalNoOfVotersVoted;

		VotesCountDetail votesDetail = new VotesCountDetail(totalNoOfVoters, totalNoOfVotersVoted);
		votesDetail.setVotingPercentage(voteCastedPercentage(votesDetail));
		return votesDetail;
	}

	public Double voteCastedPercentage(VotesCountDetail votesDetail) {
		if (votesDetail != null) {
			Double value = (double) (((double) votesDetail.getTotalNoOfVotersVoted())
					/ ((double) votesDetail.getTotalNoOfVoters())) * 100;
			return value;
		}
		return null;
	}

	public List<Users> getVotersOfACandidate(Long candidateId) {
		Optional<Users> candidateOptional = userRepository.findById(candidateId);
		Users candidate = null;

		if (candidateOptional.isPresent())
			candidate = candidateOptional.get();
		if (candidate != null) {
			return candidate.getVoters();
		}
		return null;
	}

	@Override
	public List<VotesDetail> getAllCandidatesVotes() {

		List<Users> allCandidates = userRepository.findByRole(1);
		List<VotesDetail> votesDetails = new ArrayList<>();

		for (Users candidate : allCandidates) {

			VotesDetail voteDetail = new VotesDetail(candidate.getUserId(), candidate.getName(), candidate.getEmail());

			try {

				voteDetail.setParty(candidate.getParty());
				voteDetail.setTotalVotesReceived(Long.valueOf(candidate.getVoters().size()));

			} catch (Exception e) {

				if (candidate.getVoters() == null)
					voteDetail.setTotalVotesReceived(Long.valueOf(0));

			}
			votesDetails.add(voteDetail);
		}
		return votesDetails;
	}
}
