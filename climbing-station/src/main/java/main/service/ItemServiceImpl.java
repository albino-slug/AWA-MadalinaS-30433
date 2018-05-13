package main.service;

import main.model.validator.ItemValidator;
import main.aux.Notification;
import main.model.Item;
import main.model.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemValidator itemValidator;
    @Override
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(Integer id){
        return itemRepository.findById(id);
    }
    @Override
    public List<Item> findByTitle(String title){
        return itemRepository.findByTitle(title);
    }
    @Override
    public List<Item> findByType(ItemType type){
        return itemRepository.findByType(type);
    }
    @Override
    public List<Item> findByCreator(String creator){
        return itemRepository.findByCreator(creator);
    }
    @Override
    public Notification<Boolean> save(Item item){
        Notification<Boolean> saveNotification = new Notification<>();
        try {
            itemRepository.save(item);
            saveNotification.setResult(Boolean.TRUE);
        }
        catch (Exception e){
            saveNotification.addError("unable to save item " + item.toString());
            saveNotification.setResult(Boolean.FALSE);
        }
        return saveNotification;
    }

    @Override
    public Notification<Boolean> deleteById(Integer id){
        Notification<Boolean> deleteNotification = new Notification<>();
        if (id == null){
            deleteNotification.setResult(Boolean.FALSE);
            deleteNotification.addError("item ID cannot be NULL");
            return deleteNotification;
        }
        if (id.intValue()>0){
            try{
                itemRepository.deleteById(id);
                deleteNotification.setResult(Boolean.TRUE);
            }
            catch (Exception e){
                deleteNotification.setResult(Boolean.FALSE);
                deleteNotification.addError("unable to delete item with id " + id);
            }
        }
        else{
            deleteNotification.setResult(Boolean.TRUE);
            deleteNotification.addError("item ID cannot be a negative number");
        }
        return deleteNotification;
    }
}
