package com.bayzdelivery.model;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "person")
public class Person implements Serializable{

  private static final long serialVersionUID = 432154291451321L;

  public Person() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull
  @Column(name = "name")
  String name;

  @NotNull
  @Email
  @Column(name = "email",unique = true)
  String email;

  // I don't believe this column is needed , but I  will leave it because the task doesn't explain it is full use
  // if we need a unique number the id should be enough, but since it is here I will make a function that generates a registeration number
  @NotNull
  @Column(name = "registration_number",unique = true)
  String registrationNumber;

  @ManyToOne
  @JoinColumn(name = "person_type_id")
  PersonType personType;

  public Long getId() {
    return id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public PersonType getPersonType() {
    return personType;
  }

  public void setPersonType(PersonType personType) {
    this.personType = personType;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (registrationNumber == null) {
      if (other.registrationNumber != null)
        return false;
    } else if (!registrationNumber.equals(other.registrationNumber))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", name=" + name + ", email=" + email + ", registrationNumber=" + registrationNumber + "]";
  }




}
