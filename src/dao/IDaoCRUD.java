package dao;

import enums.DeleteStatusEnum;
import enums.InsertStatusEnum;
import enums.UpdateStatusEnum;

import java.util.List;

public interface IDaoCRUD<T> {
    InsertStatusEnum insert(T t);
    UpdateStatusEnum update(T t);
    DeleteStatusEnum delete(int id);
    T findById(int id);
    List<T> findAll();
    List<T> sort(int sortOption);
}
