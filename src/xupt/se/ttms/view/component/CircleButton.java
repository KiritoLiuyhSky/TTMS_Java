package xupt.se.ttms.view.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class CircleButton extends JButton {  
	  
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
  
    public static final Color BUTTON_COLOR1 = new Color(125, 161, 237);  
    public static final Color BUTTON_COLOR2 = new Color(91, 118, 173);  
    public static final Color BUTTON_BAK_COLOR1_1 = new Color(108, 135, 210,  
            179);  
    public static final Color BUTTON_BAK_COLOR1_2 = new Color(108, 135, 210,  
            255);  
    public static final Color BUTTON_BAK_COLOR2_1 = new Color(180, 230, 250,  
            179);  
    public static final Color BUTTON_BAK_COLOR2_2 = new Color(180, 230, 250,  
            255);  
    public static final Color BUTTON_FOREGROUND_COLOR = Color.BLACK;  
    private boolean hover;  
  
    public CircleButton(String text) {  
        super(text);  
            //步骤1  
        setBorderPainted(false);  
        setFocusPainted(false);  
        setContentAreaFilled(false);  
  
        setForeground(BUTTON_FOREGROUND_COLOR);  
        addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseEntered(MouseEvent e) {  
                hover = true;  
                repaint();  
            }  
  
            @Override  
            public void mouseExited(MouseEvent e) {  
                hover = false;  
                repaint();  
            }  
        });  
    }  
  
    @Override  
    protected void paintComponent(Graphics g) {  
        Graphics2D g2d = (Graphics2D) g.create();  
        int h = getHeight();  
        int w = getWidth();  
//      Color color2 = BUTTON_BAK_COLOR1_2;  
//      Color color1 = BUTTON_BAK_COLOR2_2;  
        float tran = 1F;  
        if (!hover) {  
            tran = 0.7F;  
//          color2 = BUTTON_BAK_COLOR1_1;  
//          color1 = BUTTON_BAK_COLOR2_1;  
        }  
            //步骤2  
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                RenderingHints.VALUE_ANTIALIAS_ON);  
        GradientPaint p1;  
        GradientPaint p2;  
        if (getModel().isPressed()) {  
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,  
                    new Color(100, 100, 100));  
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,  
                    new Color(255, 255, 255, 100));  
        } else {  
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,  
                    new Color(0, 0, 0));  
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,  
                    h - 3, new Color(0, 0, 0, 50));  
        }  
//设置透明度  
  
             g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  
                tran));  
        Shape clip = g2d.getClip();  
           //绘制整个按钮  
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,  
                h - 1, h, h);  
        g2d.clip(r2d);  
        GradientPaint gp = new GradientPaint(0.0F, 0.0F, BUTTON_COLOR1, 0.0F,  
                h, BUTTON_COLOR2, true);  
        g2d.setPaint(gp);  
        g2d.fillRect(0, 0, w, h);  
                //鼠标移入就绘制立体效果  
        if (hover) {  
            RoundRectangle2D.Float r2d2 = new RoundRectangle2D.Float(5, 2,  
                    w - 10, h / 2 - 1, h / 2, h / 2);  
            g2d.clip(r2d2);  
            GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, BUTTON_BAK_COLOR2_2, 0.0F,  
                    h / 2, BUTTON_BAK_COLOR1_2, true);  
            g2d.setPaint(gp2);  
            g2d.fillRect(5, 2, w - 10, h / 2);  
        }  
        g2d.setClip(clip);  
                //绘制边框  
        g2d.setPaint(p1);  
        g2d.drawRoundRect(0, 0, w - 1, h - 1, h, h);  
        g2d.setPaint(p2);  
        g2d.drawRoundRect(1, 1, w - 3, h - 3, h - 2, h - 2);  
        g2d.dispose();  
        super.paintComponent(g);  
    }  
}  