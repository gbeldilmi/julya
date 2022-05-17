package julya.db;

import java.util.ArrayList;

public class Table {
  private ArrayList<Item> items;
  private ArrayList<String> keys;
  private long lastId;
  public Table() {
    this.items = new ArrayList<Item>();
    this.keys = new ArrayList<String>();
    this.lastId = 0;
  }
  public Table(String... keys) {
    this();
    for (String key : keys) {
      this.keys.add(key);
    }
  }
  public void addItem(Object... values) throws IllegalArgumentException {
    if (values.length != this.keys.size()) {
      throw new IllegalArgumentException("Incorrect number of values");
    }
    this.items.add(new Item(++this.lastId, values));
  }
  public void addKey(String key) {
    this.keys.add(key);
    for (Item item : this.items) {
      item.extend(null);
    }
  }
  public void addKey(int index, String keyName) {
    this.keys.add(index, keyName);
    for (Item item : this.items) {
      item.extend(index, null);
    }
  }
  public Item[] getAllItems() {
    return this.items.toArray(new Item[this.items.size()]);
  }
  public Item getItem(long id) {
    for (Item item : this.items) {
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }
  public Item[] getItemsCorresponding(String key, Object value) {
    ArrayList<Item> result = new ArrayList<Item>();
    int keyIndex = this.keys.indexOf(key);
    if (keyIndex != -1) {
      for (Item item : this.items) {
        if (item.getValues()[keyIndex].equals(value)) {
          result.add(item);
        }
      }
    }
    return result.toArray(new Item[result.size()]);
  }
  public Item[] getItemsNotCorresponding(String key, Object value) {
    ArrayList<Item> result = new ArrayList<Item>();
    int keyIndex = this.keys.indexOf(key);
    if (keyIndex != -1) {
      for (Item item : this.items) {
        if (!item.getValues()[keyIndex].equals(value)) {
          result.add(item);
        }
      }
    }
    return result.toArray(new Item[result.size()]);
  }
  public void removeItem(long id) {
    this.items.remove(this.getItem(id));
  }
  public void removeItem(Item item) {
    this.items.remove(item);
  }
  public void removeKey(int index) {
    this.keys.remove(index);
    for (Item item : this.items) {
      item.crop(index);
    }
  }
  public void removeKey(String keyName) {
    int index = this.keys.indexOf(keyName);
    if (index != -1) {
      this.removeKey(index);
    }
  }
}
