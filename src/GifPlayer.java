import javax.sound.sampled.*;
import javax.swing.Timer;
import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.awt.*;

public class GifPlayer {

    static GifPlayer gif= new GifPlayer();
    static ImageIcon icon,icon1;
    static JOptionPane pane,pane2;
    static JDialog d,d2;
    static Timer pause1;

    
    public static void main(String[] args) {
        createFiles();
        startProgram();
    }


    public static void randomColor(){
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        Color randomColor = new Color(red, green, blue);
        pane.setBackground(randomColor);
        pane.setForeground(randomColor);
    }


    public static void createFiles(){

        icon = new ImageIcon(Objects.requireNonNull(GifPlayer.class.getResource("bruh.gif")).getFile());
        icon1 = new ImageIcon(Objects.requireNonNull(GifPlayer.class.getResource("sturdy.gif")).getFile());

        pane = new JOptionPane("",JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION,icon1,new Object[]{});
        pane2=new JOptionPane( " ", JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION ,icon,new Object[]{});

        Color color = new Color(23, 0, 5);
        d = pane.createDialog(null, "Sturdy");
        d2 = pane2.createDialog(null,"Wake up to Reality");

        pane2.setBackground(color);
        pane2.setForeground(color);

        d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        d2.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }



    public void playSturdySong() {
        try {
            URL url = getClass().getResource("sturdy.wav");
            assert url != null;
            File music = new File(url.getPath());
            if (music.exists()) {
                AudioInputStream a = AudioSystem.getAudioInputStream(music);
                Clip c = AudioSystem.getClip();
                c.open(a);
                c.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void playMadara() {
        try {
            URL url = getClass().getResource("wakeup.wav");
            assert url != null;
            File music = new File(url.getPath());
            if (music.exists()) {
                AudioInputStream a = AudioSystem.getAudioInputStream(music);
                Clip c = AudioSystem.getClip();
                c.open(a);
                c.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void makePause(){
         pause1 = new Timer(45000, e -> {

            d.setVisible(false);
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ef) {
                    ef.printStackTrace();
                }
                new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ef) {
                        ef.printStackTrace();
                    }
                    d2.setVisible(false);
                    System.exit(0);
                }).start();
                gif.playMadara();
                d2.setVisible(true);

            }).start();
            JOptionPane.showOptionDialog(null,"Before you go....","Wait",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{},null);

        });
        pause1.setRepeats(false);
    }
    
    

    public static void playFiles(){
        LocalDateTime then = LocalDateTime.now();
        pause1.start();
        gif.playSturdySong();
        do {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                d.setVisible(false);
                randomColor();
                int i= (int) (Math.random() * 1500);
                int j=(int) (Math.random() * 200);
                d.setLocation(i,j);
            }).start();
            randomColor();
            d.setVisible(true);
        } while (ChronoUnit.SECONDS.between(then, LocalDateTime.now()) < 45);
    }

    

    public static void startProgram(){

        makePause();

        int ok= JOptionPane.showConfirmDialog(null,"Would you like to get sturdy?","Sturdy Time",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);

        if(ok==0){
           playFiles();
        }
        else{
            System.exit(0);
        }
    }


}
