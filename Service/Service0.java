package Service;

import Domain.Entity;
import Domain.Friendship;
import Domain.User;
import Domain.validators.ValidationException;
import repository.Repository0;

import java.util.UUID;

public class Service0 implements Service<UUID>{

    private Repository0<UUID,User> utilizatorRepo;
    private Repository0<UUID,Friendship> friendshipRepo;

    public Service0(Repository0<UUID, User> userRepo, Repository0<UUID, Friendship> friendshipRepo) {
        this.utilizatorRepo = userRepo;
        this.friendshipRepo = friendshipRepo;

    }

    /**
     *
     * @param user - the entity that has to be added
     * returns true if the entity is added
     * returns false if the entity isn't added
     */
    @Override
    public boolean addUser(User user) {
        Entity<UUID> u = null;

        if(user.getUserName()==null)
            throw new IllegalArgumentException("The username mustn't be null!");
        else if (getUbyUsername(user.getUserName())!=null)
            throw new IllegalArgumentException("The username is taken! Try another one:)");

        utilizatorRepo.save(user);



        if (u != null) {
            throw new ValidationException("There is another user with this ID");

        }

        return true;
    }

    /**
     *
     * @param username the id of the entity that we have to remove
     * returns the user if we found one
     * and null otherwise
     */
    @Override
    public Entity<UUID> deleteUser(String username) {
        User u = null;

        u = getUbyUsername(username);
        if (u == null) {
            throw new ValidationException("There is no user with this username!");
        }
        for (User friend : u.getFriends()) {
            for (Friendship f : friendshipRepo.findAll())
                if ((f.getUser1().equals(u) && f.getUser2().equals(friend)) || (f.getUser1().equals(friend) && f.getUser2().equals(u))) {
                    friendshipRepo.delete(f.getId());
                    break;
                }
            friend.removeFriend(u);
        }
        u = (User) utilizatorRepo.delete(u.getId());


        if (u == null) {
            throw new ValidationException("There is no user with this ID!");
        }

        return u;
    }

    /**
     *
     * @param username1 -
     * @param Username2 the usernames of the user we have to create a friendship between
     *
     * @return
     */
    @Override
    public boolean createFriendship(String username1, String Username2) {
        Entity<UUID> f = null;
        User u1, u2;

        if (username1 == null || Username2 == null)
            throw new IllegalArgumentException("Usernames must not be null!");

        u1 = getUbyUsername(username1);
        u2 = getUbyUsername(Username2);
        if (u1 == null || u2 == null || u1.equals(u2))
            throw new ValidationException("There are no two users with these two usernames!");
        for(User friend:u1.getFriends())
        {
            if(friend == u2)
                throw new ValidationException("These two users are already friends!");
        }
        f = friendshipRepo.save(new Friendship(u1, u2));


        if (f != null) {
            throw new ValidationException("These two users are already friends!");

        }
        u1.addFriend(u2);
        u2.addFriend(u1);
        return true;
    }

    /**
     *
     * @param username1 -
     * @param Username2 the usernames of the user we have to create a friendship between
     * @return the friendship if it exists
     *       null otherwise
     */
    @Override
    public Entity<UUID> deleteFriendship(String username1, String Username2) {
        Entity<UUID> f = null;
        User u1, u2;

        if (username1 == null || Username2 == null)
            throw new IllegalArgumentException("Usernames must not be null!");

        u1 = getUbyUsername(username1);
        u2 = getUbyUsername(Username2);
        if (u1 == null || u2 == null || u1.equals(u2))
            throw new ValidationException("There are no two users with these two usernames!");

        Iterable<Friendship> friendships = friendshipRepo.findAll();
        for (Friendship fr : friendships)
            if ((u1==fr.getUser1() && u2==fr.getUser2()) ||(u2==fr.getUser1() && u1==fr.getUser2())){
                f = friendshipRepo.delete(fr.getId());
                break;
            }

        if (f == null) {
            throw new ValidationException("These users are not friends!");

        }
        u1.removeFriend(u2);
        u2.removeFriend(u1);
        return f;
    }

    /**
     *
     * @return an Iterable of all the users
     */
    @Override
    public Iterable<User> getAllUsers() {
        return utilizatorRepo.findAll();
    }

    /**
     *
     * @return an Iterable of all the friendships
     */
    @Override
    public Iterable<Friendship> getAllFriendships() {
        return friendshipRepo.findAll();
    }

    public User getUbyUsername(String username){
        Iterable<User> utilizators = utilizatorRepo.findAll();
        for (User u : utilizators)
            if (u.getUserName().equals(username))
                return u;
        return null;
    }

}
