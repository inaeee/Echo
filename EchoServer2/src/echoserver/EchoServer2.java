package echoserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class EchoServer2 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9800);
            System.out.println("��ȭ���� �����Ǿ����ϴ�.");
            while(true) {
                Socket client = server.accept();
                Echo echoThread = new Echo(client);
                echoThread.start();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class Echo extends Thread {
    private Socket socket;
    public Echo(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InetAddress client = socket.getInetAddress();
            System.out.println("�ý��� : [" + client + "] ���� ��ȭ�� �����Ͽ����ϴ�.");

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter(os);
            PrintWriter pw = new PrintWriter(osr);

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String msg = null;
            while(true) {
                Set<String> msgSet = new HashSet<>();
                msg = br.readLine();
                msgSet.add(msg);
                if (msg == null) {
                    System.out.println("[" + client.getHostAddress() + "] ���� >��ȭ���� �������ϴ�.");
                }
                for (String s : msgSet) {
                    System.out.println("["+ client.getHostAddress() +"] : " +s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
