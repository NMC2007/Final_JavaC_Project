package dao.impl;

public interface ILogin<T> {
    T login(String username, String password);
}
