/**
 * Created by mikha on 09.05.2017.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import com.alee.laf.WebLookAndFeel;
import de.joergjahnke.common.extendeddevices.WavePlayer;
import de.joergjahnke.gameboy.core.Gameboy;
import de.joergjahnke.gameboy.swing.GameboyFrame;
import net.java.games.input.*;
import net.java.games.input.Component;
import sun.awt.image.InputStreamImageSource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import net.java.games.*;

/**
 * Created by mikha on 28.04.2017.
 */
public class Main {


    public static boolean isWindows() {

        String os = System.getProperty("os.name").toLowerCase();
        //windows
        return (os.contains("win"));

    }

    public static boolean isMac() {

        String os = System.getProperty("os.name").toLowerCase();
        //Mac
        return (os.contains("mac"));

    }

    public static boolean isUnix() {

        String os = System.getProperty("os.name").toLowerCase();
        //linux or unix
        return (os.contains("nix") || os.contains("nux"));

    }

    public static class imgFrame extends JFrame{
        @Override
        public void paint(Graphics g) {
            g.drawImage(imagg2,0,0,null);
            super.paint(g);
        }
    }
    public WavePlayer play=null;
    static String game = "";
    static boolean isplay =false;
   private static int minmax=1;
   static boolean bb = false;
   private static JFrame f2 = new JFrame();
   static JFrame ff2 = new JFrame();
   private static Vector<elem> products = new Vector<>();
   private static Vector<elem> arr = new Vector();
   static BufferedImage imagg2 = null;
   public   Thread t=null;
   public Gameboy gameboy;
    static class newPanel extends JPanel{
       public void paintComponent(Graphics g){
           Image im = null;
           try {
               imagg2=ImageIO.read(Main.class.getClassLoader().getResource("back.jpg"));
           } catch (IOException e) {}
           g.drawImage(imagg2, 0, 0, null);
       }
   }

    static class newPanel2 extends JPanel{
        public void paintComponent(Graphics g){
            Image im = null;
            try {
                imagg2=ImageIO.read(Main.class.getClassLoader().getResource("back2.png"));
            } catch (IOException e) {}
            g.drawImage(imagg2, 0, 0, null);
        }
    }
    Main(){


        JFrame f = new JFrame("Pixel.GameCenter");
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setPreferredSize(new Dimension(870,620));
        f.pack();
        JLabel l = new JLabel();
        JLabel anekdot = new JLabel();
        String url = "http://questforthebest.esy.es/";
        newPanel p= new newPanel();

        f.add(p,"Center");
        f.setResizable(false);

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(1,3));
        pan.add(l,"Left");
        JButton search = new JButton("Search");



        JTextField fild = new JTextField();
        pan.add(fild,"Right");
        pan.add(search);

        f.add(pan,"North");
        l.setFont(new Font(Font.DIALOG,Font.BOLD,17));
        l.setHorizontalTextPosition(SwingConstants.CENTER);

