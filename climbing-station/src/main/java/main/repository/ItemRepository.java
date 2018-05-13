package main.repository;

import main.model.Item;
import main.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByTitle(String title);
    List<Item> findByType(ItemType type);
    List<Item> findByCreator(String creator);
}
