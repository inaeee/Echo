package echoclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
 
public class EchoClient2 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("52.79.251.10", 9800);   
            //Socket client = new Socket("13.125.98.170", 9800);  
            //Socket client = new Socket("192.168.0.44", 9800); 
            

            InputStreamReader isr2 = new InputStreamReader(System.in);
            BufferedReader keyboard = new BufferedReader(isr2);
            
            //������ ������ ������ ��ü
            OutputStream os = client.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter(os);
            PrintWriter pw = new PrintWriter(osr);
            
            //�������� �������� ������ �޴� ��ü
            InputStream is = client.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            while(true) {
                System.out.println("������ ������ �޼��� �Է�(quit�Է½� ����) : ");
                String msg = keyboard.readLine();
                System.out.printf(msg);
                System.out.println("\n");
                if(msg.equals("quit")) {
                    System.out.println("�������� ������ ���� �մϴ�.");
                    client.close();
                    break;
                }
                pw.println(msg);
                pw.flush();            
                 
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
