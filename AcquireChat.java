import java.io.*;
public class AcquireChat extends Thread{
    DataInputStream is;
    DataOutputStream os;
    String cID;
    String olds="84957984359";
    public AcquireChat(DataInputStream ois,DataOutputStream ous1,String clientID){
        is = ois;
        os = ous1;
        cID = clientID;
    }

    public void run(){
        while(true){
            try{
                String s = (String)this.is.readUTF();
                if(s.length()>0){
                    if(!s.substring(0,3).equals(cID)&&!s.equals(olds)){
                        System.out.println(s.substring(0,3)+": " +s.substring(3));
                        olds = s;
                        //os.writeUTF("Acquired");
                        //os.flush();
                    }
                }
            }catch(Exception e){}
        }
    }
}
