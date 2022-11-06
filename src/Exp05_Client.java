import java.net.*;
import java.io.*;

public class Exp05_Client {

    public static void main(String[] args) throws UnknownHostException,IOException {
        // TODO code application logic here
        //Step 1. Create a client socket to connect to Server
        Socket cs= new Socket("LocalHost",5000);

        //To confirm Client is communicating through the port
        System.out.println("Client "+cs.getInetAddress()+" is communicating from port No.: "+cs.getPort());

                //Receive Date Sent by Server
                BufferedReader fromserver=new BufferedReader(new InputStreamReader(cs.getInputStream()));

        System.out.println(fromserver.readLine());
        fromserver.close();
        cs.close();

    }
}

