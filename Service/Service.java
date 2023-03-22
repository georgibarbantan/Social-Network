package Service;

import Domain.Entity;
import Domain.Friendship;
import Domain.User;

public interface Service<ID> {
    /**
     *
     * @param user - the entity that has to be added
     * @return true if the entity was added
     *         false otherwise
     */
    boolean addUser(User user);

    /**
     *
     * @param username the id of the entity that we have to remove
     * @return the entity that was removed if there was any
     *         null otherwise
     */
    Entity<ID> deleteUser(String username);

    /**
     *
     * @param username1 -
     * @param Username2 the usernames of the user we have to create a friendship between
     *
     * @return true if the entity was added
     *      *         false otherwise
     */
    boolean createFriendship(String username1,String Username2);

    /**
     *
     * @param username1 -
     * @param Username2 the usernames of the user we have to create a friendship between
     * @return the friendship if it exists
     *                null otherwise
     */
    Entity<ID> deleteFriendship(String username1,String Username2);

    /**
     *
     * @return Iterable of all users
     */
    Iterable<User> getAllUsers();

    /**
     *
     * @return Iterable of all friendships
     */
    Iterable<Friendship> getAllFriendships();
}
