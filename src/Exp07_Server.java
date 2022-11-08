import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Exp07_Server{
    static ServerSocket serverSocket;
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(1515);
            while(true) {
                Socket accept_client = serverSocket.accept();
                new Thread(new ServerIn(accept_client)).start();
                new Thread(new ServerOut(accept_client)).start();
            }
        } catch (IOException e) { e.printStackTrace();
            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
//Accepted threads
class ServerIn implements Runnable{
    Socket socket;
    ServerIn(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            while(true) {
                byte infile[] = new byte[1024];
                int size  = in.read(infile);
                String string = new String(infile,0,size);
                if(!string.equals("") && !string.equals("\n")) System.out.println("message from client: "+ string);
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
class ServerOut implements Runnable{
    Socket socket;
    Scanner reader = new Scanner(System.in);
    ServerOut(Socket socket){
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
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

