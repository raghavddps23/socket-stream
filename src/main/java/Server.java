// Server2 class that
// receives data and sends data

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server{

    public static void main(String args[]) throws Exception
    {
        File soundFile = AudioUtil.getSoundFile("/home/learner/Mp3/src/main/java/ans.wav");

        try (ServerSocket serverSocker = new ServerSocket(8888);
             FileInputStream in = new FileInputStream(soundFile)) {
            if (serverSocker.isBound()) {
                Socket client = serverSocker.accept();
                OutputStream out = client.getOutputStream();
                byte buffer[] = new byte[1000000];
                int count;
                while ((count = in.read(buffer)) != -1)
                    out.write(buffer, 0, count);
            }
        }

    }
}
