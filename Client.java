import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
public static void main(String[] args) {
try {
System.out.println("Connexion vers la machine serveur :");
Socket client = new Socket ("localhost", 7777); // socket de connexion

    InputStream input = client.getInputStream ();
    DataInputStream in=new DataInputStream(input);
    OutputStream output = client.getOutputStream ();
    DataOutputStream out=new DataOutputStream(output);
    Scanner scan = new Scanner(System.in);
    System.out.println(in.readUTF());
    boolean win=false;
    while (win==false) { 
        int guess=scan.nextInt();
        out.writeInt(guess);
        String server_response=in.readUTF();
        System.out.println("server: "+server_response);
        if(server_response.equals("Congratulations, you guessed the number!")){
            System.out.println("YOU won");
            win=true;
        }
        
    }
   
   

    





} catch (UnknownHostException e) {}
catch (IOException e) {}
}
}
