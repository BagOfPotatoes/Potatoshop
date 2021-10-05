
/**
 * Potatoshop application
 *
 * @notJays
 * @version 3.00 2021/4/20
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Potatoshop {

    private static JPanel container = new JPanel();
    private static JFrame frame = new JFrame();
    private static JLabel label1 = new JLabel();
    private static JLabel label2 = new JLabel();
    private static BufferedImage image = null;
    private static int width, height, times;
    private static String location = "potate.png";
    private static JFileChooser fileChooser = new JFileChooser();
    private static File f, sound;

    public static void main(String[] args) {

        container = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenu options = new JMenu("Options");
        JMenuItem option1 = new JMenuItem("Save image");
        JMenuItem option2 = new JMenuItem("Open file");
        JMenuItem option3 = new JMenuItem("Invert colours");
        JMenuItem option4 = new JMenuItem("Greyscale");
        JMenuItem option5 = new JMenuItem("Horizontal inversion");
        JMenuItem option6 = new JMenuItem("Vertical inversion");
        JMenuItem option7 = new JMenuItem("Bulge");
        JMenuItem option8 = new JMenuItem("Gaussian blur");
        JMenuItem option9 = new JMenuItem("Sepia tone");
        JMenuItem option10 = new JMenuItem("Reset image");
        JMenuItem option11 = new JMenuItem("Shut down");
        label1 = new JLabel(" ", SwingConstants.CENTER);
        label2 = new JLabel();

        fileMenu.add(option1);
        fileMenu.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        options.add(option6);
        options.add(option7);
        options.add(option8);
        options.add(option9);
        options.add(option10);
        fileMenu.add(option11);
        menuBar.add(fileMenu);
        menuBar.add(options);
        container.add(label1);
        container.add(label2);
        option1.setActionCommand("option1");
        option1.addActionListener(new command());
        option2.setActionCommand("option2");
        option2.addActionListener(new command());
        option3.setActionCommand("option3");
        option3.addActionListener(new command());
        option4.setActionCommand("option4");
        option4.addActionListener(new command());
        option5.setActionCommand("option5");
        option5.addActionListener(new command());
        option6.setActionCommand("option6");
        option6.addActionListener(new command());
        option7.setActionCommand("option7");
        option7.addActionListener(new command());
        option8.setActionCommand("option8");
        option8.addActionListener(new command());
        option9.setActionCommand("option9");
        option9.addActionListener(new command());
        option10.setActionCommand("option10");
        option10.addActionListener(new command());
        option11.setActionCommand("option11");
        option11.addActionListener(new command());

        frame.setVisible(true);
        frame.add(container);
        frame.setJMenuBar(menuBar);
        frame.setTitle("Photatoshop");
        frame.setSize(1200, 1000);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            f = new File("potate.png"); //default image
            image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
            ImageIcon imageIcon = new ImageIcon(image);
            label1.setIcon(imageIcon);
            container.setBackground(Color.white);
            width = image.getWidth();
            height = image.getHeight();
            times = 0;
        } catch (IOException ea) {
            System.out.println("Error: " + ea);
        }
    }

    private static class command implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String eventName = e.getActionCommand();
            switch (eventName) {
                case "option1":
                    try {
                        ImageIO.write(image, "png", new File("new_image.png")); //saving format
                        System.out.println("Image saved to .java folder");
                    } catch (IOException eb) {
                        System.out.println("Error: " + eb);
                    }
                    break;
                case "option2":
                    int returns = fileChooser.showOpenDialog(null);
                    if (returns == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        location = selectedFile.getAbsolutePath();
                        try {
                            image = ImageIO.read(new File(location)); //rememebering location of file
                            ImageIcon imageIcon = new ImageIcon(image);
                            label1.setIcon(imageIcon);
                            System.out.println("Image mounted and loaded");
                            width = image.getWidth();
                            height = image.getHeight();
                            times = 0;
                        } catch (IOException ec) {
                            System.out.println("Error: " + ec);
                        }
                    }
                    break;
            case "option3":
                invert(image);
                break;
            case "option4":
                greyscale(image);
                break;
            case "option5":
                inverseH(image);
                break;
            case "option6":
                inverseV(image);
                break;
            case "option7":
                bulge(image);
                break;
            case "option8":
                blur(image);
                break;
            case "option9":
                sepia(image);
                break;
            case "option10":
                times = 0;
                try {
                    f = new File(location); //image file path
                    image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
                    image = ImageIO.read(f);
                    ImageIcon imageIcon = new ImageIcon(image);
                    label1.setIcon(imageIcon);
                    container.setBackground(Color.WHITE);
                    width = image.getWidth();
                    height = image.getHeight();
                    System.out.println("Image has been reset");
                } catch (IOException ed) {
                    System.out.println("Error: " + ed);
                }
                break;
            case "option11":
                try {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ee) {
                        System.out.println("No sleep");
                    }
                    Runtime runtime = Runtime.getRuntime();
                    System.exit(0);
                } catch (IOException eb) {
                    System.out.println("Error: " + eb);
                }
                    break;
            default:
                System.out.println("Yes");
                break;
        }
    }

    public static void invert(BufferedImage image) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int argb = image.getRGB(i, j);
                Color col = new Color(argb, true);
                col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue());
                image.setRGB(i, j, col.getRGB());
                ImageIcon imageIcon = new ImageIcon(image);
                label1.setIcon(imageIcon);
                container.setBackground(col);
            }
        }
    }

    public static void greyscale(BufferedImage image) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int argb = image.getRGB(i, j);
                Color col = new Color(argb, true);
                col = new Color((col.getRed() + col.getGreen() + col.getBlue()) / 3, (col.getRed() + col.getGreen() + col.getBlue()) / 3, (col.getRed() + col.getGreen() + col.getBlue()) / 3);
                image.setRGB(i, j, col.getRGB());
                ImageIcon imageIcon = new ImageIcon(image);
                label1.setIcon(imageIcon);
                container.setBackground(col);
            }
        }
    }

    public static void inverseH(BufferedImage image) {

        BufferedImage inverse = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                inverse.setRGB((width - i - 1), j, image.getRGB(i, j));
                ImageIcon imageIcon = new ImageIcon(image);
                label1.setIcon(imageIcon);
            }
        }

        for (int x = 0; x < inverse.getWidth(); x++) {
            for (int y = 0; y < inverse.getHeight(); y++) {
                image.setRGB(x, y, inverse.getRGB(x, y));
                ImageIcon imageIcon = new ImageIcon(image);
                label1.setIcon(imageIcon);
            }
        }
    }

    public static void inverseV(BufferedImage image) {

        BufferedImage inverse = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                inverse.setRGB(i, (height - j - 1), image.getRGB(i, j));
                ImageIcon imageIcon = new ImageIcon(image);
                label1.setIcon(imageIcon);
            }
        }

        for (int x = 0; x < inverse.getWidth(); x++) {
            for (int y = 0; y < inverse.getHeight(); y++) {
                image.setRGB(x, y, inverse.getRGB(x, y));
                ImageIcon imageIcon = new ImageIcon(image);
                label1.setIcon(imageIcon);
            }
        }
    }

    public static void bulge(BufferedImage image) {

        BufferedImage bulge = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int cX = width / 2;
        int cY = height / 2;
        double radii = 450;
        double bulgeStrength = 0.5;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int dX = x - cX;
                int dY = y - cY;
                int sX = x;
                int sY = y;
                double distance2 = dX * dX + dY * dY;

                if (distance2 < radii * radii) {
                    double distance = Math.sqrt(distance2);
                    boolean condition = false;
                    condition = true;

                    if (condition) {
                        double r = distance / radii;
                        double a = Math.atan2(dY, dX);
                        double rn = Math.pow(r, bulgeStrength) * distance;
                        double newX = rn * Math.cos(a) + cX;
                        double newY = rn * Math.sin(a) + cY;  //formulas for positions
                        sY += (newY - y);
                        sX += (newX - x);
                    } else {
                        double whereX = dX / distance;
                        double whereY = dY / distance;
                        double alpha = distance / radii;
                        double distortion = distance * Math.pow(1 - alpha, 1.0 / bulgeStrength);
                        sY -= distortion * whereY;
                        sX -= distortion * whereX;
                    }
                }

                if (sX >= 0 & sX < width & sY >= 0 & sY < height) {
                    int col = image.getRGB(sX, sY);
                    bulge.setRGB(x, y, col);
                }
            }
        }
        image = bulge;
        ImageIcon imageIcon = new ImageIcon(image);
        label1.setIcon(imageIcon);
    }

    public static void blur(BufferedImage image) {

        times++;
        int size = times * 2;		//amount of times to blur
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];

        for (int i = 0; i < data.length; i++) {		//making the blur matrix
            data[i] = weight;
        }

        Kernel kernel = new Kernel(size, size, data);
        BufferedImage blurred = null;
        BufferedImageOp operation = new ConvolveOp(kernel);
        image = operation.filter(image, blurred);
        ImageIcon imageIcon = new ImageIcon(image);
        label1.setIcon(imageIcon);
    }

    public static void sepia(BufferedImage image) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int argb = image.getRGB(i, j);
                Color col = new Color(argb, true);
                int r = col.getRed();
                int g = col.getGreen();
                int b = col.getBlue();
                int grey = (r + g + b) / 3;
                r = g = b = grey;
                r = r + (19 * 2); //adjusted colour values to seem more real as green (560nm) is more sensitive to human eye
                g = g + 20;
                if (r > 255) { //incase colours are out of bounds due to the adjustment
                    r = 255;
                }
                if (r < 0) {
                    r = 0;
                }
                if (g > 255) {
                    g = 255;
                }
                if (g < 0) {
                    g = 0;
                }
                if (b > 255) {
                    b = 255;
                }
                if (b < 0) {
                    b = 0;
                }
                col = new Color(r, g, b);
                image.setRGB(i, j, col.getRGB());
                ImageIcon imageIcon = new ImageIcon(image);
                label1.setIcon(imageIcon);
                container.setBackground(col);
            }
        }
    }
}
