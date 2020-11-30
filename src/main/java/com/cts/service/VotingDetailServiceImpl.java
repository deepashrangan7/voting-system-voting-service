package com.cts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Party;
import com.cts.model.Users;
import com.cts.model.VotesCountDetail;
import com.cts.model.VotesDetail;
import com.cts.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotingDetailServiceImpl implements VotingDetailService {

	@Autowired
	private UserRepository userRepository;

	public List<Users> getAllCandidates() {
		List<Users> candidates = null;
		try {
			candidates = userRepository.findByRole(1);
			for (Users user : candidates) {
				Party party = new Party();
				party.setPartyId(user.getParty().getPartyId());
				party.setPartyName(user.getParty().getPartyName());
				user.setParty(party);
			}
		} catch (Exception e) {
			log.error("error while fetching all candidates {}", e.getMessage());
		}
		return candidates;
	}

	public VotesCountDetail getTotalVotesCasted() {

		List<Users> notVoted = userRepository.findByIsVotedIsNull();
		List<Users> voted = userRepository.findByIsVotedNotNull();

		Long notVotedCount = Long.valueOf(0);

		if (notVoted != null)
			notVotedCount = Long.valueOf(notVoted.size());

		Long totalNoOfVoters;
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
			return ((((double) votesDetail.getTotalNoOfVotersVoted()) / ((double) votesDetail.getTotalNoOfVoters()))
					* 100);

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
		return new ArrayList<Users>();
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
		votesDetails.stream().sorted((candidate1, candidate2) -> candidate2.getTotalVotesReceived()
				.compareTo(candidate1.getTotalVotesReceived()));
		return votesDetails;
	}

	@Override
	public Users isVoteCasted(Long userId) {
		if (userId != null) {
			Optional<Users> optionalUser = userRepository.findById(userId);
			if (optionalUser.isPresent()) {
				Users user = optionalUser.get();
				return user.getIsVoted();
			}
		}
		return null;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
