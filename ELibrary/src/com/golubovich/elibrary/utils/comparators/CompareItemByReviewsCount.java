package com.golubovich.elibrary.utils.comparators;

import com.golubovich.elibrary.beans.Item;
import java.util.Comparator;

public class CompareItemByReviewsCount implements Comparator<Item> {

  @Override
  public int compare(Item o1, Item o2) {
    int count1;
    if (o1.getReview() == null){
      count1 = 0;
    }
    else {
      count1 = o1.getReview().size();
    }
    int count2;
    if (o2.getReview() == null){
      count2 = 0;
    }
    else {
      count2 = o2.getReview().size();
    }

    if(count1 > count2) {
      return 1;
    } else if(count1 < count2) {
      return -1;
    }
    return 0;
  }
}
