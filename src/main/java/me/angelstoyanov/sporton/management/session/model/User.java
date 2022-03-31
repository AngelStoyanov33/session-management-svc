package me.angelstoyanov.sporton.management.session.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Objects;

@JsonRootName("user")
@JsonPropertyOrder({"user_id", "email", "first_name", "last_name", "location"})
public class User {

    @JsonProperty(value = "user_id", required = true)
    private String userId;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "first_name", required = true)
    @BsonProperty("first_name")
    private String firstName;

    @JsonProperty(value = "last_name", required = true)
    @BsonProperty("last_name")
    private String lastName;

    @JsonProperty("location")
    private String location;

    public User() {
    }

    public User(String email, String firstName, String lastName, String location) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
    }

    public User(User other) {
        this.email = other.getEmail();
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.location = other.getLocation();
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(location, user.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, firstName, lastName, location);
    }

    @Override
    public String toString() {
        return "{" +
                "user_id=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
