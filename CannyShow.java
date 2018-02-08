package canny;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CannyShow extends JApplet {

	private static final long serialVersionUID = 1L;

	public static EdgeDetector edgeDetector;
	public static JComboBox gaussKerBox;
	public static JPanel pan;
	public static JPanel pan4;
	public static JLabel outputLabel;
	public static JLabel outputImage;
	public static JTextField highthresh;
	public static JTextField lowthresh;
	public static JTextField sigma;
	public static JFrame frame1;
	public static String imageFile = "bird.jpg";
	public static JTextField inputImg;
	public static JButton loadImage;
	public static String image_url;
	public static URL theURL;
	public static Image image1;
	public static Toolkit tool;
	public static JLabel inputImage;
	public static JPanel pan3;
	public static JPanel pan2;
	public static JPanel pan2_1;
	public static JPanel pan3_1;
	public static JLabel inputLabel;
	public static JLabel kerlabel = new JLabel("Kernel");
	public static JLabel highlabel = new JLabel("Upper Threshold");
	public static JLabel lowlabel = new JLabel("Lower Threshold");
	public static JLabel sigmalabel = new JLabel("Standard Deviation");

	public static void kerSize() {
		int index = gaussKerBox.getSelectedIndex();
		if (index == 0) {
			edgeDetector.setGaussKernel(3);
		} else if (index == 1) {
			edgeDetector.setGaussKernel(4);
		} else if (index == 2) {
			edgeDetector.setGaussKernel(5);
		} else if (index == 3) {
			edgeDetector.setGaussKernel(6);
		} else if (index == 4) {
			edgeDetector.setGaussKernel(7);
		} else if (index == 5) {
			edgeDetector.setGaussKernel(8);
		} else if (index == 6) {
			edgeDetector.setGaussKernel(9);
		} else if (index == 7) {
			edgeDetector.setGaussKernel(10);
		} else if (index == 8) {
			edgeDetector.setGaussKernel(12);
		} else if (index == 9) {
			edgeDetector.setGaussKernel(15);
		} else if (index == 10) {
			edgeDetector.setGaussKernel(20);
		}
	}

	public static void loadImage() {
		pan3.remove(inputImage);
		pan4.remove(outputImage);

		imageFile = inputImg.getText();
		System.out.println("File Loading " + imageFile);
		image1 = tool.getImage(imageFile);
		image1 = image1.getScaledInstance(300, 300, Image.SCALE_DEFAULT);

		inputImage = new JLabel(new ImageIcon(image1));
		inputImage.setPreferredSize(new Dimension(300, 300));

		outputImage = new JLabel(new ImageIcon(image1));
		outputImage.setPreferredSize(new Dimension(300, 300));

		edgeDetector.setSourceImage(image1);

		pan3.add(inputImage);
		pan3.repaint();
		pan4.add(outputImage);
		pan4.repaint();
		pan.repaint();
		frame1.setContentPane(pan);
		frame1.pack();
		frame1.show();
	}

	public static void showOutputImage(Image img, JPanel pane) {
		pane.remove(outputImage);
		outputImage = new JLabel(new ImageIcon(img));
		outputImage.setPreferredSize(new Dimension(300, 300));
		pane.add(outputImage);
		pane.repaint();
		pan.repaint();
		frame1.setContentPane(pan);
		frame1.pack();
		frame1.show();
	}

	public static void startCanny() throws IOException {
		edgeDetector.setHighThreshold(Integer.valueOf(highthresh.getText())
				.intValue());
		edgeDetector.setLowThreshold(Integer.valueOf(lowthresh.getText())
				.intValue());
		edgeDetector.setSigma(Float.valueOf(sigma.getText()).floatValue());

		
		try {
			edgeDetector.process();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Image edgeImage = edgeDetector.getEdgeImage();

		showOutputImage(edgeImage, pan4);
	}

	public static void main(String[] args) {
		JButton startButton = new JButton("Start Process");
		startButton.setAlignmentY(CENTER_ALIGNMENT);
		startButton.setAlignmentX(CENTER_ALIGNMENT);

		edgeDetector = new EdgeDetector();

		
		tool = Toolkit.getDefaultToolkit();
		image1 = tool.getImage(imageFile);
		image1 = image1.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		edgeDetector.setSourceImage(image1);
		inputLabel = new JLabel("Input Image");

		inputImg = new JTextField("img/bird.jpg", 10);
		loadImage = new JButton("Upload Image");
		loadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadImage();
			}
		});

		
		frame1 = new JFrame("Canny Edge Detector");
		frame1.setPreferredSize(new Dimension(900, 500));
		frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);

		
		pan = new JPanel();
		pan.setPreferredSize(new Dimension(800, 500));
		pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
		pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pan2_1 = new JPanel();
		pan2_1.setLayout(new BoxLayout(pan2_1, BoxLayout.LINE_AXIS));
		pan2_1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pan2_1.setAlignmentY(CENTER_ALIGNMENT);
		pan2_1.setAlignmentX(CENTER_ALIGNMENT);
		pan3_1 = new JPanel();
		pan3_1.setLayout(new BoxLayout(pan3_1, BoxLayout.LINE_AXIS));
		pan3_1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pan3 = new JPanel();
		pan3.setPreferredSize(new Dimension(320, 500));
		pan3.setLayout(new BoxLayout(pan3, BoxLayout.PAGE_AXIS));
		pan3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pan4 = new JPanel();
		pan4.setPreferredSize(new Dimension(320, 500));
		pan4.setLayout(new BoxLayout(pan4, BoxLayout.PAGE_AXIS));
		pan4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		
		inputImage = new JLabel(new ImageIcon(image1));
		inputImage.setPreferredSize(new Dimension(300, 300));

		
		outputImage = new JLabel(new ImageIcon(image1));
		outputImage.setPreferredSize(new Dimension(300, 300));

		
		gaussKerBox = new JComboBox();
		gaussKerBox.addItem("3x3");
		gaussKerBox.addItem("4x4");
		gaussKerBox.addItem("5X5");
		gaussKerBox.addItem("6x6");
		gaussKerBox.addItem("7x7");
		gaussKerBox.addItem("8x8");
		gaussKerBox.addItem("9x9");
		gaussKerBox.addItem("10x10");
		gaussKerBox.addItem("12x12");
		gaussKerBox.addItem("15x15");
		gaussKerBox.addItem("20x20");

		gaussKerBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kerSize();
			}
		});

		
		highthresh = new JTextField("10", 5);

		
		lowthresh = new JTextField("1", 5);

		
		sigma = new JTextField("1.0", 5);

		pan2.add(loadImage);
		pan2.add(inputImg);
		pan2.add(kerlabel);
		pan2.add(gaussKerBox);
		pan2.add(highlabel);
		pan2.add(highthresh);
		pan2.add(lowlabel);
		pan2.add(lowthresh);
		pan2.add(sigmalabel);
		pan2.add(sigma);
		pan2_1.add(startButton);
		pan3.add(inputLabel);
		pan3.add(inputImage);

		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startCanny();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		outputLabel = new JLabel("Output Image");
		pan4.add(outputLabel);
		pan4.add(outputImage);
		pan.add(pan4);
		pan3_1.add(pan3);
		pan3_1.add(pan4);
		pan.add(pan2);
		pan.add(pan2_1);
		pan.add(pan3_1);
		frame1.setContentPane(pan);
		frame1.pack();
		frame1.show();
	}
}
