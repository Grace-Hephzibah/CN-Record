import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;

public class Exp12 {
    public static void main(String args[]) throws Exception {
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +  (localhost.getHostAddress()).trim());
        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e) { systemipaddress = "Cannot Execute Properly"; }
        System.out.println("Public IP Address: " + systemipaddress +"\n");
    }
}
