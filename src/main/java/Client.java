import javax.sound.sampled.*;
import java.io.*;
import java.net.Socket;

class Client{

    public static void main(String args[]) throws Exception
    {

        Socket clientSocket = new Socket("localhost",8888);
        int i = 0;
        while(clientSocket.isConnected()){
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            byte[] message = new byte[1000000]; // the well known size
            in.readFully(message);
            play(message,i);
            i = i + 1;
        }
    }

    private static synchronized void play(byte[] message,int k) throws Exception {

        String src = "/home/learner/Mp3/src/main/java/" + k + ".wav";
        InputStream b_in = new ByteArrayInputStream(message);
        AudioFormat format = new AudioFormat(8000f, 16, 1, true, false);
        AudioInputStream stream = new AudioInputStream(b_in, format, message.length);
        File file = new File(src);
        AudioSystem.write(stream, AudioFileFormat.Type.WAVE, file);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(src));
        AudioFormat format1 = inputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format1);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(inputStream);
        clip.start();
    }
}
