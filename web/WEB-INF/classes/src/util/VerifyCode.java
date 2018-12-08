package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCode {

    private int width=60;
    private int height=20;
    private Random random=new Random();
    private Color color=new Color(255,255,255);
    private String text;

    public BufferedImage getImage()
    {
        BufferedImage image = createImage();
        Graphics g = image.getGraphics();
        StringBuilder stringBuilder = new StringBuilder();


        for (int i=0;i<4;i++)
        {
            String str = randomChar() + "";
            stringBuilder.append(str);
            g.setColor(randomColor());
            g.drawString(str, i * width / 4, height);
        }
        drawLine(g);
        text = stringBuilder.toString();
        return image;
    }

    public String getText()
    {
        return text;
    }

    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image,"JPEG",out);
    }

    private BufferedImage createImage()
    {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0,0,width,height);
        return image;
    }

    private char randomChar()
    {
        String numbers = "1234567890";
        int index=random.nextInt(numbers.length());
        return numbers.charAt(index);
    }

    private Color randomColor()
    {
        int red=random.nextInt(150);
        int green=random.nextInt(150);
        int blue=random.nextInt(150);
        return new Color(red,green,blue);
    }

    private void drawLine(Graphics g){
        for (int i = 0; i < 2; i++) {
            g.setColor(randomColor());
            g.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }
    }
}