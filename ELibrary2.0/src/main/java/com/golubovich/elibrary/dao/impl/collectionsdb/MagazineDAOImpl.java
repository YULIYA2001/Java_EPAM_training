package com.golubovich.elibrary.dao.impl.collectionsdb;

import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.beans.Magazine;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.ItemType;
import com.golubovich.elibrary.utils.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MagazineDAOImpl implements ItemDAO<Magazine> {

  private final DataSource dataSource = DataSource.getInstance();

  public boolean create(Magazine item) {
    return dataSource.getItems().get(ItemType.MAGAZINE).add(item);
  }

  public List<Magazine> read() {
    return new ArrayList(dataSource.getItems().get(ItemType.MAGAZINE));
  }

  public void update(Magazine currentItem, Magazine updatedItem) {
    dataSource.getItems().get(ItemType.MAGAZINE).set(
        dataSource.getItems().get(ItemType.MAGAZINE).indexOf(currentItem), updatedItem
    );
  }

  public boolean delete(Magazine deletedItem) {
    return dataSource.getItems().get(ItemType.MAGAZINE).remove(deletedItem);
  }

  public Magazine findByCode(int code) {
    Iterator<Item> magazinesIterator = dataSource.getItems().get(ItemType.MAGAZINE).iterator();

    Magazine magazine;
    while (magazinesIterator.hasNext()) {
      magazine = (Magazine) magazinesIterator.next();
      if (magazine.getCode() == code) {
        return magazine;
      }
    }
    return null;
  }
}

