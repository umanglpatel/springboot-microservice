package com.demo.user.data.vo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String phoneNo;

	public User() {
	}

	public User(String firstName, String phoneNo) {
		this.firstName = firstName;
		this.phoneNo = phoneNo;
	}

	public User(String firstName, String lastName, String phoneNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "phone_no", nullable = false, length = 15)
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
