package main.repository;


import main.model.Item;
import main.model.ItemType;
import main.model.User;
import main.model.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {
    public final static List<Item> items = new ArrayList<>(Arrays.asList(
            new Item("Course 1", "admin1", ItemType.COURSE, "description for course 1"),
            new Item("Course 2", "admin2", ItemType.COURSE, "description for course 2"),
            new Item("Announcement 1", "admin2", ItemType.ANNOUNCEMENT, "description for announcement 1"),
            new Item("Announcement 2", "admin1", ItemType.ANNOUNCEMENT, "description for announcement 2"),
            new Item("Event 1", "admin1", ItemType.EVENT, "description for event 1"),
            new Item("Review 1", "user2", ItemType.REVIEW, "description for review 1")
    ));
    public final static List<User> users = new ArrayList<>(Arrays.asList(
            new User("admin1", 40, "123",Role.ADMIN),
            new User("admin2", 36, "123",Role.ADMIN),
            new User("user1", 16, "123",Role.USER),
            new User("user2", 21, "123",Role.USER)
    ));
    public final static Item newItem = new Item("new item", "admin1", ItemType.ANNOUNCEMENT, "description for new announcement");
    public final static User newUser =  new User("new user", 50, "123", Role.USER);
}
