package com.springboot.boilerplate.enitity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.springboot.boilerplate.constant.Gender;
import com.springboot.boilerplate.dto.CourseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_detail")
public class UserDetail extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", nullable = false) 
	private String firstName;
	
	@Column(name = "middle_name", nullable = true) 
	private String middleName;
	
	@Column(name = "last_name", nullable = false) 
	private String lastName;
	
	@Column(name = "phone", nullable = false, unique = true) 
	private String phone;
	
	@Column(name = "gender", nullable = false) 
	private Gender gender;
	
	@Column(name = "date_of_birth", nullable = false) 
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "userDetail")
	private User user;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateOfBirth, firstName, gender, id, lastName, middleName, phone, user);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetail other = (UserDetail) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(firstName, other.firstName)
				&& gender == other.gender && Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(middleName, other.middleName) && Objects.equals(phone, other.phone)
				&& Objects.equals(user, other.user);
	}
    
}
