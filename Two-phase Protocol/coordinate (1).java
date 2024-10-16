import java.io.*;
import java.net.*;

class coordinate {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket ss = new ServerSocket(4545);
        System.out.println("\nEnter No. of  Subordinate:");
        int cnt = Integer.parseInt(br.readLine());
        Socket[] s = new Socket[cnt];
        DataInputStream[] dis = new DataInputStream[cnt];
        DataOutputStream[] dos = new DataOutputStream[cnt];
        for (int i = 0; i < cnt; i++) {
            s[i] = ss.accept();
            dis[i] = new DataInputStream(s[i].getInputStream());
            dos[i] = new DataOutputStream(s[i].getOutputStream());
        }
        String pr = new String("prepare");
        System.out.println("Sending prepare message");
        for (int i = 0; i < cnt; i++) {
            dos[i].writeUTF(pr);
        }
        String[] in = new String[cnt];
        boolean flg = true;
        for (int i = 0; i < cnt; i++) {
            in[i] = dis[i].readUTF();
            if (in[i].equals("no"))
                flg = false;
        }
        if (flg == false) {
            pr = "abort";
            System.out.println("Sending abort msg");
            for (int i = 0; i < cnt; i++) {
                dos[i].writeUTF(pr);
            }
        } else {
            pr = "commit";
            System.out.println("Sending commit msg");
            for (int i = 0; i < cnt; i++) {

                dos[i].writeUTF(pr);
            }

            for (int i = 0; i < cnt; i++) {
                System.out.println("Receiving  ack");
            }
        }
    }
}
