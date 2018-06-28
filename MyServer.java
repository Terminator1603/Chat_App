import java.io.*;
import java.net.*;
import java.util.Scanner;
class MyServer{
    
    public static void main(String[] args) throws Exception{
        int port = 2500;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Connecting to server on port "+port);
        ServerSocket ss = new ServerSocket(port);
        Socket socket = ss.accept();//establishes connection
        
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        DataOutputStream ous = new DataOutputStream(socket.getOutputStream());
        System.out.println("Connected");
        
        String st1="",st2="";
        while(!st1.equals("stop")){
            st1 = (String)ois.readUTF();
            System.out.println(st1);
            System.out.println("Enter a message");
            st2 = br.readLine();
            ous.writeUTF(st2);
            ous.flush();
        }
        ois.close();
        ss.close();
        System.out.println("Connection terminated");

    }
}