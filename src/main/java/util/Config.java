package util;

public class Config {

    public static int getJettyPort(){
        return Integer.parseInt(System.getProperty("jetty.port","8080"));
    }

    public static int getJettyThreads(){
        return Integer.parseInt(System.getProperty("jetty.threads", "100"));
    }
}
