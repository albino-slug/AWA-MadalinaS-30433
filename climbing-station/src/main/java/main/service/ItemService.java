package main.service;

import main.aux.Notification;
import main.model.Item;
import main.model.ItemType;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    public List<Item> findAll();
    public List<Item> findByTitle(String title);
    public List<Item> findByType(ItemType type);
    public List<Item> findByCreator(String creator);
    public Optional<Item> findById(Integer id);
    public Notification<Boolean>  deleteById(Integer id);
    public Notification<Boolean>  save(Item item);
}
