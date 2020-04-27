package com.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.ConnectException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.json.JSONException;

import com.rmi.SensorClientRMI;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame frmLoginSystem;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblUserNotify;

	static int status = 0;

	public LoginUI() {
		Image img1 = new ImageIcon(this.getClass().getResource("/04.png")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/07.png")).getImage();
		Image img3 = new ImageIcon(this.getClass().getResource("/05.png")).getImage();
		Image img4 = new ImageIcon(this.getClass().getResource("/06.png")).getImage();
		Image img5 = new ImageIcon(this.getClass().getResource("/02.png")).getImage();
		Image img6 = new ImageIcon(this.getClass().getResource("/03.png")).getImage();
		Image img7 = new ImageIcon(this.getClass().getResource("/01.png")).getImage();
		Image img8 = new ImageIcon(this.getClass().getResource("/08.png")).getImage();

		frmLoginSystem = new JFrame();
		frmLoginSystem.setResizable(false);
		frmLoginSystem.setIconImage(img7);
		frmLoginSystem.setTitle("User Login");
		frmLoginSystem.getContentPane().setBackground(SystemColor.textHighlight);
		frmLoginSystem.setBounds(200, 200, 690, 425);
		frmLoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginSystem.getContentPane().setLayout(null);
		frmLoginSystem.setLocationRelativeTo(null);

		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setForeground(SystemColor.text);
		lblUserLogin.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblUserLogin.setBounds(22, 37, 648, 67);
		lblUserLogin.setIcon(new ImageIcon(img5));
		frmLoginSystem.getContentPane().add(lblUserLogin);

		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(img3));
		btnLogin.setForeground(new Color(224, 255, 255));
		btnLogin.setBackground(new Color(210, 105, 30));
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnLogin.setFocusable(false);
		btnLogin.setBounds(285, 298, 144, 54);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = "admin";
				String password = "admin123";
				try {
					if (String.valueOf(txtPassword.getPassword()).isEmpty() | txtUsername.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill login details!", "Login Error!",
								JOptionPane.ERROR_MESSAGE);
						txtUsername.setText(null);
						txtPassword.setText(null);
					} else if (txtUsername.getText().equals(username)
							&& String.valueOf(txtPassword.getPassword()).equals(password)) {
						txtUsername.setText(null);
						txtPassword.setText(null);
						JOptionPane.showMessageDialog(null, "Logged in sucessfully!");
						frmLoginSystem.dispose();
						disposeFrame();
						ManageSensorUI manageSensorUI = new ManageSensorUI();
						manageSensorUI.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid login details. Please try again!", "Login Error!",
								JOptionPane.ERROR_MESSAGE);
						txtUsername.setText(null);
						txtPassword.setText(null);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frmLoginSystem.getContentPane().add(btnLogin);

		JButton btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon(img4));
		btnReset.setForeground(new Color(224, 255, 255));
		btnReset.setBackground(new Color(30, 144, 255));
		btnReset.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBounds(114, 310, 120, 35);
		btnReset.setFocusable(false);
		btnReset.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (!txtUsername.getText().isEmpty() || !txtPassword.getText().isEmpty()) {
					int action = JOptionPane.showConfirmDialog(null, "Do you really want to reset data?", "Reset Data",
							JOptionPane.YES_NO_OPTION);
					if (action == 0) {
						txtUsername.setText(null);
						txtPassword.setText(null);
					}
				}
			}
		});
		frmLoginSystem.getContentPane().add(btnReset);

		JButton btnExit = new JButton(" Exit");
		btnExit.setIcon(new ImageIcon(img2));
		btnExit.setForeground(new Color(224, 255, 255));
		btnExit.setBackground(new Color(139, 0, 0));
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setFocusable(false);
		btnExit.setBounds(474, 310, 120, 35);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you really want to exit.",
						"Login Exit Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		frmLoginSystem.getContentPane().add(btnExit);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(22, 105, 648, 2);
		frmLoginSystem.getContentPane().add(separator1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 11, 670, 403);
		panel.setLayout(null);
		frmLoginSystem.getContentPane().add(panel);

		txtUsername = new JTextField();
		txtUsername.setBounds(242, 130, 279, 35);
		txtUsername.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUsername.setColumns(10);
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String userName = txtUsername.getText();
				if (!userName.matches("[a-zA-Z0-9 ,]+")) {
					lblUserNotify.setText("Please enter a valid username.");
					txtUsername.setText(null);
				} else {
					lblUserNotify.setText(null);
				}
			}
		});
		panel.add(txtUsername);

		JLabel lblUsername = new JLabel("");
		lblUsername.setBounds(118, 115, 104, 67);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setIcon(new ImageIcon(img6));
		lblUsername.setForeground(SystemColor.menu);
		lblUsername.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("");
		lblPassword.setBounds(118, 193, 104, 54);
		lblPassword.setForeground(SystemColor.menu);
		lblPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setIcon(new ImageIcon(img1));
		panel.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(240, 201, 281, 32);
		txtPassword.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(txtPassword);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 265, 648, 2);
		panel.add(separator);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(img8));
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(0, 0, 670, 403);
		panel.add(lblNewLabel);

		lblUserNotify = new JLabel("");
		lblUserNotify.setForeground(SystemColor.text);
		lblUserNotify.setBounds(242, 166, 279, 16);
		panel.add(lblUserNotify);

		frmLoginSystem.setUndecorated(true);
	}

	public void displayFrame() {
		LoginUI window = new LoginUI();
		window.frmLoginSystem.setVisible(true);
	}

	public void disposeFrame() {
		super.dispose();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SensorClientRMI sensorClientRMI = new SensorClientRMI();
					SensorDetailsUI frame = new SensorDetailsUI();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
							dim.height / 2 - frame.getSize().height / 2);
					frame.setVisible(true);
					if (status == 1) {
						JOptionPane.showMessageDialog(null,
								"The CO2 level or smoke level is greater than 5 in a sensor!", "WARNING!",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (ConnectException e) {
					JOptionPane.showMessageDialog(null, "Connection failed! Connect to REST API and try again!",
							"WARNING!", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (JSONException e) {
					JOptionPane.showMessageDialog(null,
							"JSON object isuue! Check for corrupted data in the database and try again!", "WARNING!",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
