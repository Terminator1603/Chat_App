import java.io.*;
import java.net.*;
import java.util.Scanner;
public class chatBroadcast extends Thread{
    DataOutputStream ous;
    Chat chat;
    public chatBroadcast(DataOutputStream ous1,Chat chat1){
        ous = ous1;
        chat = chat1;
    }

    public void run(){
        try{
            while(true){
                for(int i=0;i<1000;i++){
                    ous.writeUTF(chat.chatText);
                    ous.flush();
                }
                chat.chatText="";
            }
        }catch(Exception e){}
    }
}
