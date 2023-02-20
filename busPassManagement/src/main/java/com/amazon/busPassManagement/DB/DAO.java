package com.amazon.busPassManagement.DB;


import java.util.List;

public interface DAO<T> {
      int insert(T object);
      int update(T object);
      int delete(T object);
      List<T> retrieve();
      // To retrieve Custom data by passing custom query...
      List<T> retrieve(String sql);
}
