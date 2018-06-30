import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;
public class Server{ 
    public static void main(String args[]){
        Chat chat = new Chat();
        Socket s =null;
        ServerSocket ss2=null;
        System.out.println("Accepting connections");
        try{
            ss2 = new ServerSocket(2500);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("There is an error you worthless use of oxygen (server error)");
        }
        int n = 0;
        ArrayList<ServerThread> arrli = new ArrayList<ServerThread>(n);
        //ArrayList<String> arrlis = new ArrayList<String>(n);
        while(true){
            try{
                s = ss2.accept();
                System.out.println("Connecting new client");
                arrli.add(new ServerThread(s,chat,n+1));
                arrli.get(n).start();
                n++;
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Honestly how are you so bad (connection error)");
            }
        }
    }
}
