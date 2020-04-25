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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.models.Sensor;
import com.services.ISensorService;
import com.services.SensorService;

public class ManageSensorUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtSensorId;
	private JTextField txtSensorName;
	private JTextField txtRoomNo;
	private JTextField txtFloorNo;

	private JPanel panel = new JPanel();
	private Sensor sensor = new Sensor();
	private ISensorService iSensorService = (ISensorService) new SensorService();
	private ArrayList<Sensor> sensorsList = new ArrayList<Sensor>();

	public ManageSensorUI()
			throws Exception {
		Image img1 = new ImageIcon(this.getClass().getResource("/10.png")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/11.png")).getImage();
		Image img3 = new ImageIcon(this.getClass().getResource("/07.png")).getImage();
		Image img4 = new ImageIcon(this.getClass().getResource("/06.png")).getImage();
		Image img5 = new ImageIcon(this.getClass().getResource("/09.png")).getImage();
		Image img6 = new ImageIcon(this.getClass().getResource("/13.png")).getImage();

		setResizable(false);
		setTitle("Manage Sensor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1028, 632);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel3 = new JPanel();
		panel3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel3.setBackground(new Color(30, 144, 255));
		panel3.setForeground(SystemColor.text);
		panel3.setBounds(0, 0, 1022, 83);
		panel3.setLayout(null);
		contentPane.add(panel3);

		JButton btnLogout = new JButton("Log Out");
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnLogout.setBounds(885, 25, 113, 35);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogout.setBackground(new Color(204, 204, 204));
		btnLogout.setForeground(new Color(0, 0, 0));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					SensorDetailsUI sensorDetailsUI = new SensorDetailsUI();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					sensorDetailsUI.setLocation(dim.width / 2 - sensorDetailsUI.getSize().width / 2,
							dim.height / 2 - sensorDetailsUI.getSize().height / 2);
					sensorDetailsUI.setVisible(true);
					disposeFrame();
					if (SensorDetailsUI.status == 1) {
						JOptionPane.showMessageDialog(null,
								"The CO2 level or smoke level is greater than 5 in a sensor!", "WARNING!",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		panel3.add(btnLogout);

		JLabel lblTitle = new JLabel("Manage Sensors");
		lblTitle.setIcon(new ImageIcon(img5));
		lblTitle.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblTitle.setForeground(SystemColor.text);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 11, 998, 65);
		panel3.add(lblTitle);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(0, 0, 51));
		panel1.setBounds(0, 81, 1022, 10);
		contentPane.add(panel1);

		JPanel panelManage = new JPanel();
		panelManage.setBounds(0, 0, 1022, 603);
		panelManage.setLayout(null);
		contentPane.add(panelManage);

		panel.setBounds(0, 88, 1022, 515);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(0, 51, 102));
		panel.setLayout(null);
		panelManage.add(panel);

		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(img6));
		btnAdd.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(210, 105, 30));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdd.setBounds(265, 437, 113, 47);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!txtSensorId.getText().isEmpty() && !txtSensorName.getText().isEmpty()
							&& !txtRoomNo.getText().isEmpty() && !txtFloorNo.getText().isEmpty()) {
						sensor.setSensorId(txtSensorId.getText());
						sensor.setSensorName(txtSensorName.getText());
						sensor.setFloorNo(Integer.parseInt(txtFloorNo.getText()));
						sensor.setRoomNo(Integer.parseInt(txtRoomNo.getText()));
						iSensorService.addSensor(sensor);
						JOptionPane.showMessageDialog(null, "Sensor added sucessfully.");
						resetFields();
					} else {
						JOptionPane.showMessageDialog(null, "Fill all the fields and try again!", "Insert Error!",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					System.out.println(e1);
				} finally {
					try {
						displayTable();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(img1));
		btnUpdate.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(210, 105, 30));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(454, 437, 113, 47);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sensor.setSensorId(txtSensorId.getText());
					sensor.setSensorName(txtSensorName.getText());
					sensor.setFloorNo(Integer.parseInt(txtFloorNo.getText()));
					sensor.setRoomNo(Integer.parseInt(txtRoomNo.getText()));
					iSensorService.updateSensor(sensor.getSensorId(), sensor);
					JOptionPane.showMessageDialog(null, "Sensor updated sucessfully.");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Please select any sensor to update.", "WARNING!",
							JOptionPane.ERROR_MESSAGE);
				} finally {
					resetFields();
					try {
						displayTable();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnUpdate);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRemove.setIcon(new ImageIcon(img2));
		btnRemove.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(210, 105, 30));
		btnRemove.setBounds(643, 437, 113, 47);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtSensorId.getText().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Please select any sensor to remove." + "\n", "WARNING!",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int action = JOptionPane.showConfirmDialog(null, "Do you really want to remove?",
								"Remove Sensor", JOptionPane.YES_NO_OPTION);
						if (action == 0) {
							sensor.setSensorId(txtSensorId.getText());
							iSensorService.removeSensor(sensor.getSensorId());
							JOptionPane.showMessageDialog(null, "Sensor deleted sucessfully.");
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING!", JOptionPane.ERROR_MESSAGE);
				} finally {
					resetFields();
					try {
						displayTable();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		panel.add(btnRemove);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setIcon(new ImageIcon(img3));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.setBounds(832, 437, 113, 47);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Exit Confirmation",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		panel.add(btnExit);

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(102, 204, 255));
		panel2.setBounds(38, 265, 947, 125);
		panel2.setLayout(null);
		panel.add(panel2);

		JLabel lblSensorID = new JLabel("Sensor ID");
		lblSensorID.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSensorID.setBounds(27, 21, 118, 20);
		panel2.add(lblSensorID);

		txtSensorId = new JTextField();
		txtSensorId.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtSensorId.setBounds(188, 21, 232, 20);
		txtSensorId.setBackground(new Color(224, 255, 255));
		txtSensorId.setEditable(true);
		txtSensorId.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtSensorId.setColumns(10);
		txtSensorId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String sensorId = txtSensorId.getText();
				if (!sensorId.matches("[a-zA-Z0-9 ,]+") && !sensorId.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid sensor id with only letters and digits.");
					txtSensorId.setText(null);
				}
			}
		});
		panel2.add(txtSensorId);

		JLabel lblSensorName = new JLabel("Sensor Name");
		lblSensorName.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSensorName.setBounds(27, 65, 149, 20);
		panel2.add(lblSensorName);

		txtSensorName = new JTextField();
		txtSensorName.setBounds(188, 65, 232, 20);
		txtSensorName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtSensorName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtSensorName.setBackground(new Color(224, 255, 255));
		txtSensorName.setColumns(10);
		txtSensorName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String sensorName = txtSensorName.getText();
				if (!sensorName.matches("[a-zA-Z0-9 ,]+") && !sensorName.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Please enter a valid sensor name with only letters and digits.");
					txtSensorName.setText(null);
				}
			}
		});
		panel2.add(txtSensorName);

		JLabel lblRoomNo = new JLabel("Room Number");
		lblRoomNo.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblRoomNo.setBounds(490, 21, 118, 20);
		panel2.add(lblRoomNo);

		JLabel lblFloorNo = new JLabel("Floor Number");
		lblFloorNo.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblFloorNo.setBounds(490, 66, 131, 20);
		panel2.add(lblFloorNo);

		txtRoomNo = new JTextField();
		txtRoomNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtRoomNo.setEditable(true);
		txtRoomNo.setColumns(10);
		txtRoomNo.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtRoomNo.setBackground(new Color(224, 255, 255));
		txtRoomNo.setBounds(654, 21, 232, 20);
		txtRoomNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String roomNo = txtRoomNo.getText();
				if (!roomNo.matches("[0-9 ,]+") && !roomNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid room number.");
					txtRoomNo.setText(null);
				}
			}
		});
		panel2.add(txtRoomNo);

		txtFloorNo = new JTextField();
		txtFloorNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtFloorNo.setEditable(true);
		txtFloorNo.setColumns(10);
		txtFloorNo.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtFloorNo.setBackground(new Color(224, 255, 255));
		txtFloorNo.setBounds(654, 65, 232, 20);
		txtFloorNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String floorNo = txtFloorNo.getText();
				if (!floorNo.matches("[0-9 ,]+") && !floorNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid floor number.");
					txtFloorNo.setText(null);
				}
			}
		});
		panel2.add(txtFloorNo);

		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReset.setBackground(SystemColor.controlText);
		btnReset.setForeground(SystemColor.text);
		btnReset.setBounds(76, 437, 113, 47);
		btnReset.setIcon(new ImageIcon(img4));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					resetFields();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		panel.add(btnReset);

		displayTable();
	}

	public ArrayList<Sensor> refreshTable() throws Exception {
		sensorsList = iSensorService.getSensorsList();
		return sensorsList;
	}

	public void disposeFrame() {
		super.dispose();
	}

	public void displayTable() throws Exception {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 25));
		scrollPane.setBounds(38, 34, 947, 195);
		panel.add(scrollPane);

		String col[] = { "Sensor ID", "Sensor Name", "Floor No", "Room No" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		JTable table = new JTable(tableModel);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setEnabled(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowVerticalLines(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBackground(new Color(240, 230, 140));
		table.setSurrendersFocusOnKeystroke(true);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		ArrayList<Sensor> sensorsList = new ArrayList<Sensor>();
		sensorsList = refreshTable();
		for (Sensor sensor1 : sensorsList) {
			String sensorId = sensor1.getSensorId();
			String sensorName = sensor1.getSensorName();
			int roomNo = sensor1.getRoomNo();
			int floorNo = sensor1.getFloorNo();
			Object[] objs = { sensorId, sensorName, floorNo, roomNo };
			tableModel.addRow(objs);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table.getSelectedRow();
					String sensorId = (table.getModel().getValueAt(row, 0)).toString();
					Sensor sensor1 = iSensorService.getSensor(sensorId);
					txtSensorId.setText(sensor1.getSensorId());
					txtSensorName.setText(sensor1.getSensorName());
					txtFloorNo.setText(Integer.toString(sensor1.getFloorNo()));
					txtRoomNo.setText(Integer.toString(sensor1.getRoomNo()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});

		scrollPane.setViewportView(table);
	}

	public void resetFields() {
		txtSensorId.setText(null);
		txtSensorName.setText(null);
		txtFloorNo.setText(null);
		txtRoomNo.setText(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageSensorUI frame = new ManageSensorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
