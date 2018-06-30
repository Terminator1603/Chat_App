import java.io.*;
import java.net.*;
import java.util.Scanner;
class Client{
    public static void main(String[] args) throws Exception{
        String clientID="";
        if(args.length!=0){
            clientID = args[0];
        }else{
            clientID = idGenerator(3);
        }
        InetAddress host = InetAddress.getLocalHost();;
        int port = 2500;
        Scanner in = new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Connecting with host");
        Socket s = new Socket(host.getHostName(),port);

        DataInputStream ois = new DataInputStream(s.getInputStream());
        DataOutputStream ous = new DataOutputStream(s.getOutputStream());
        System.out.println("Connected to: "+host.getHostName());
        System.out.println("-----------------------------------");
        ous.writeUTF(clientID);  
        ous.flush();
        String st1="",st2="";  
        AcquireChat ac = new AcquireChat(ois,ous,clientID);
        ac.start();
        while(!st1.equals("stop")){
            System.out.print(clientID+": ");
            st1=br.readLine();
            ous.writeUTF(clientID+st1);  
            ous.flush();  
            //System.out.println((String)ois.readUTF());
        }  
        ous.close();
        s.close();
    }
    public static String idGenerator(int n){
        String id = "";
        for(int i =0;i<n;i++){
            id += (char)(int)(Math.random()*(91-65)+65);
        }
        return id;
    }
}
