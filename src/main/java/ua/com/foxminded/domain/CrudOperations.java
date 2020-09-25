package ua.com.foxminded.domain;

import java.util.List;

public interface CrudOperations<T, ID> { // ToDo: implement in all CRUD based classes (service, DAO, UI-flow(?))
    T create(T entity);

    T findById(ID entity);

    List<T> readAll();

    T update(T entity);

    void delete(ID entity);


}
