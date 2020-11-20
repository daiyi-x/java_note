import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ThreadLocalProxyAuthenticator extends Authenticator {
    private static ThreadLocal<PasswordAuthentication> credentials = null;
    private ThreadLocalProxyAuthenticator(){
        credentials = new ThreadLocal<>();
    }
    private static class SingletonHolder {
        private static final ThreadLocalProxyAuthenticator instance = new ThreadLocalProxyAuthenticator();
    }
    public static final ThreadLocalProxyAuthenticator getInstance() {
        return SingletonHolder.instance;
    }
    public void setCredentials(String user, String password) {
        credentials.set(new PasswordAuthentication(user, password.toCharArray()));
    }
    public static void clearCredentials() {
        ThreadLocalProxyAuthenticator authenticator = ThreadLocalProxyAuthenticator.getInstance();
        Authenticator.setDefault(authenticator);
        authenticator.credentials.set(null);
    }
    public PasswordAuthentication getPasswordAuthentication() {
        return credentials.get();
    }
}