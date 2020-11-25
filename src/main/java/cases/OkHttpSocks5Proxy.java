//import okhttp3.*;
//
//import javax.net.SocketFactory;
//import java.io.IOException;
//import java.net.Authenticator;
//import java.net.InetSocketAddress;
//import java.net.PasswordAuthentication;
//import java.net.Proxy;
//import java.util.concurrent.TimeUnit;
//
//public class OkHttpSocksTest {
//    public Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("gate5.rola-ip.co", 2081 ));
//
//
//    static class ProxyGenerator{
//        public Proxy getProxy(String host, int port, String userName, String password) {
//            java.net.Authenticator.setDefault(new java.net.Authenticator(){
//                protected PasswordAuthentication getPasswordAuthentication(){
//                    PasswordAuthentication authentication = new PasswordAuthentication("hyun855_2", "jisuanji855".toCharArray());
//                    return authentication;
//                }
//            });
//            return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("gate5.rola-ip.co", 2081 ));
//        }
//    }
//
//
//    private class OkHttpProxyInterceptor implements Interceptor{
//
//        public OkHttpProxyInterceptor(String userName, String password) {
//            this.userName = userName;
//            this.password = password;
//        }
//
//        private String userName;
//        private String password;
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            ThreadLocalProxyAuthenticator authenticator = ThreadLocalProxyAuthenticator.getInstance();
//            authenticator.setCredentials(userName, password);
//            Authenticator.setDefault(authenticator);
//            try{
//                return chain.proceed(chain.request());
//            } finally {
//                ThreadLocalProxyAuthenticator.clearCredentials();
//            }
//        }
//    }
//
//    public void proxySocks5Test() {
//
////        Proxy proxy = new ProxyGenerator().getProxy("gate5.rola-ip.co", 2081, "hyun855_2", "jisuanji855");
////        Proxy proxy1 = new ProxyGenerator().getProxy("192.168.10.14", 1080, "inewbit", "aso123456");
//        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(150, TimeUnit.SECONDS)
//                .proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("gate5.rola-ip.co", 2081 )))
//                .addInterceptor(new OkHttpProxyInterceptor("hyun855_2","jisuanji855" ))
//                .build();//创建OkHttpClient，并且设置超时时间和代理
//        Request request = new Request.Builder().url("https://google.com/").get().build();//查询本机ip地址请求
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println("-------");
//            System.out.println(response.body().string());
//            System.out.println("-------");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        new OkHttpSocksTest().proxySocks5Test();
//    }
//
//}
