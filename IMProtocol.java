import java.net.*;
import java.io.*;

public class IMProtocol {
    private static final int WAITING = 0;
    private static final int IN_CONVERSATION = 1;

    private int state = WAITING;

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Connection Established";
            state = IN_CONVERSATION;
        } else if (state == IN_CONVERSATION) {
            if (!theInput.equalsIgnoreCase("Bye")) {
                theOutput = "";
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}