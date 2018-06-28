import java.io.*;
import java.net.*;
import java.util.Scanner;
class MyClient{
    public static void main(String[] args) throws Exception{
        InetAddress host = InetAddress.getLocalHost();;
        int port = 2500;
        Scanner in = new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Connecting with host");
        Socket s = new Socket(host.getHostName(),port);
        DataInputStream ois = new DataInputStream(s.getInputStream());
        DataOutputStream ous = new DataOutputStream(s.getOutputStream());
        System.out.println("Connected");
          
        String st1="",st2="";  
        while(!str.equals("stop")){  
            System.out.println("Enter a message");
            st1=br.readLine();  
            ous.writeUTF(str);  
            ous.flush();  
            st2=(String)ois.readUTF();  
            System.out.println("Server says: "+st2);  
        }  
        ous.close();
        s.close();
    }
}
