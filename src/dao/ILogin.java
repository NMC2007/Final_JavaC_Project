package dao;

public interface ILogin<T> {
    T login(String username, String password);
}
