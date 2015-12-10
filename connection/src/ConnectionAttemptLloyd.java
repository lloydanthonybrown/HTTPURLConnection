//import alberto.ConnectionAttempt;
import org.quickconnectfamily.json.JSONOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConnectionAttemptLloyd {
    public static void main(String[] args){
        ConnectionAttemptLloyd connectNow = new ConnectionAttemptLloyd();
        // Problem: Tells me that it's trying to access a private method. I changed it to public.
        // Error: java.net.ConnectException: Connection refused: connect
        // at ConnectionAttemptLloyd.main(ConnectionAttemptLloyd.java:11)
//        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//        at java.lang.reflect.Method.invoke(Method.java:497)
//        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
        connectNow.go();
    }

    public void go() {
        // Happy path: ... the fact that it gave me feedback? I'm not sure how else to otherwise get a happy path for this.
        // Infinite Loop - keeps asking me for input, even after displaying all the errors from below.
// Wow, nasty things happen without the while loop, too! It allowed me to run it multiple times before it finally finished the process.
 while(true){
            try {
                Scanner systemInScanner = new Scanner(System.in);
                System.out.printf("Enter message.\n");
                String messageForServer = systemInScanner.nextLine();

                // Happy path: Using a valid URL for the connection.
                URL urlOne = new URL("http://localhost:7777/json");
                HttpURLConnection connectionOne = (HttpURLConnection) urlOne.openConnection(); // Good question to ask!! what is the thing in parentheses doing? Is there a better way to write it?
                connectionOne.setDoOutput(true);//allows POST
                // Go out and interact with the URL given, this line is CRITICAL to make it talk to the outside world
                JSONOutputStream outToServerOne = new JSONOutputStream(connectionOne.getOutputStream());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Nasty path: URL Empty String
                // ERROR (verified): java.net.MalformedURLException: no protocol:
                URL urlTwo = new URL("");
                HttpURLConnection connectionTwo = (HttpURLConnection)urlTwo.openConnection();
                connectionTwo.setDoOutput(true);
                JSONOutputStream outToServerTwo = new JSONOutputStream(connectionTwo.getOutputStream());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Nasty path: URL null
                // ERROR (verified): java.net.MalformedURLException: no protocol:
                URL urlThree = new URL(null);
                HttpURLConnection connectionThree = (HttpURLConnection)urlThree.openConnection();
                connectionThree.setDoOutput(true);
                JSONOutputStream outToServerThree = new JSONOutputStream(connectionThree.getOutputStream());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Nasty path: URL FTP type
                // ERROR (verified): java.lang.ClassCastException: sun.net.www.protocol.ftp.FtpURLConnection cannot be cast to java.net.HttpURLConnection
                URL urlFour = new URL("ftp://ftp.funet.fi/pub/standards/RFC/rfc959.txt");
                HttpURLConnection connectionFour = (HttpURLConnection)urlFour.openConnection();
                connectionFour.setDoOutput(true);
                JSONOutputStream outToServerFour = new JSONOutputStream(connectionFour.getOutputStream());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Nasty path: URL Busy Server
                // ERROR (verified): java.net.ConnectException: Connection timed out: connect
                URL urlFive = new URL("http://www.google.com:81");
                HttpURLConnection connectionFive = (HttpURLConnection)urlFive.openConnection();
                connectionFive.setDoOutput(true);
                JSONOutputStream outToServerFive = new JSONOutputStream(connectionFive.getOutputStream());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Nasty path: URL Non Existent
                // ERROR: IOException - if an error occurred connecting to the server.
                URL urlSix = new URL("http://localhost:8080/json");
                HttpURLConnection connectionSix = (HttpURLConnection)urlSix.openConnection();
                connectionSix.setDoOutput(true);
                JSONOutputStream outToServerSix = new JSONOutputStream(connectionSix.getOutputStream());
            }
            catch (Exception e) {
                    e.printStackTrace();
                    }

            try {
                // Nasty path: URL Called Twice
                // ERROR: IllegalStateException - if URLConnection is already connected or if a different streaming mode is already enabled.
                URL urlSeven = new URL("http://localhost:7070/json");
                urlSeven = new URL("http://localhost:7070/json");
                HttpURLConnection connectionSeven = (HttpURLConnection)urlSeven.openConnection();
                connectionSeven.setDoOutput(true);
                JSONOutputStream outToServerSeven = new JSONOutputStream(connectionSeven.getOutputStream());
            }
            catch (Exception e){
                e.printStackTrace();

            }
        }

    }
}
