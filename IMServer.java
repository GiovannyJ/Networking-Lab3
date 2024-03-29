import java.net.*;
import java.io.*;

public class IMServer {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java IMServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try ( 
            //* Listen on the port for a connection request
            ServerSocket serverSocket = new ServerSocket(portNumber);
            //* Accept connection request
            Socket clientSocket = serverSocket.accept();

            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
        
            String inputLine, outputLine;
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            
            // Initiate conversation with client
            IMProtocol imp = new IMProtocol();
            //* Send initial message to client
            outputLine = imp.processInput(null);
            out.println(outputLine);

            /*
             * while the server can read an input line
             * print out what the server said
             * check if you are going to say bye and then send ur response
             */
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client: " + inputLine);
                //* Determine servers reply
                outputLine = imp.processInput(inputLine);
                //*if the output is not bye. or Bye. then */
                if(!outputLine.equalsIgnoreCase("bye.")){
                    System.out.print("Server: ");
                    //*get an input from server side */
                    outputLine = stdIn.readLine();
                }
                //* Send Servers reply to client
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}