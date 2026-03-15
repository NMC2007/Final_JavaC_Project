package business;

public interface ILoginBusiness<T> {
    T CheckLogin(String username, String password);
}
