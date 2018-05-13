package main.model.builder;

import main.model.Item;
import main.model.ItemType;

public final class ItemBuilder {
    public Integer id = new Integer(0);
    private String title = "default";
    private String creator = "default";
    private ItemType type = ItemType.ANNOUNCEMENT;
    private String description = "default";

    private ItemBuilder() {
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ItemBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder withCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public ItemBuilder withType(ItemType type) {
        this.type = type;
        return this;
    }

    public ItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Item build() {
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setCreator(creator);
        item.setType(type);
        item.setDescription(description);
        return item;
    }
}
