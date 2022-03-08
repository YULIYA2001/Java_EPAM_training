package com.golubovich.elibrary.utils.comparators;

import com.golubovich.elibrary.beans.Client;
import java.util.Comparator;

public class CompareClientsByDate implements Comparator<Client> {

  @Override
  public int compare(Client o1, Client o2) {
    if (o1.getRegistrationDate().compareTo(o2.getRegistrationDate()) > 0) {
      return 1;
    } else if (o1.getRegistrationDate().compareTo(o2.getRegistrationDate()) < 0) {
      return -1;
    }
    return 0;
  }
}
