package br.com.mariojp.app.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Paint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Canvas canvas;
	private Color color = Color.WHITE;
	private JButton blackButton, blueButton, greenButton, redButton, magentaButton, grayButton,
			orangeButton, yellowButton, pinkButton, cyanButton, lightGrayButton, rectangle, pencil;
	
	private JMenuItem clearButton, saveButton, loadButton, saveAsButton, colorPicker;
	private JFileChooser fileChooser;
	private File file;


	private Icon save = new ImageIcon(getClass().getResource("/images/save.png"));
	private Icon pencilIcon = new ImageIcon(getClass().getResource("/images/pencil.png"));
	private Icon rect = new ImageIcon(getClass().getResource("/images/rect.png"));
	private int saveCounter = 0;
	private JLabel filenameBar, thicknessStat;
	private JSlider thicknessSlider;
	private int width, height;
	
	ChangeListener thick = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			thicknessStat.setText(String.format("%s", thicknessSlider.getValue()));
			canvas.setThickness(thicknessSlider.getValue());
		}
	};
	
	ActionListener listener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == clearButton) {
				canvas.clear();
			} else if (event.getSource() == blackButton) {
				canvas.black();
			} else if (event.getSource() == blueButton) {
				canvas.blue();
			} else if (event.getSource() == greenButton) {
				canvas.green();
			} else if (event.getSource() == redButton) {
				canvas.red();
			} else if (event.getSource() == magentaButton) {
				canvas.magenta();
			} else if (event.getSource() == grayButton) {
				canvas.gray();
			} else if (event.getSource() == orangeButton) {
				canvas.orange();
			} else if (event.getSource() == yellowButton) {
				canvas.yellow();
			} else if (event.getSource() == pinkButton) {
				canvas.pink();
			} else if (event.getSource() == cyanButton) {
				canvas.cyan();
			} else if (event.getSource() == lightGrayButton) {
				canvas.lightGray();
			} else if (event.getSource() == rectangle) {
				canvas.rect();
			} else if (event.getSource() == pencil) {
				canvas.pencil();
			} else if (event.getSource() == saveButton) {
				if (saveCounter == 0) {
					fileChooser = new JFileChooser();
					if (fileChooser.showSaveDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
						file = fileChooser.getSelectedFile();
						saveCounter = 1;
						filenameBar.setText(file.toString());
						canvas.save(file);
					}
				} else {
					filenameBar.setText(file.toString());
					canvas.save(file);
				}
			} else if (event.getSource() == saveAsButton) {
				saveCounter = 1;
				fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					filenameBar.setText(file.toString());
					canvas.save(file);
				}
			} else if (event.getSource() == loadButton) {
				fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					filenameBar.setText(file.toString());
					canvas.load(file);
				}
			} else if (event.getSource() == colorPicker) {
				color = JColorChooser.showDialog(null, "Pick your color!", color);
				if (color == null)
					color = (Color.WHITE);
				canvas.picker(color);
			}
		}
	};

	public Paint(int width, int height) {
		super("Paint (" + width + "X" + height + ")");
		this.width = width;
		this.height = height;
		
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
		setLayout(new BorderLayout());
		canvas = new Canvas();

		add(canvas, BorderLayout.CENTER);

		JPanel top = new JPanel();
		


		
				
		JPanel panel1 = new JPanel();
		
		Box box = Box.createVerticalBox();
		Box box1 = Box.createHorizontalBox();

		pencil = new JButton(pencilIcon);
		pencil.setPreferredSize(new Dimension(40, 40));
		pencil.addActionListener(listener);
		rectangle = new JButton(rect);
		rectangle.setPreferredSize(new Dimension(40, 40));
		rectangle.addActionListener(listener);
		
		thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
		thicknessSlider.setMajorTickSpacing(25);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.setPreferredSize(new Dimension(40, 40));
		thicknessSlider.addChangeListener(thick);
		
		
		blackButton = new JButton();
		blackButton.setBackground(Color.BLACK);
		blackButton.setPreferredSize(new Dimension(40, 40));
		blackButton.addActionListener(listener);
		blueButton = new JButton();
		blueButton.setBackground(Color.BLUE);
		blueButton.setPreferredSize(new Dimension(40, 40));
		blueButton.addActionListener(listener);
		greenButton = new JButton();
		greenButton.setBackground(Color.GREEN);
		greenButton.setPreferredSize(new Dimension(40, 40));
		greenButton.addActionListener(listener);
		redButton = new JButton();
		redButton.setBackground(Color.RED);
		redButton.setPreferredSize(new Dimension(40, 40));
		redButton.addActionListener(listener);
		magentaButton = new JButton();
		magentaButton.setBackground(Color.MAGENTA);
		magentaButton.setPreferredSize(new Dimension(40, 40));
		magentaButton.addActionListener(listener);
		grayButton = new JButton();
		grayButton.setBackground(Color.GRAY);
		grayButton.setPreferredSize(new Dimension(40, 40));
		grayButton.addActionListener(listener);
		orangeButton = new JButton();
		orangeButton.setBackground(Color.ORANGE);
		orangeButton.setPreferredSize(new Dimension(40, 40));
		orangeButton.addActionListener(listener);
		yellowButton = new JButton();
		yellowButton.setBackground(Color.YELLOW);
		yellowButton.setPreferredSize(new Dimension(40, 40));
		yellowButton.addActionListener(listener);
		pinkButton = new JButton();
		pinkButton.setBackground(Color.PINK);
		pinkButton.setPreferredSize(new Dimension(40, 40));
		pinkButton.addActionListener(listener);
		cyanButton = new JButton();
		cyanButton.setBackground(Color.CYAN);
		cyanButton.setPreferredSize(new Dimension(40, 40));
		cyanButton.addActionListener(listener);
		lightGrayButton = new JButton();
		lightGrayButton.setBackground(Color.LIGHT_GRAY);
		lightGrayButton.setPreferredSize(new Dimension(40, 40));
		lightGrayButton.addActionListener(listener);
		
		
		saveButton = new JMenuItem("Save", save);
		saveButton.addActionListener(listener);
		saveAsButton = new JMenuItem("Save As...", save);
		saveAsButton.addActionListener(listener);
		loadButton = new JMenuItem("Load");
		loadButton.addActionListener(listener);
		colorPicker = new JMenuItem("Color Picker");
		colorPicker.addActionListener(listener);
		clearButton = new JMenuItem("Clear");
		clearButton.addActionListener(listener);

		filenameBar = new JLabel("No file");
		thicknessStat = new JLabel("1");

		box.add(Box.createVerticalStrut(40));
		box1.add(thicknessSlider, BorderLayout.NORTH);
		box1.add(thicknessStat, BorderLayout.NORTH);
		box.add(box1, BorderLayout.NORTH);
		
		box.add(Box.createVerticalStrut(5));
		box.add(pencil, BorderLayout.NORTH);
		box.add(Box.createVerticalStrut(5));
		box.add(rectangle, BorderLayout.NORTH);
		panel1.add(filenameBar, BorderLayout.SOUTH);

		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		menu.add(saveButton);
		menu.add(saveAsButton);
		menu.add(loadButton);
		menu.add(colorPicker);
		menu.add(clearButton);

		top.add(greenButton);
		top.add(blueButton);
		top.add(blackButton);
		top.add(redButton);
		top.add(magentaButton);
		top.add(grayButton);
		top.add(orangeButton);
		top.add(yellowButton);
		top.add(pinkButton);
		top.add(cyanButton);
		top.add(lightGrayButton);
		
		add(top, BorderLayout.NORTH);
		
		add(panel1, BorderLayout.SOUTH);
		add(box, BorderLayout.WEST);

		setVisible(true);

		setSize(width + 79, height + 11);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
