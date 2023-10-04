package tech.bingulhan.webserver.response;

import tech.bingulhan.webserver.WebServer;
import tech.bingulhan.webserver.response.ResponseManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author BingulHan
 */
public class SocketResponse {

    private Socket socket;
    private WebServer webServer;
    public SocketResponse(WebServer server,Socket socket){
        this.socket = socket;
        this.webServer = server;

        try {
            sendData();
        } catch (IOException e) {
            server.getLogger().warning(e.getMessage());
        }
    }

    public void sendData() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        PrintWriter out = new PrintWriter(this.socket.getOutputStream());

        //Start sending our reply, using the HTTP 1.1 protocol
        out.print("HTTP/1.1 200 \r\n"); // Version & status code
        out.print("Content-Type: text/html\r\n"); // The type of data
        out.print("Connection: close\r\n"); // Will close stream
        out.print("\r\n"); // End of headers


        ResponseManager responseManager = new ResponseManager(out,webServer,socket.getRemoteSocketAddress().toString());
        webServer.getHttpListeners().forEach(l->l.onHttpRequest(responseManager));

        webServer.getLogger().info("Request: IP Adress:"+socket.getRemoteSocketAddress().toString());

        if (!responseManager.isCancelled()) {
            out.close(); // Flush and close the output stream
        }

        in.close(); // Close the input stream
        this.socket.close(); // Close the socket itself


    }
}
