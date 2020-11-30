package com.cts.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class Users {
	public Users(String name, String email, String password, Integer role, Party party) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.party = party;
	}

	public Users(Long userId, String name, String email, String password, Integer role, Integer active, Users isVoted,
			List<Users> voters, Party party) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.active = active;
		this.isVoted = isVoted;
		this.voters = voters;
		this.party = party;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	@NotBlank(message = "user name should not be blank")
	@NotNull(message = "user name should not be null")
	@Size(max = 20, min = 4, message = "name should me minimum of length 4 and less than or equal to 20")
	@NonNull
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "Email should not be blank")
	@NotNull(message = "Email should not be null")
	@Pattern(regexp = "[a-z0-9._%+-A-Z]+@[a-z0-9.-A-Z]+\\.[a-z]{2,4}$", message = "Type Valid emailId")
	@NonNull
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "password should not be blank")
	@NotNull(message = "password should not be null")
	@Size(max = 15, min = 4, message = "password should me minimum of length 4 and less than or equal to 15")
	@NonNull
	private String password;

	@Column(columnDefinition = "TINYINT", nullable = false)
	@NotNull(message = "must select a role")
	@Min(value = 0, message = "select a valid role")
	@Max(value = 2, message = "select a valid role")
	@NonNull
	private Integer role;

	@Column(columnDefinition = "TINYINT", nullable = false)
	private Integer active = 1;

	@ManyToOne
	@JoinColumn(name = "voted_user_id")
	private Users isVoted;

	@OneToMany(mappedBy = "isVoted")
	private List<Users> voters;

	@ManyToOne
	@JoinColumn(name = "party_id")
	@NonNull
	private Party party;

	@JsonIgnore
	public Users getIsVoted() {
		return isVoted;
	}

	@JsonIgnore
	public List<Users> getVoters() {
		return voters;
	}

	public Party getParty() {
		return party;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", active=" + active + ", party=" + party + "]";
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public void setIsVoted(Users isVoted) {
		this.isVoted = isVoted;
	}

	public void setVoters(List<Users> voters) {
		this.voters = voters;
	}

	public void setParty(Party party) {
		this.party = party;
	}

}
