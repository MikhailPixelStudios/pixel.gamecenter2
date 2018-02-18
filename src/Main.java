/**
 * Created by mikha on 09.05.2017.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import com.alee.extended.drag.FileDragAndDropHandler;
import com.alee.laf.WebLookAndFeel;
import de.joergjahnke.common.extendeddevices.WavePlayer;
import de.joergjahnke.gameboy.core.Gameboy;
import de.joergjahnke.gameboy.swing.GameboyFrame;
import net.java.games.input.*;
import net.java.games.input.Component;
import sun.awt.image.InputStreamImageSource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public static class imgFrame extends JFrame {
        @Override
        public void paint(Graphics g) {
            g.drawImage(imagg2, 0, 0, null);
            super.paint(g);
        }
    }

    public TrayIcon trayIcon;
    public WavePlayer play = null;
    static String game = "";
    static boolean isplay = false;
    static boolean openusr=false;
    private static int minmax = 3;
    static boolean bb = false;
    private static JFrame f2 = new JFrame();
    static JFrame ff2 = new JFrame();
    private static Vector<elem> products = new Vector<>();
    private static Vector<elem> arr = new Vector();
    static BufferedImage imagg2 = null;
    public Thread t = null;
    public Gameboy gameboy;
    public String url = "";
    public JCheckBox gamepad;
    static boolean islogin = false;
    static JFrame frame = new JFrame("Pixel.Accoint");

    public static int tek=0;
    static int nottek=0;
    public static String content="";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    public static String query;
    private static final String url2 = "jdbc:mysql://sql11.freemysqlhosting.net/sql11177728";
    public static final String user = "sql11177728";
    private static final String password = "k5YlrACJe4";
    static String img="";

    static class newPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                imagg2 = ImageIO.read(Main.class.getClassLoader().getResource("back.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(imagg2, 0, 0, null);
        }
    }
static class newmenubar extends JMenuBar{
    public void paintComponent(Graphics g) {
        Image im = null;
        try {
            imagg2 = ImageIO.read(Main.class.getClassLoader().getResource("bar.jpg"));
        } catch (IOException e) {
        }
        g.drawImage(imagg2, 0, 0, null);
    }
}
    static class newPanel2 extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                imagg2 = ImageIO.read(Main.class.getClassLoader().getResource("back2.png"));
            } catch (IOException e) {
            }
            g.drawImage(imagg2, 0, 0, null);
        }
    }
    public static JTextField username = new JTextField();
    public static   JTextField pass = new JPasswordField();
    public static Object[] message = {
            "Username:", username,
            "Password:", pass
    };
    Main() {


        JFrame f = new JFrame("Pixel.GameCenter");
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setPreferredSize(new Dimension(890, 620));
        f.pack();
        JLabel l = new JLabel();
        JLabel anekdot = new JLabel();
        url = "http://questforthebest.esy.es/";
        newPanel p = new newPanel();

        f.add(p, "Center");
        f.setResizable(false);
        f.setLocationRelativeTo(null);
gamepad = new JCheckBox("Gamepad");
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(1, 3));
        //pan.add(l, "Left");
        JButton search = new JButton("Search");

        JPanel pan2 = new JPanel();
        //JPanel user = new JPanel();
        JButton open_user = new JButton();

        Image op2 = null;
        try {
            op2 = ImageIO.read(Main.class.getClassLoader().getResource("user.jpg"));
            op2 = op2.getScaledInstance(20, 20, 1);
            open_user.setIcon(new ImageIcon(op2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        open_user.setToolTipText("Login into Pixel.Account");
        pan2.add(pan, "Center");
     //  pan.add(user,"East");
      //  p.add(user,"East");
        newmenubar bar = new newmenubar();

        Image op = null;
        JButton ope = new JButton();
        try {
            op = ImageIO.read(Main.class.getClassLoader().getResource("open.png"));
            op = op.getScaledInstance(20, 20, 1);
            ope.setIcon(new ImageIcon(op));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JTextField fild = new JTextField(10);
        pan.add(fild, "Right");
        pan.add(search);
        bar.add(open_user);
        bar.add(l);
        bar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bar.add(gamepad);
        bar.add(fild);
        bar.add(search);
        bar.add(Box.createHorizontalGlue());
        bar.add(ope);
        f.setJMenuBar(bar);
        f.add(pan, "North");
        l.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        l.setHorizontalTextPosition(SwingConstants.CENTER);


ope.setToolTipText("Load ROM");
        ope.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                File ff, ff2;

                int res = chooser.showSaveDialog(chooser);
                if (res == JFileChooser.APPROVE_OPTION) {
                    ff = chooser.getSelectedFile();
                   elem ee = new elem("\""+ff.getName()+"\"","fin",ff.getName(),"",(int)ff.getTotalSpace(),"","","");

                GameBoyFrame f = new GameBoyFrame(ee);
                }
            }
        });

       f.setDropTarget(new DropTarget(){
           public synchronized void drop(DropTargetDropEvent evt) {
               try {
                 //  f.getGraphics().drawString("Drop here to play!",evt.getLocation().x,evt.getLocation().y);

                   evt.acceptDrop(DnDConstants.ACTION_COPY);

                   DataFlavor[] flavors = evt.getTransferable().getTransferDataFlavors();
                   DataFlavor flavor = flavors[0];
                       // If the drop items are files
                  java.util.List<File> droppedFiles= (java.util.List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                  File file = droppedFiles.get(0);

                           elem ee = new elem("\""+file.getName()+"\"","fin",file.getName(),"",(int)file.getTotalSpace(),"","","");
                           GameBoyFrame fr = new GameBoyFrame(ee);




               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       });
        PopupMenu popup = new PopupMenu();
        MenuItem item = new MenuItem("Close");

open_user.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JButton use = new JButton("");
                                    Image op3 = null;
                                    try {
                                        op3 = ImageIO.read(Main.class.getClassLoader().getResource("user.jpg"));
                                        op3 = op3.getScaledInstance(350, 350, 1);
                                        use.setIcon(new ImageIcon(op3));
                                    } catch (IOException ee) {
                                        ee.printStackTrace();
                                    }

                                    frame.setPreferredSize(new Dimension(400, 600));
                                    frame.setResizable(false);
                                    JPanel panel = new JPanel();
                                    JPanel org = new JPanel();

                                    org.setLayout(new GridLayout(3,1));

                                    JButton chat = new JButton();
                                    Image op4 = null;
                                    try {
                                        op4 = ImageIO.read(Main.class.getClassLoader().getResource("dialog.jpg"));
                                        op4 = op4.getScaledInstance(50, 50, 1);
                                        chat.setIcon(new ImageIcon(op4));
                                    } catch (IOException ee) {
                                        ee.printStackTrace();
                                    }
                                    org.add(chat);

                                    frame.add(panel);
                                    panel.add(use, "Center");
                                    panel.add(org,"South");
                                    use.setToolTipText("Login into your Pixel.Account");
                                    try {
                                        Image img= ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
                                        frame.setIconImage(img);
                                    } catch (IOException ee) {
                                        ee.printStackTrace();
                                    }

                                    if (!openusr) {
                                        use.setPreferredSize(new Dimension(350, 350));
                                        //frame.add(use, "Center");
                                        frame.setVisible(true);
                                        openusr = !openusr;
                                    } else {
                                        frame.setVisible(false);
                                        openusr = !openusr;
                                    }
                                    frame.pack();
                                    use.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            int res = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
                                            if (res == JOptionPane.OK_OPTION) {
                                                query = "select * from `usertbl` where `username` like " + "\"" + username.getText() + "\" and `password` like \"" + pass.getText() + "\"";
                                                try {
                                                    Class.forName("org.h2.Driver");
                                                } catch (ClassNotFoundException e3) {
                                                    e3.printStackTrace();
                                                }
                                                try {
                                                    con = DriverManager.getConnection(url2, user, password);
                                                } catch (SQLException e1) {
                                                    e1.printStackTrace();
                                                }

                                                // getting Statement object to execute query
                                                try {
                                                    stmt = con.createStatement();
                                                } catch (SQLException e1) {
                                                    e1.printStackTrace();
                                                }

                                                // executing SELECT query
                                                try {
                                                    rs = stmt.executeQuery(query);
                                                } catch (SQLException e1) {
                                                    e1.printStackTrace();
                                                }
                                                boolean bbb = false;
                                                String fname="";
                                                try {
                                                    while (rs.next()) {
                                                        String count = null;
                                                        try {
                                                            count = rs.getString(3);
                                                        } catch (SQLException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                        if (rs.getString(4).equals(username.getText()) && rs.getString(5).equals(pass.getText()))
                                                            System.out.println("You are logged in. INFO : " + count);
                                                        bbb = rs.getString(4).equals(username.getText()) && rs.getString(5).equals(pass.getText());
                                                        img=rs.getString(6);
                                                        fname=rs.getString(1);
                                                    }


                                                    if (rs.wasNull() || !bbb){
                                                        JOptionPane.showMessageDialog(null,"Wrong username or password");
                                                       // System.exit(0);
                                                    } else {
                                                        islogin=true;
                                                        JOptionPane.showMessageDialog(null, "You are successfully logged in");
                                                        java.util.Date d = new java.util.Date();
                                                        use.setToolTipText("Welcome, "+fname);
                                                       // query = "INSERT INTO `Message` (`Login`,`Date`,`Message`) VALUES ('" + username.getText() + "','" + d.toString() + "','JOINED THE CHAT')";

                                                      //  stmt.executeUpdate(query);
                                                        ImageIcon i = new ImageIcon(new URL(img));
                                                        i=new ImageIcon(i.getImage().getScaledInstance(350,350,1));
                                                        use.setIcon(i);
                                                        use.updateUI();
                                                        query = "select * from `Notif`";
                                                        try {
                                                            rs = stmt.executeQuery(query);
                                                        } catch (SQLException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                        try {
                                                            while (rs.next()) {
                                                                tek += 1;
                                                            }
                                                        } catch (SQLException e1) {
                                                            e1.printStackTrace();
                                                        }

                                                        javax.swing.Timer t1 = new javax.swing.Timer(5000, new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {


                                                                query = "select * from `Notif`";

                                                                try {
                                                                    rs = stmt.executeQuery(query);
                                                                } catch (SQLException e1) {
                                                                    e1.printStackTrace();
                                                                }
                                                                int cou = 0;

                                                                try {
                                                                    while (rs.next()) {
                                                                        cou += 1;
                                                                        if (cou > tek) {
                                                                           if (!rs.getString(1).equals(username.getText())) trayIcon.displayMessage("Pixel.GameCenter",rs.getString(2), TrayIcon.MessageType.INFO);

                                                                        }
                                                                    }
                                                                } catch (SQLException e1) {
                                                                    e1.printStackTrace();
                                                                }
                                                                tek = cou;
                                                            }
                                                        });
                                                        t1.start();
                                                    }

                                                } catch (SQLException e1) {
                                                    e1.printStackTrace();
                                                } catch (MalformedURLException e1) {
                                                    e1.printStackTrace();
                                                }

                                            }
                                            frame.addWindowListener(new WindowListener() {
                                                @Override
                                                public void windowOpened(WindowEvent e) {

                                                }

                                                @Override
                                                public void windowClosing(WindowEvent e) {
                                                    openusr = false;
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
                                    });

                                    chat.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (islogin) {
                                                JFrame f = new JFrame();
                                                JPanel p = new JPanel();
                                                JPanel dp = new JPanel();
                                                JPanel p2 = new JPanel();
                                                JTextField fild = new JTextField(10);
                                                JButton b = new JButton("Send");
                                                JButton b2 = new JButton("R");
                                                p2.setLayout(new GridLayout(1, 3));
                                                JTextPane area = new JTextPane();
                                                JScrollPane pan = new JScrollPane(area);
                                                area.setContentType("text/html");
                                                content = "<html>";
                                                area.setPreferredSize(new Dimension(500, 600));
                                                f.setPreferredSize(new Dimension(550, 740));
                                                f.add(p);
                                                f.setResizable(false);
                                                pan.setPreferredSize(new Dimension(520, 640));
                                                // dp.setPreferredSize(new Dimension(520,880));
                                                pan.getVerticalScrollBar().setValue(pan.getVerticalScrollBar().getMinimum());

                                                p.add(pan, "Center");

                                                p.add(p2, "South");
                                                p2.add(fild);
                                                p2.add(b);

                                                area.setEditable(false);
                                                try {
                                                    Image img = ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
                                                    f.setIconImage(img);
                                                } catch (IOException ee) {
                                                    ee.printStackTrace();
                                                }

                                                f.setTitle("Pixel.Chat Speaker: " + username.getText());
                                                query = "select * from `Message`";
                                                try {
                                                    rs = stmt.executeQuery(query);
                                                } catch (SQLException e1) {
                                                    e1.printStackTrace();
                                                }
                                                try {
                                                    while (rs.next()) {
                                                        tek += 1;
                                                        content += rs.getString(3);

                                                        area.setText(content);
                                                    }
                                                } catch (SQLException e1) {
                                                    e1.printStackTrace();
                                                }


                                                b.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (!fild.getText().equals("")) {
                                                            java.util.Date d = new java.util.Date();
                                                            String fincont = "<br><p style=\"border: 1px solid black; padding: 5px;\"><img src=\"" + img + "\" width=40 heigth=40>" + username.getText() + ": <b>" + fild.getText() + "</b> (" + d.toString() + ")</p></br>";
                                                            query = "INSERT INTO `Message` (`Login`,`Date`,`Message`) VALUES ('" + username.getText() + "','" + d.toString() + "','" + fincont + "')";
                                                            try {
                                                                stmt.executeUpdate(query);
                                                            } catch (SQLException e1) {
                                                                e1.printStackTrace();
                                                            }
                                                            fild.setText("");
                                                            //pan.getVerticalScrollBar().setValue(pan.getVerticalScrollBar().getMaximum());
                                                        }
                                                    }
                                                });
                                                javax.swing.Timer t = new javax.swing.Timer(2000, new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        query = "select * from `Message`";

                                                        try {
                                                            rs = stmt.executeQuery(query);
                                                        } catch (SQLException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                        int cou = 0;

                                                        try {
                                                            while (rs.next()) {
                                                                cou += 1;
                                                                if (cou > tek) {
                                                                    content += rs.getString(3);
                                                                    area.setText(content);
                                                                    pan.getVerticalScrollBar().setValue(pan.getVerticalScrollBar().getMaximum());

                                                                }
                                                            }
                                                        } catch (SQLException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                        tek = cou;
                                                    }
                                                });


                                                f.pack();
                                                f.setVisible(true);
                                                t.start();
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Please, login into your Pixel.Account!", "Error", JOptionPane.OK_CANCEL_OPTION);
                                            }
                                        }
                                    });
                                }

                            });


        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popup.add(item);
        SystemTray tray = SystemTray.getSystemTray();
        BufferedImage imgg = null;
        BufferedImage imagg2 = null;
        try {
            imgg = ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
            imagg2 = ImageIO.read(Main.class.getClassLoader().getResource("back.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.setIconImage(imgg);

        trayIcon = new TrayIcon(imgg);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Pixel.GameCenter");
        try {
            tray.add(trayIcon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
 if (checkInternetConnection()) {
     try {
         int n = 0;
         // качаем файл с помощью Stream
         downloadUsingStream(url + "pgc.txt", "pgc.txt", n);
     } catch (IOException e) {
         e.printStackTrace();
     }
 } else {
     File fil = new File("pgc.txt");
     if (fil.exists()){
        JOptionPane.showMessageDialog(f,"There is a problem with your Internet connection! Would you like to continue in offline mode?");
     } else  {
         JOptionPane.showMessageDialog(f,"There is a problem with your Internet connection!");
         System.exit(0);
     }
 }

        File ff = new File("pgc.txt");
        String s = "";
        String ss = "";
        String siz = "";
        String info = "";
        String version = "";
        String platform = "";
        String icon = "";
        int size = 0;
        long cou = 0;
        // ArrayList<String> products = new ArrayList();
        String sod = "";
        try {
            int c = 0;
            BufferedReader r = new BufferedReader(new FileReader(ff));
            while (c != -1) {
                c = r.read();

                if (c != 10) {
                    sod += (char) c;
                }
            }
            r.close();
            ff.delete();
            ff = new File("pgc.txt");
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
                s = s.substring(0, s.length() - 1);
                l.setText(s);
                s = "";
                c = '0';
                while (c != (int) '*') {
                    c = r.read();
                    siz += (char) c;
                }
                siz = siz.substring(0, siz.length() - 1);
                cou = Long.parseLong(siz);
                for (int i = 0; i < cou; i++) {
                    c = '0';
                    while (c != (int) '*') {
                        c = r.read();
                        s += (char) c;
                    }
                    s = s.substring(0, s.length() - 1);
                    // s = r.readLine();
                    c = '0';
                    while (c != (int) '*') {
                        c = r.read();
                        ss += (char) c;
                    }
                    ss = ss.substring(0, ss.length() - 1);
                    c = '0';
                    //ss=r.readLine();
                    siz = "";
                    while (c != (int) '*') {
                        c = r.read();
                        siz += (char) c;
                    }
                    siz = siz.substring(0, siz.length() - 1);
                    c = '0';
                    while (c != (int) '*') {
                        c = r.read();
                        info += (char) c;
                    }
                    info = info.substring(0, info.length() - 1);
                    c = '0';
                    while (c != (int) '*') {
                        c = r.read();
                        version += (char) c;
                    }
                    version = version.substring(0, version.length() - 1);
                    c = '0';
                    while (c != (int) '*') {
                        c = r.read();
                        platform += (char) c;
                    }
                    platform = platform.substring(0, platform.length() - 1);
                    c = '0';
                    while (c != (int) '*') {
                        c = r.read();
                        icon += (char) c;
                    }
                    icon = icon.substring(0, icon.length() - 1);
                    siz = siz.substring(0, siz.length() - 1);
                    size = Integer.parseInt(siz);

                    elem e = new elem(s, version, ss, info, size, platform, icon, "");
                    elem ee = new elem(s, version, ss, info, size, platform, icon, "");

                    products.add(e);
                    arr.add(ee);
                    platform = "";
                    siz = "";
                    s = "";
                    ss = "";
                    info = "";
                    version = "";
                    icon = "";
                }
                c = '0';
                while (c != (int) '*') {
                    c = r.read();
                    s += (char) c;
                }

                anekdot.setText(s);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        anekdot.setFont(new Font(Font.DIALOG, Font.ITALIC, 16));

        // mod.getDataVector().add(products);
        // JPanel list = new JPanel();
        JPanel list = new JPanel();

        list.setLayout(new GridLayout(products.size() / 3 + 1, 3));


        // pane.setForeground(new Color(255,255,255,150));
        list.setForeground(new Color(255, 255, 255, 150));
        for (int j = 0; j < cou; j++) {
            int r = (int) (Math.random() * 255) + 1;
            int g = (int) (Math.random() * 255) + 1;
            int bb = (int) (Math.random() * 255) + 1;
            //products.get(j).setPreferredSize(new Dimension(260,260));
            products.get(j).setLayout(new BorderLayout());
            products.get(j).setBackground(new Color(r, g, bb, 150));
            arr.get(j).setBackground(new Color(r, g, bb, 150));
            list.add(products.get(j));
            JPanel inform = new JPanel();
            JPanel inform2 = new JPanel();
            URL urli = null;
            JButton b = new JButton();

            b.setBackground(new Color(255, 255, 255, 255));
            JButton b2 = new JButton("+");
            try {
                urli = new URL(url + products.get(j).imageurl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                Image image = ImageIO.read(urli);
                image = image.getScaledInstance(180, 180, 1);
                b.setIcon(new ImageIcon(image));
                //b2.setIcon(new ImageIcon(image));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // products.get(j).add(ic,"Center");
            //arr.get(j).add(ic2,"Center");

            JLabel lab = new JLabel(products.get(j).name + " v[" + products.get(j).version + "]");
            JLabel lab2 = new JLabel(products.get(j).name + " v[" + products.get(j).version + "]");

            JButton platf = new JButton("platform: " + products.get(j).platform);
            JButton platf2 = new JButton("platform: " + products.get(j).platform);
            lab.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
            lab2.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
            // products.get(j).add(lab,"North");
            arr.get(j).add(lab2, "North");

            JButton q = new JButton("?");
            JButton q2 = new JButton("?");
            products.get(j).add(b, "Center");
            products.get(j).add(inform, "South");
            inform.add(q, "Center");
//            inform.add(platf,"West");


            arr.get(j).add(b2, "Center");
            arr.get(j).add(q2, "South");
            //          arr.get(j).add(platf2,"South");
            int finalJ = j;
            q.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(q, "<html><p style='width: 200px;'><font align=\"justify\">" + products.get(finalJ).info + "</font></p>><br></br><b>Platform: <font color='red'>" + products.get(finalJ).platform + "</font></b><html>", "Game description", JOptionPane.PLAIN_MESSAGE);
                }
            });

            JLabel label = new JLabel();
            JLabel label2 = new JLabel();
            File fil = new File(products.get(j).down);
            if (fil.exists()) {
                products.get(j).status = "Downloaded";
            } else products.get(j).status = Long.toString(products.get(j).size) + " bytes";
            label.setText("<html><b>" + products.get(j).status + "</b></html>");
            label2.setText("<html><b>" + products.get(j).status + "</b></html>");

            inform.add(label, "South");
            arr.get(j).add(label2, "South");
            products.get(j).setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            q2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(q, "<html><p style='width: 200px;><font align=\"justify\">" + products.get(finalJ).info + "</font></p>><br></br><b>Platform: <font color='red'>" + products.get(finalJ).platform + "</font></b><html>", "Game description", JOptionPane.PLAIN_MESSAGE);
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
                    if (e.getSource() == b) {
                        GameBoyFrame gbf = new GameBoyFrame(products.get(finalJ));
                        query = "INSERT INTO `Notif` (`username`,`notif`) VALUES ('" + username.getText() + "','" + username.getText() + " is playing "+products.get(finalJ).name+"')";
                        try {
                            stmt.executeUpdate(query);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });

            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == b2) {
                        GameBoyFrame gbf = new GameBoyFrame(products.get(finalJ));
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
                        int cou = 1;
                        for (int n = 0; n < arr.size(); n++) {
                            if (arr.get(n).name.contains(fild.getText())) {

                                arr.get(n).setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                                p.add(arr.get(n));
                                System.out.print(0);
                                cou += 1;
                            }
                        }
                        JScrollPane pane = new JScrollPane(p);
                        pane.setPreferredSize(new Dimension(580, 480));

                        f2.add(pane);
                        f2.setTitle("Results of : " + fild.getText());
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


        pp.setPreferredSize(new Dimension(870, 530));
        //pp.setViewportView(list);
        p.add(pp, "Center");

        p.add(anekdot, "South");


        f.repaint();
        f.setVisible(true);
    }
static int oldw=0;
    static Color cc = null;
  static   int oldh=0;
    class GameBoyFrame extends JFrame {
        elem e;

        GameBoyFrame(elem e) {
            this.e = e;
            try {
              if (checkInternetConnection()) {
                  if (!e.status.equals("Downloaded")) {
                      downloadUsingStream(url + e.down, e.down, e.size);
                      trayIcon.displayMessage("Download", e.version + " version of " + e.name + " was downloaded!", TrayIcon.MessageType.INFO);
                      int dialogButton = JOptionPane.YES_NO_OPTION;
                      int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to run game?", "Download Completed!", dialogButton);
                      if (dialogResult == JOptionPane.YES_OPTION) {
                          e.status = "Downloaded";
                      }
                  }
              } else JOptionPane.showMessageDialog(this,"There is a problem with your internet connection!");


                try {
                    Image img = ImageIO.read(Main.class.getClassLoader().getResource("drawing.png"));
                    this.setIconImage(img);
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
                de.joergjahnke.gameboy.swing.GameboyCanvas canv = new de.joergjahnke.gameboy.swing.GameboyCanvas();
                gameboy = new Gameboy();

                try {
                    gameboy.load(new FileInputStream(e.down));
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
                int w= Toolkit.getDefaultToolkit ().getScreenSize ().width;
                int h = Toolkit.getDefaultToolkit ().getScreenSize ().height;
                JPanel panel = new JPanel();
                JPanel panel2 = new JPanel();
                JMenuBar bar = new JMenuBar();
                JButton min = new JButton("-");
                JButton max = new JButton("+");
                JButton sound = new JButton("Stop");
                JButton save = new JButton("Save");
                JButton load = new JButton("Load");
                JCheckBox ful = new JCheckBox("Fullscreen");
                GameBoyFrame gbf = this;
                 oldw =this.getWidth();
                 oldh = this.getHeight();
                 ful.setFocusable(false);
                 int oldscal=0;
                ful.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (ful.isSelected()) {
                             oldw = gbf.getWidth();
                             oldh = gbf.getHeight();
                            cc = panel.getBackground();
                            panel.setBackground(Color.BLACK);
                            gbf.repaint();
                            min.disable();
                            max.disable();

                            canv.setScaling(h/144);
                            gbf.setExtendedState(JFrame.MAXIMIZED_BOTH);
                         //   gbf.setUndecorated(true);

                        } else {
                            gbf.setPreferredSize(new Dimension(oldw,oldh));
                            panel.setBackground(cc);
                            min.enable();
                            max.enable();
                            canv.setScaling(minmax);
                            gbf.pack();
                        }
                    }
                });
                bar.add(min);
                bar.add(max);
                bar.add(sound);
                bar.add(save);
                bar.add(load);
                bar.add(ful);





                play = new WavePlayer(gameboy.getSoundChip());
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        File ff = new File(e.name + ".dat");
                        File ff2 = new File(e.name + ".dat");
                        JFileChooser chooser = new JFileChooser();
                        int res = chooser.showSaveDialog(chooser);
                        if (res == JFileChooser.APPROVE_OPTION) {
                            ff = chooser.getSelectedFile();
                        //    ff2 = new File(ff.getAbsolutePath() + ".PixelSound");
                            DataOutputStream stream = null;
                            DataOutputStream stream2 = null;
                            try {
                                stream = new DataOutputStream(new FileOutputStream(ff));
                               // stream2 = new DataOutputStream(new FileOutputStream(ff2));

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
                        File ff, ff2;

                        int res = chooser.showSaveDialog(chooser);
                        if (res == JFileChooser.APPROVE_OPTION) {
                            ff = chooser.getSelectedFile();
                            ff2 = new File(ff.getAbsolutePath() + ".PixelSound");
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

                panel.add(canv, "Center");
               // JPanel p = new JPanel();
                save.setFocusable(false);
                load.setFocusable(false);
               // p.add(save, "East");
                //p.add(load, "West");
                bar.setLayout(new FlowLayout(FlowLayout.CENTER));
                this.setJMenuBar(bar);

                canv.setScaling(minmax);

                min.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!ful.isSelected()) {
                            minmax--;
                            canv.setScaling(minmax);
                        }
                    }
                });
                max.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!ful.isSelected()) {
                            minmax++;
                            canv.setScaling(minmax);
                        }
                    }
                });

                canv.setGameboy(gameboy);
                this.add(panel);
                canv.setPreferredSize(new Dimension(640, 480));
                canv.setScaling(3);
                canv.setVisible(true);
                this.setPreferredSize(new Dimension(510, 530));
                this.pack();
                min.setFocusable(false);
                max.setFocusable(false);

                this.setTitle(e.name);


                gameboy.getSoundChip().addObserver(play);


                sound.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (sound.getText().equals("Start")) {
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
                        if (gamepad.isSelected()) {
                            GamePadController cont = new GamePadController();
                            int k=0;
                            try {
                                Robot r = new Robot();
                                while (true) {
                                    cont.poll();
                                    if (cont.isButtonPressed(4)) {
                                        Thread t1 = new Thread(new Runnable() {
                                            @Override
                                            public void run() {

                                            }
                                        });
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
                                    if (cont.getXYStickDir() == GamePadController.NORTH) {
                                        r.keyPress(KeyEvent.VK_UP);
                                        //   System.out.print("up");
                                       r.delay(5);
                                        r.keyRelease(KeyEvent.VK_UP);
                                    }
                                    if (cont.getXYStickDir() == GamePadController.SOUTH) {
                                        r.keyPress(KeyEvent.VK_DOWN);
                                        //  System.out.print("down");
                                        r.delay(5);
                                        r.keyRelease(KeyEvent.VK_DOWN);
                                    }
                                    if (cont.getXYStickDir() == GamePadController.WEST) {
                                        r.keyPress(KeyEvent.VK_LEFT);
                                        //  System.out.print("down");
                                        r.delay(5);
                                        r.keyRelease(KeyEvent.VK_LEFT);
                                    }
                                    if (cont.getXYStickDir() == GamePadController.EAST) {
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

                                    if (cont.getXYStickDir() == GamePadController.NONE){

                                    }

                                }
                            } catch (AWTException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                if (gamepad.isSelected())  tt.start();

                Thread t = new Thread(gameboy);
                t.start();


                this.addWindowListener(new WindowListener() {
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
                this.setVisible(true);


            } catch (IOException ee) {
                ee.printStackTrace();
            }


        }
    }

    class elem extends JPanel {
        String name, version, down, info, platform, imageurl, status;
        int size;

        elem(String name, String version, String down, String info, int size, String platform, String imageurl, String status) {
            this.down = down;
            this.name = name;
            this.version = version;
            this.size = size;
            this.info = info;
            this.platform = platform;
            this.imageurl = imageurl;
            this.status = status;
        }

    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();

            }
        });


    }

    private static boolean checkInternetConnection() {
        Boolean result = false;
        HttpURLConnection con = null;
        try {
            // HttpURLConnection.setFollowRedirects(false);
            // HttpURLConnection.setInstanceFollowRedirects(false)
            con = (HttpURLConnection) new URL("http://www.google.com").openConnection();
            con.setRequestMethod("HEAD");
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public class gbthread extends Thread {
        String nam;

        gbthread(String nam) {
            this.nam = nam;

        }

        public void run() {

        }
    }

    // качаем файл с помощью Stream
    private static void downloadUsingStream(String urlStr, String file, int size) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}
