import java.io.*;
import java.util.*;
import java.net.*;

public class Exp07_Client{
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1",1515);
            new Thread(new ClientIn(client)).start();
            new Thread(new ClientOut(client)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//Accepted threads
class ClientIn implements Runnable{
    Socket socket;
    ClientIn(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            while(true) {
                byte infile[] = new byte[1024];
                int size = in.read(infile);
                String string = new String(infile,0,size);
                if(!string.equals("") && !string.equals("\n")) System.out.println("message from server: "+ string);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
//Thread to send
class ClientOut implements Runnable{
    Socket socket;
    Scanner reader = new Scanner(System.in);
    ClientOut(Socket socket){
        this.socket = socket;
    }
    public void run() {
        try {
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            while(true) {
                String string  = reader.nextLine();
                out.write(string);
                out.flush();
            }
        } catch (IOException e) { e.printStackTrace();
            try {
                socket.close();
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

