import java.io.*;
import java.net.*;
import java.util.Scanner;
class ServerThread extends Thread{
    Socket s = null;    
    DataInputStream ois;
    DataOutputStream ous;
    Chat chat;
    String clientID;
    int clientN;
    public ServerThread(Socket s,Chat c,int clientNum){
        this.s = s;
        chat = c;
        clientN = clientNum;
    }
    public void run(){
        System.out.println("Server thread runnng for client "+clientN);
        try{
            ois = new DataInputStream(s.getInputStream());
            ous = new DataOutputStream(s.getOutputStream());
            System.out.println("Server thread connected for client "+clientN);
        }catch(Exception e){
            System.out.println("Server connection failed for client "+clientN);
        }
        try{
            System.out.println("Server thread running for client "+clientN);
            int port = 2500;
            clientID = (String)this.ois.readUTF();
            System.out.println("Client "+clientN+" declared itself as "+clientID);
            chat.lastUp = clientID;
            chatBroadcast cb = new chatBroadcast(ous,chat);
            cb.start();
            String st1="";
            while(true){
                st1 = (String)this.ois.readUTF();
                chat.chatText = st1;
                chat.lastUp = clientID;
                System.out.println(chat.lastUp+" sent a message");
            }
        }catch(SocketException e){
            System.out.println("Connection terminated by client "+clientN);
        }catch(IOException e1){
            e1.printStackTrace();
            System.out.println("IOExcpetion thrown");
        }
    }
}
