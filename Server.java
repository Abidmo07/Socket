import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        int client_number=0;
        ServerSocket server = new ServerSocket(7777); // socket de connexion
        while (true) { 
            try {
                    
                    Socket sock = server.accept();
                    client_number++;
                    System.out.println("New connection with client " +client_number +" "  + sock.getInetAddress().getHostName());
                    THD thread =new THD(sock);
                    thread.start();
                    
            } catch (IOException e) {
                
            }
        }
       
    }
}

class THD extends Thread implements Runnable {
    private Socket sock;

    public THD(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try {
            InputStream input = sock.getInputStream();
            DataInputStream in = new DataInputStream(input);
            OutputStream output = sock.getOutputStream();
            DataOutputStream out = new DataOutputStream(output);
            boolean win=false;
            Random random = new Random();
            int number = random.nextInt(100) + 1; // Generate a random number between 1 and 100
            System.out.println("The guessed number is: " + number);
            out.writeUTF("Guess a number between 1 and 100");

            while (win==false) {
                int guess = in.readInt(); // Read the guessed number
                if (guess < number) {
                    out.writeUTF("High");
                } else if (guess > number) {
                    out.writeUTF("Low");
                } else if (guess == number) {
                    out.writeUTF("Congratulations, you guessed the number!");
                    win=true; 
                     
                }
            }
           
            sock.close();

            
        } catch (IOException e) {
            
        }
    }
}
