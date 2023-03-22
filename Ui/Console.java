package Ui;

import Domain.Friendship;
import Domain.User;
import Service.Service0;

import java.util.Scanner;

public class Console {
    private final Service0 service;
    private Scanner scanner;

    public Console(Service0 service) {
        this.service = service;
        scanner = new Scanner(System.in);
    }
    private void showmenu(){

        System.out.println("1. Adauga un user.");
        System.out.println("2. Sterge un user. ");
        System.out.println("3. Sterge o prietenie");
        System.out.println("4. Adauga o prietenie");
        System.out.println("5. Afiseaza toti userii");
        System.out.println("6. Afiseaza toate prieteniile");
        System.out.println("0. Exit");
    }
    private void values(){
        User u1 = new User("Andra", "Chereji", "andra.02","1234");
        User u2 = new User("Sergiu", "Campeanu", "sncamp","1174");
        User u3 = new User("Sara", "Pop", "srp","1758");
        User u4 = new User("Bianca", "Topan", "btopan","8888");
        User u5 = new User("Lavinia", "Ionescu", "lavi","1546");

        this.service.addUser(u1);
        this.service.addUser(u2);
        this.service.addUser(u3);
        this.service.addUser(u4);
        this.service.addUser(u5);

        this.service.createFriendship(u1.getUserName(), u2.getUserName());
        this.service.createFriendship(u1.getUserName(), u3.getUserName());
        this.service.createFriendship(u3.getUserName(), u4.getUserName());
        this.service.createFriendship(u4.getUserName(), u5.getUserName());
        this.service.createFriendship(u3.getUserName(), u5.getUserName());

    }

    public void runApp(){
        values();
        scanner = new Scanner(System.in);
        while(true){

            showmenu();
            System.out.println("Introduceti optiunea: ");
            int opt = scanner.nextInt();
            try {
                switch (opt) {
                    case 0:
                        scanner.close();
                        return;
                    case 1:
                        User u = readu();
                        service.addUser(u);
                        break;
                    case 2:
                        String email = readuname();
                        service.deleteUser(email);
                        break;
                    case 3:
                        String usern1 = readuname();
                        String usern2 = readuname();
                        service.deleteFriendship(usern1, usern2);
                        break;
                    case 4:
                        usern1 = readuname();
                        usern2 = readuname();
                        service.createFriendship(usern1, usern2);
                        break;
                    case 5:
                        Iterable<User> users = service.getAllUsers();
                        users.forEach(System.out::println);
                        break;
                    case 6:
                        Iterable<Friendship> friendships = service.getAllFriendships();
                        friendships.forEach(System.out::println);
                        break;
                    default:
                        System.out.println("Comanda gresita");

                }
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    public User readu() {
        System.out.println("Introduceti numele: ");
        String firstname = scanner.next();
        System.out.println("Introduceti prenumele: ");
        String lastname = scanner.next();
        System.out.println("Introduceti username-ul: ");
        String username = scanner.next();
        System.out.println("Introduceti parola: ");
        String parola = scanner.next();
        User u = new User(firstname, lastname, username,parola);
        return u;
    }

    public String readuname() {
        System.out.print("Give the username: ");
        String username = scanner.next();
        return username;
    }
}

