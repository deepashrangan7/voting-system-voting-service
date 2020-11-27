package com.cts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.model.Status;
import com.cts.model.Users;
import com.cts.repository.UserRepository;

@Service
public class VotingServiceImpl implements VotingService {
	@Autowired
	private UserRepository userRepository;

	public Status castVote(Long candidateId, Long userId) {

		try {
			Optional<Users> candidateOptional = userRepository.findById(candidateId);
			Users candidate = null;

			if (candidateOptional.isPresent())
				candidate = candidateOptional.get();

			if (candidate != null) {

				Optional<Users> optionalUser = userRepository.findById(userId);

				if (optionalUser.isPresent()) {
					Users votedUser = optionalUser.get();

					if (votedUser.getIsVoted() == null) {
						votedUser.setIsVoted((candidate));
						userRepository.save(votedUser);
						return new Status(200, "Vote casted successfully");

					} else {
						return new Status(500, "Vote casted Already");

					}
				}
			}

		} catch (Exception e) {
			return new Status(500, "unable to cast vote " + e.getMessage());
		}

		return new Status(500, "unable to cast vote ");
	}

}
