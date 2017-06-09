import javax.swing.*;
import java.awt.*;

/**
 * Created by mikha on 08.06.2017.
 */
public class CircleBtn extends JButton{
    public CircleBtn(String text)
    {
        super(text);

        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        setContentAreaFilled(false);


    }
    @Override
    protected void paintBorder(Graphics g)
    {
        g.setColor(getForeground());

        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
    }
    @Override
    protected void paintComponent(Graphics g)
    {

        if(getModel().isArmed())
        {
            g.setColor(Color.GREEN);
            setForeground(Color.yellow);
        }
        else
        {
            g.setColor(getBackground());
            setForeground(Color.BLACK);
        }
        g.fillOval(0, 0, getSize().width-1, getSize().height-1);

        super.paintComponent(g);

    }
}
