import java.io.*;
import java.net.*;

class subordinate {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Socket s = new Socket("localhost", 4545);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        String out = new String();
        String in = dis.readUTF();
        System.out.println("Receiving " + in + " msg.");
        System.out.println("SubOrdinateready:'yes' or 'no':");
        out = br.readLine();
        System.out.println("Sending " + out);
        dos.writeUTF(out);
        in = dis.readUTF();
        System.out.println("Reciving " + in + " msg");
        if (in.equals("commit")) {
            out = "ack";
            System.out.println("Sending Ack");
        }
    }
}
