import java.net.*;
import java.io.*;
import java.util.Date;

public class Exp05_Server {

    public static void main(String[] args)throws IOException {
        // TODO code application logic here

        //Step 1. Reserve a port number on the Server to offer this service
        ServerSocket ss= new ServerSocket(5000);
        //(Optional)To confirm Server Reserved specified port or not
        System.out.println("The Server has reserved port No.: "+ss.getLocalPort()+" for this Service");

        //Step 2. Now create a Client Socket on Server for Bidirectonal Communication.
        //Socket is created only when client communicates with the server
        Socket cs=ss.accept();

        //To confirm Server communicated through the socket or not
        System.out.println("Client with IP Address "+cs.getInetAddress()+" has communicated via port No.: "+cs.getPort());

        Date d=new Date();
        String s="Current Date & Time on Server is:"+d;

        //Send String s to client via client socket
        PrintWriter toclient=new PrintWriter(cs.getOutputStream(),true);
        toclient.print(s);
        toclient.close();
        cs.close();
        ss.close();
    }
}
