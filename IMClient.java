import java.io.*;
import java.net.*;

public class IMClient {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java IMClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            //* Initiate a connection request to server IPADDR:PORT {SOCKET}
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            /*
             * While the input channel is open print the messages from the server
             * unless it says bye then the convo is done
             * get the users input in the client side and send it to the server
             */
            
            //* Receive message from server
            while ((fromServer = in.readLine()) != null) {
                //* Print Message from server
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;
                //* Read response from client
                System.out.print("Client: ");
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    // System.out.println("Client: " + fromUser);
                    //* Send response to server
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}