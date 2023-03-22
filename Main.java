import Domain.Friendship;
import Domain.User;
import Domain.validators.FriendshipValidator;
import Domain.validators.UserValidator;
import repository.memory.InMemoryRepository;
import Service.Service0;
import Ui.Console;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<UUID, User> userRepo = new InMemoryRepository<>(new UserValidator());
        InMemoryRepository<UUID, Friendship> friendshipRepo = new InMemoryRepository<>(new FriendshipValidator());

        Service0 service = new Service0(userRepo,friendshipRepo);

        Console console = new Console(service);
        console.runApp();
    }
}