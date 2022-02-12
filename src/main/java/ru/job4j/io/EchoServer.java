package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("Hello")) {
                            out.write("Hello, dear friend\r\n".getBytes());
                        }
                        if (str.contains("Exit")) {
                            server.close();
                        } else {
                            out.write("What, dear friend\r\n".getBytes());
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception IO", e);
        }
    }
}