package com.golubovich.elibrary.utils;

import com.alibaba.fastjson.JSON;

// Facade pattern (structural pattern)
public class JSONSerializer {

  public static String toJSON(Object obj) {
    return JSON.toJSONString(obj);
  }

  public static <T> T fromJSON(String jsonString, Class<T> clazz) {
    return JSON.parseObject(jsonString, clazz);
  }
}