        PopupMenu popup = new PopupMenu();
        MenuItem item = new MenuItem("Close");

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popup.add(item);
        SystemTray tray = SystemTray.getSystemTray();
        BufferedImage imgg = null;
        BufferedImage imagg2=null;
        try {
            imgg = ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
            imagg2=ImageIO.read(Main.class.getClassLoader().getResource("back.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.setIconImage(imgg);

        TrayIcon trayIcon = new TrayIcon(imgg);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Pixel.GameCenter");
        try {
            tray.add(trayIcon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }

            try {
                int n = 0;
                // качаем файл с помощью Stream
                downloadUsingStream(url + "pgc.txt", "pgc.txt", n);
            } catch (IOException e) {
                e.printStackTrace();
            }


            File ff = new File("pgc.txt");
        String s="";
        String ss="";
        String siz="";
        String info="";
        String version="";
        String platform="";
        String icon="";
        int size=0;
        long cou=0;
        // ArrayList<String> products = new ArrayList();
        String sod ="";
        try {
            int c=0;
            BufferedReader r = new BufferedReader(new FileReader(ff));
            while (c!=-1){
                c=r.read();

                if (c!=10){
                    sod+=(char)c;
                }
            }
            r.close();
            ff.delete();
            ff =new File("pgc.txt");
            FileWriter wr = new FileWriter(ff);
            wr.write(sod);
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



        if (ff.exists()) {
            int c = 0;
            try {
                BufferedReader r = new BufferedReader(new FileReader(ff));
                while (c != (int) '*') {
                    c = r.read();
                    s += (char) c;
                }
                s=s.substring(0,s.length()-1);
                l.setText(s);
                s="";
                c='0';
                while (c != (int) '*') {
                    c = r.read();
                    siz += (char) c;
                }
                siz=siz.substring(0,siz.length()-1);
                cou = Long.parseLong(siz);
                for (int i = 0; i < cou; i++) {
                    c='0';
                    while (c != (int) '*') {
                        c = r.read();
                        s += (char) c;
                    }
                    s=s.substring(0,s.length()-1);
                    // s = r.readLine();
                    c='0';
                    while (c != (int) '*') {
                        c = r.read();
                        ss += (char) c;
                    }
                    ss=ss.substring(0,ss.length()-1);
                    c='0';
                    //ss=r.readLine();
                    siz="";
                    while (c != (int) '*') {
                        c = r.read();
                        siz += (char) c;
                    }
                    siz=siz.substring(0,siz.length()-1);
                    c='0';
                    while (c != (int) '*') {
                        c = r.read();
                        info += (char) c;
                    }
                    info=info.substring(0,info.length()-1);
                    c='0';
                    while (c != (int) '*') {
                        c = r.read();
                        version += (char) c;
                    }
                    version=version.substring(0,version.length()-1);
                    c='0';
                    while (c != (int) '*') {
                        c = r.read();
                        platform += (char) c;
                    }
                    platform=platform.substring(0,platform.length()-1);
                    c='0';
                    while (c != (int) '*') {
                        c = r.read();
                        icon += (char) c;
                    }
                    icon=icon.substring(0,icon.length()-1);
                    siz=siz.substring(0,siz.length()-1);
                    size=Integer.parseInt(siz);

                    elem e = new elem(s, version,ss,info,size,platform,icon,"");
                    elem ee = new elem(s, version,ss,info,size,platform,icon,"");

                    products.add(e);
                    arr.add(ee);
                    platform="";
                    siz="";
                    s="";
                    ss="";
                    info="";
                    version="";
                    icon="";
                }
                c='0';
                while (c != (int) '*') {
                    c = r.read();
                    s += (char) c;
                }

                anekdot.setText(s);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        anekdot.setFont(new Font(Font.DIALOG,Font.ITALIC,16));

        // mod.getDataVector().add(products);
       // JPanel list = new JPanel();
        JPanel list = new JPanel();

        list.setLayout(new GridLayout(products.size()/3+1,3));



       // pane.setForeground(new Color(255,255,255,150));
        list.setForeground(new Color(255,255,255,150));
        for (int j=0;j<cou;j++){
                int r = (int) (Math.random()*255)+1;
                int g = (int) (Math.random()*255)+1;
                int bb = (int) (Math.random()*255)+1;
                //products.get(j).setPreferredSize(new Dimension(260,260));
            products.get(j).setLayout(new BorderLayout());
                products.get(j).setBackground(new Color(r,g,bb,150));
            arr.get(j).setBackground(new Color(r,g,bb,150));
            list.add(products.get(j));
            JPanel inform = new JPanel();
            JPanel inform2 = new JPanel();
            URL urli = null;
            JButton b = new JButton();

            b.setBackground(new Color(255,255,255,255));
            JButton b2 = new JButton("+");
            try {
                urli = new URL(url+products.get(j).imageurl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                Image image = ImageIO.read(urli);
                image=image.getScaledInstance(180,180,1);
                b.setIcon(new ImageIcon(image));
                //b2.setIcon(new ImageIcon(image));
            } catch (IOException e) {
                e.printStackTrace();
            }

           // products.get(j).add(ic,"Center");
            //arr.get(j).add(ic2,"Center");

            JLabel lab= new JLabel(products.get(j).name+" v["+products.get(j).version+"]");
            JLabel lab2 = new JLabel(products.get(j).name+" v["+products.get(j).version+"]");

            JButton platf = new JButton("platform: "+products.get(j).platform);
            JButton platf2 = new JButton("platform: "+products.get(j).platform);
            lab.setFont(new Font(Font.DIALOG,Font.BOLD,15));
            lab2.setFont(new Font(Font.DIALOG,Font.BOLD,15));
           // products.get(j).add(lab,"North");
            arr.get(j).add(lab2,"North");

            JButton q=new JButton("?");
            JButton q2=new JButton("?");
            products.get(j).add(b,"Center");
            products.get(j).add(inform,"South");
            inform.add(q,"Center");
//            inform.add(platf,"West");


            arr.get(j).add(b2,"Center");
            arr.get(j).add(q2,"South");
  //          arr.get(j).add(platf2,"South");
            int finalJ = j;
            q.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(q,"<html><p style='width: 200px;'><font align=\"justify\">"+products.get(finalJ).info+"</font></p>><br></br><b>Platform: <font color='red'>"+products.get(finalJ).platform+"</font></b><html>","Game description",JOptionPane.PLAIN_MESSAGE);
                }
            });

            JLabel label = new JLabel();
            JLabel label2 = new JLabel();
            File fil= new File(products.get(j).down);
            if (fil.exists()) {
               products.get(j).status="Downloaded";
            } else products.get(j).status=Long.toString(products.get(j).size)+" bytes";
            label.setText("<html><b>"+products.get(j).status+"</b></html>");
            label2.setText("<html><b>"+products.get(j).status+"</b></html>");

            inform.add(label,"South");
            arr.get(j).add(label2,"South");
            products.get(j).setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            q2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(q,"<html><p style='width: 200px;><font align=\"justify\">"+products.get(finalJ).info+"</font></p>><br></br><b>Platform: <font color='red'>"+products.get(finalJ).platform+"</font></b><html>","Game description",JOptionPane.PLAIN_MESSAGE);
                }
            });

            ActionListener listen = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            };



            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource()==b){
                        try {

                           if(!products.get(finalJ).status.equals("Downloaded")) {
                               downloadUsingStream(url + products.get(finalJ).down, products.get(finalJ).down, products.get(finalJ).size);
                               trayIcon.displayMessage("Download", products.get(finalJ).version + " version of " + products.get(finalJ).name + " was downloaded!", TrayIcon.MessageType.INFO);
                               int dialogButton = JOptionPane.YES_NO_OPTION;
                               int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to run game?", "Download Completed!", dialogButton);
                               if (dialogResult == JOptionPane.YES_OPTION) {
                                   products.get(finalJ).status = "Downloaded";
                               }
                           }


                                JFrame ff = new JFrame();
                                try {
                                    Image img = ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
                                    ff.setIconImage(img);
                                } catch (IOException ee) {
                                    ee.printStackTrace();
                                }
                                de.joergjahnke.gameboy.swing.GameboyCanvas canv = new de.joergjahnke.gameboy.swing.GameboyCanvas();
                                 gameboy = new Gameboy();
                                try {
                                    gameboy.load(new FileInputStream(products.get(finalJ).down));
                                } catch (IOException ee) {
                                    ee.printStackTrace();
                                }

                                JPanel panel = new JPanel();
                                JPanel panel2 = new JPanel();
                                JButton min = new JButton("-");
                                JButton max = new JButton("+");
                                JButton sound = new JButton("Stop");
                                JButton save = new JButton("Save");
                                JButton load = new JButton("Load");


                             play = new WavePlayer(gameboy.getSoundChip());

                                save.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        File ff = new File(products.get(finalJ).name+".dat");
                                        File ff2 = new File(products.get(finalJ).name+".dat");
                                        JFileChooser chooser = new JFileChooser();
                                        int  res = chooser.showSaveDialog(chooser);
                                        if (res ==JFileChooser.APPROVE_OPTION){
                                            ff=chooser.getSelectedFile();
                                            ff2 = new File(ff.getAbsolutePath()+".PixelSound");
                                            DataOutputStream stream = null;
                                            DataOutputStream stream2 = null;
                                            try {
                                                stream = new DataOutputStream(new FileOutputStream(ff));
                                                stream2 = new DataOutputStream(new FileOutputStream(ff2));

                                            } catch (FileNotFoundException e1) {
                                                e1.printStackTrace();
                                            }
                                            try {
                                                    gameboy.serialize(stream);
                                                   // gameboy.getSoundChip().serialize(stream2);

                                                } catch (IOException e1) {
                                                    e1.printStackTrace();
                                                }
                                        }
                                    }
                                });

