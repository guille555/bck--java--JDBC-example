package io.scout.dao;

import java.util.List;

/**
 * @author DEV Scout
 */
public interface IBasicDAO<T> {

  public abstract int save(T object);

  public abstract void update(T object);

  public abstract void shift(T object);

  public abstract void delete(T object);

  public abstract void addCode(long id, String code);

  public abstract T findById(long id);

  public abstract T findByCode(String code);

  public abstract List<T> findAll();
}
