package julya.db;

import java.util.ArrayList;

public class Item {
  private long id;
  private ArrayList<Object> values;
  public Item(long id) {
    this.id = id;
    this.values = new ArrayList<Object>();
  }
  public Item(long id, Object[] values) {
    this(id);
    for (Object value : values) {
      this.values.add(value);
    }
  }
  public void extend(Object value) {
    this.values.add(value);
  }
  public void extend(int index, Object value) {
    this.values.add(index, value);
  }
  public long getId() {
    return this.id;
  }
  public Object[] getValues() {
    return this.values.toArray();
  }
  public void crop(int index) {
    this.values.remove(index);
  }
}
