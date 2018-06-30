import java.io.*;
import java.net.*;
import java.util.Scanner;
class ServerThread extends Thread{
    Socket s = null;    
    DataInputStream ois;
    DataOutputStream ous;
    Chat chat;
    String clientID;
    public ServerThread(Socket s,Chat c){
        this.s = s;
        chat = c;
    }
    public void run(){
        System.out.println("Server thread runnng for: "+clientID);
        try{
            ois = new DataInputStream(s.getInputStream());
            ous = new DataOutputStream(s.getOutputStream());
            System.out.println("Server thread connected for: "+clientID);
        }catch(Exception e){
            System.out.println("Server connection failed for: "+clientID);
        }
        try{
            System.out.println("Server thread running for: "+clientID);
            int port = 2500;        clientID = (String)this.ois.readUTF();
            chat.lastUp = clientID;
            chatBroadcast cb = new chatBroadcast(ous,chat);
            cb.start();
            String st1;
            while(true){
                st1 = (String)this.ois.readUTF();
                chat.chatText = st1;
                chat.lastUp = clientID;
                System.out.println(chat.lastUp);
            }
            //this.ois.close();
            //this.s.close();
            //System.out.println("Connection terminated");
        }catch(Exception e){System.out.println("how");}
    }
}