                                load.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JFileChooser chooser = new JFileChooser();
                                        File ff,ff2;

                                        int  res = chooser.showSaveDialog(chooser);
                                        if (res ==JFileChooser.APPROVE_OPTION){
                                            ff=chooser.getSelectedFile();
                                            ff2 = new File(ff.getAbsolutePath()+".PixelSound");
                                            DataInputStream stream = null;

                                            gameboy.pause();

                                            DataInputStream stream2 = null;

                                            try {
                                                stream = new DataInputStream(new FileInputStream(ff));
                                                stream2 = new DataInputStream(new FileInputStream(ff2));
                                            } catch (FileNotFoundException e1) {
                                                e1.printStackTrace();
                                            }
                                            try {

                                              //  gameboy.load(new FileInputStream(new File(products.get(finalJ).down)));


                                               // gameboy.getSoundChip().deserialize(stream2);
                                                play = new WavePlayer(gameboy.getSoundChip());
                                                gameboy.getSoundChip().addObserver(play);
                                                gameboy.deserialize(stream);
                                              //  t = new Thread(gameboy);
                                                //t.start();
                                                gameboy.resume();


                                               // gameboy.update(play,null);

                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                sound.setFocusable(false);
                                panel2.add(min,"West");
                                panel2.add(max,"East");
                                panel2.add(sound,"South");
                                panel.add(panel2,"North");
                                panel.add(canv,"Center");
                                JPanel p = new JPanel();
                                save.setFocusable(false);
                                load.setFocusable(false);
                                p.add(save,"East");
                                p.add(load,"West");
                                panel2.add(p,"South");

                                canv.setScaling(minmax);

                                min.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        minmax--;
                                        canv.setScaling(minmax);
                                    }
                                });
                                max.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        minmax++;
                                        canv.setScaling(minmax);
                                    }
                                });

                                canv.setGameboy(gameboy);
                                ff.add(panel);
                                canv.setPreferredSize(new Dimension(640,480));
                                canv.setScaling(3);
                                canv.setVisible(true);
                                ff.setPreferredSize(new Dimension(510,530));
                                ff.pack();
                                min.setFocusable(false);
                                max.setFocusable(false);

                                ff.setTitle(products.get(finalJ).name);


                                gameboy.getSoundChip().addObserver(play);



                                
                            

                                sound.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (sound.getText().equals("Start")){
                                            gameboy.resume();
                                            sound.setText("Stop");
                                        } else {
                                            sound.setText("Start");
                                            gameboy.pause();
                                        }
                                    }
                                });
                            Thread tt = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    GamePadController cont = new GamePadController();

                                    try {
                                        Robot r = new Robot();
                                        while (true) {
                                            cont.poll();
                                            if (cont.isButtonPressed(4)) {
                                            r.keyPress(KeyEvent.VK_A);
                                            // System.out.print('a');
                                                r.delay(5);
                                             r.keyRelease(KeyEvent.VK_A);

                                            }
                                            if (cont.isButtonPressed(3)) {
                                                r.keyPress(KeyEvent.VK_B);
                                             //   System.out.print('b');
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_B);

                                            }
                                            if (cont.getXYStickDir()==GamePadController.NORTH) {
                                                r.keyPress(KeyEvent.VK_UP);
                                             //   System.out.print("up");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_UP);
                                            }
                                            if (cont.getXYStickDir()==GamePadController.SOUTH) {
                                                r.keyPress(KeyEvent.VK_DOWN);
                                              //  System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_DOWN);
                                            }
                                            if (cont.getXYStickDir()==GamePadController.WEST) {
                                                r.keyPress(KeyEvent.VK_LEFT);
                                              //  System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_LEFT);
                                            }
                                            if (cont.getXYStickDir()==GamePadController.EAST) {
                                                r.keyPress(KeyEvent.VK_RIGHT);
                                               // System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_RIGHT);
                                            }
                                            if (cont.isButtonPressed(9)) {
                                                r.keyPress(KeyEvent.VK_SPACE);
                                               // System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_SPACE);
                                            }

                                            if (cont.isButtonPressed(10)) {
                                                r.keyPress(KeyEvent.VK_ENTER);
                                                //System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_ENTER);
                                            }

                                        }
                                    } catch (AWTException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });
                            tt.start();

                            Thread t =new Thread(gameboy);
                            t.start();


                            ff.addWindowListener(new WindowListener() {
                                    @Override
                                    public void windowOpened(WindowEvent e) {

                                    }

                                    @Override
                                    public void windowClosing(WindowEvent e) {
                                       gameboy.stop();
                                       play.stop();
                                       t.stop();
                                       tt.stop();
                                    }

                                    @Override
                                    public void windowClosed(WindowEvent e) {
                                        gameboy.stop();
                                        play.stop();
                                        t.stop();
                                        tt.stop();
                                    }

                                    @Override
                                    public void windowIconified(WindowEvent e) {

                                    }

                                    @Override
                                    public void windowDeiconified(WindowEvent e) {

                                    }

                                    @Override
                                    public void windowActivated(WindowEvent e) {

                                    }

                                    @Override
                                    public void windowDeactivated(WindowEvent e) {

                                    }
                                });
                                ff.setVisible(true);



                        } catch (IOException ee) {
                            ee.printStackTrace();
                        }




                    }
                }
            });

            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource()==b2){
                        try {

                            if(!products.get(finalJ).status.equals("Downloaded")) {
                                downloadUsingStream(url + products.get(finalJ).down, products.get(finalJ).down, products.get(finalJ).size);
                                trayIcon.displayMessage("Download", products.get(finalJ).version + " version of " + products.get(finalJ).name + " was downloaded!", TrayIcon.MessageType.INFO);
                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to run game?", "Download Completed!", dialogButton);
                                if (dialogResult == JOptionPane.YES_OPTION) {
                                    products.get(finalJ).status = "Downloaded";
                                }
                            }


                            JFrame ff = new JFrame();
                            try {
                                Image img = ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
                                ff.setIconImage(img);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                            de.joergjahnke.gameboy.swing.GameboyCanvas canv = new de.joergjahnke.gameboy.swing.GameboyCanvas();
                            gameboy = new Gameboy();
                            try {
                                gameboy.load(new FileInputStream(products.get(finalJ).down));
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }

                            JPanel panel = new JPanel();
                            JPanel panel2 = new JPanel();
                            JButton min = new JButton("-");
                            JButton max = new JButton("+");
                            JButton sound = new JButton("Stop");
                            JButton save = new JButton("Save");
                            JButton load = new JButton("Load");


                            play = new WavePlayer(gameboy.getSoundChip());
                            save.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    File ff = new File(products.get(finalJ).name+".dat");
                                    File ff2 = new File(products.get(finalJ).name+".dat");
                                    JFileChooser chooser = new JFileChooser();
                                    int  res = chooser.showSaveDialog(chooser);
                                    if (res ==JFileChooser.APPROVE_OPTION){
                                        ff=chooser.getSelectedFile();
                                        ff2 = new File(ff.getAbsolutePath()+".PixelSound");
                                        DataOutputStream stream = null;
                                        DataOutputStream stream2 = null;
                                        try {
                                            stream = new DataOutputStream(new FileOutputStream(ff));
                                            stream2 = new DataOutputStream(new FileOutputStream(ff2));

                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        }
                                        try {
                                            gameboy.serialize(stream);
                                        //    gameboy.getSoundChip().serialize(stream2);

                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                            });

                            load.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JFileChooser chooser = new JFileChooser();
                                    File ff,ff2;

                                    int  res = chooser.showSaveDialog(chooser);
                                    if (res ==JFileChooser.APPROVE_OPTION){
                                        ff=chooser.getSelectedFile();
                                        ff2 = new File(ff.getAbsolutePath()+".PixelSound");
                                        DataInputStream stream = null;

                                        gameboy.pause();

                                        DataInputStream stream2 = null;

                                        try {
                                            stream = new DataInputStream(new FileInputStream(ff));
                                            stream2 = new DataInputStream(new FileInputStream(ff2));
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        }
                                        try {

                                            //  gameboy.load(new FileInputStream(new File(products.get(finalJ).down)));


                                            // gameboy.getSoundChip().deserialize(stream2);
                                            play = new WavePlayer(gameboy.getSoundChip());
                                            gameboy.getSoundChip().addObserver(play);
                                            gameboy.deserialize(stream);
                                            //  t = new Thread(gameboy);
                                            //t.start();
                                            gameboy.resume();


                                            // gameboy.update(play,null);

                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                            });
                            sound.setFocusable(false);
                            panel2.add(min,"West");
                            panel2.add(max,"East");
                            panel2.add(sound,"South");
                            panel.add(panel2,"North");
                            panel.add(canv,"Center");
                            JPanel p = new JPanel();
                            save.setFocusable(false);
                            load.setFocusable(false);
                            p.add(save,"East");
                            p.add(load,"West");
                            panel2.add(p,"South");

                            canv.setScaling(minmax);

                            min.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    minmax--;
                                    canv.setScaling(minmax);
                                }
                            });
                            max.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    minmax++;
                                    canv.setScaling(minmax);
                                }
                            });

                            canv.setGameboy(gameboy);
                            ff.add(panel);
                            canv.setPreferredSize(new Dimension(640,480));
                            canv.setScaling(3);
                            canv.setVisible(true);
                            ff.setPreferredSize(new Dimension(510,530));
                            ff.pack();
                            min.setFocusable(false);
                            max.setFocusable(false);

                            ff.setTitle(products.get(finalJ).name);


                            gameboy.getSoundChip().addObserver(play);






                            sound.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (sound.getText().equals("Start")){
                                        gameboy.resume();
                                        sound.setText("Stop");
                                    } else {
                                        sound.setText("Start");
                                        gameboy.pause();
                                    }
                                }
                            });
                            Thread tt = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    GamePadController cont = new GamePadController();

                                    try {
                                        Robot r = new Robot();
                                        while (true) {
                                            cont.poll();
                                            if (cont.isButtonPressed(4)) {
                                                r.keyPress(KeyEvent.VK_A);
                                                // System.out.print('a');
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_A);

                                            }
                                            if (cont.isButtonPressed(3)) {
                                                r.keyPress(KeyEvent.VK_B);
                                                //   System.out.print('b');
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_B);

                                            }
                                            if (cont.getXYStickDir()==GamePadController.NORTH) {
                                                r.keyPress(KeyEvent.VK_UP);
                                                //   System.out.print("up");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_UP);
                                            }
                                            if (cont.getXYStickDir()==GamePadController.SOUTH) {
                                                r.keyPress(KeyEvent.VK_DOWN);
                                                //  System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_DOWN);
                                            }
                                            if (cont.getXYStickDir()==GamePadController.WEST) {
                                                r.keyPress(KeyEvent.VK_LEFT);
                                                //  System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_LEFT);
                                            }
                                            if (cont.getXYStickDir()==GamePadController.EAST) {
                                                r.keyPress(KeyEvent.VK_RIGHT);
                                                // System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_RIGHT);
                                            }
                                            if (cont.isButtonPressed(9)) {
                                                r.keyPress(KeyEvent.VK_SPACE);
                                                // System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_SPACE);
                                            }

                                            if (cont.isButtonPressed(10)) {
                                                r.keyPress(KeyEvent.VK_ENTER);
                                                //System.out.print("down");
                                                r.delay(5);
                                                r.keyRelease(KeyEvent.VK_ENTER);
                                            }

                                        }
                                    } catch (AWTException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });
                            tt.start();

                            Thread t =new Thread(gameboy);
                            t.start();


                            ff.addWindowListener(new WindowListener() {
                                @Override
                                public void windowOpened(WindowEvent e) {

                                }

                                @Override
                                public void windowClosing(WindowEvent e) {
                                    gameboy.stop();
                                    play.stop();
                                    t.stop();
                                    tt.stop();
                                }

                                @Override
                                public void windowClosed(WindowEvent e) {
                                    gameboy.stop();
                                    play.stop();
                                    t.stop();
                                    tt.stop();
                                }

                                @Override
                                public void windowIconified(WindowEvent e) {

                                }

                                @Override
                                public void windowDeiconified(WindowEvent e) {

                                }

                                @Override
                                public void windowActivated(WindowEvent e) {

                                }

                                @Override
                                public void windowDeactivated(WindowEvent e) {

                                }
                            });
                            ff.setVisible(true);



                        } catch (IOException ee) {
                            ee.printStackTrace();
                        }




                    }
                }
            });


        }
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == search) {
                    try {
                        Image img = ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
                        f2.setIconImage(img);
                    } catch (IOException ee) {
                        ee.printStackTrace();
                    }




                    if (!fild.getText().equals("")) {

                        JPanel p = new JPanel();
                        p.setLayout(new GridLayout(arr.size(), 1));
                        int cou=1;
                        for (int n = 0; n < arr.size(); n++) {
                            if (arr.get(n).name.contains(fild.getText())) {

                                arr.get(n).setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                                p.add(arr.get(n));
                                System.out.print(0);
                                cou+=1;
                            }
                        }
                        JScrollPane pane = new JScrollPane(p);
                        pane.setPreferredSize(new Dimension(580,480));

                        f2.add(pane);
                        f2.setTitle("Results of : "+fild.getText());
                        f2.setPreferredSize(new Dimension(600, 500));

                        f2.pack();
                        f2.repaint();
                        f2.setVisible(true);
                    }

                    f2.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                            f2.removeAll();
                            f2 = new JFrame();
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {

                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });


                }

            }
        });


        // list.add(pane);
       Container c = new Container();
       JScrollPane pp = new JScrollPane(list);



       pp.setPreferredSize(new Dimension(830,530));
    //pp.setViewportView(list);
        p.add(pp,"Center");
        p.add(anekdot,"South");


        f.repaint();
        f.setVisible(true);

    }

    class elem  extends JPanel{
        String name,version,down,info,platform,imageurl,status;
        int size;
        elem(String name, String version,String down,String info,int size,String platform,String imageurl,String status){
            this.down=down;
            this.name=name;
            this.version=version;
            this.size=size;
            this.info=info;
            this.platform=platform;
            this.imageurl = imageurl;
            this.status= status;
        }

    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();

            }
        });


    }

public class gbthread extends Thread {
        String nam;

        gbthread(String nam){
            this.nam=nam;

        }

        public void run(){

        }
}
    // качаем файл с помощью Stream
    private static void downloadUsingStream(String urlStr, String file,int size) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}
