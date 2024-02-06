import java.net.*;
import java.io.*;

public class IMProtocol {
    private static final int WAITING = 0;
    private static final int IN_CONVERSATION = 1;

    private int state = WAITING;

    public String processInput(String theInput) {
        String theOutput = null;
            /*
            * only need 2 states
            * waiting is for when the server is awaiting a clients connection
            * send response to client that the connection is made 
            * switch to inconversation after so that when the client responds the code will check for what to do
            */
        if (state == WAITING) {
            theOutput = "Connection Established";
            state = IN_CONVERSATION;
        } else if (state == IN_CONVERSATION) {
            /*
             * When in converstaion check if the client said bye and if they did then u can switch state again
             */
            if (!theInput.equalsIgnoreCase("Bye.")) {
                theOutput = "";
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}