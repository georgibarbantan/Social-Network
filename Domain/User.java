package Domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class User extends Entity<UUID>{
    private String firstName;
    private String lastName;
    private String userName;
    private String pasword;
    private Map<UUID,User> friends;

    public User(String firstName, String lastName,String userName,String pasword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName=userName;
        this.pasword=pasword;
        this.setId(UUID.randomUUID());
        friends = new HashMap<>();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public Iterable<User> getFriends() {
        return friends.values();
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username=" + userName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(userName, that.userName) && Objects.equals(pasword, that.pasword) && Objects.equals(friends, that.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getFriends());
    }

    public void addFriend(User u) {
        friends.put(u.getId(),u);
    }
    public void removeFriend(User u) {
        friends.remove(u.getId());
    }
}
