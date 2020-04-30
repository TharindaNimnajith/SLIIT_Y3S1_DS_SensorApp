package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import com.rmi.SensorClientRMI;
import com.services.ISensorService;
import com.services.SensorService;

public class SensorDetailsUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static int status = 0;

	public SensorDetailsUI() throws IOException {
		setTitle("Sensor Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 483);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 73, 740, 343);
		panel_1.setLayout(null);
		getContentPane().add(panel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 723, 329);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_1.add(scrollPane);

		String col[] = { "Sensor ID", "Sensor Name", "Is Active", "Floor No", "Room No", "CO2 Level", "Smoke Level" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		JTable table = new JTable(tableModel);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setRowSelectionAllowed(false);
		table.setForeground(Color.blue);
		table.setRowHeight(24);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setEnabled(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBackground(new Color(240, 230, 140));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

		ArrayList<com.rmi.Sensor> sensorsList = new ArrayList<com.rmi.Sensor>();
		sensorsList = refreshTable();
		for (com.rmi.Sensor sensor1 : sensorsList) {
			String sensorId = sensor1.getSensorId();
			String sensorName = sensor1.getSensorName();
			int roomNo = sensor1.getRoomNo();
			int floorNo = sensor1.getFloorNo();
			int CO2Level = sensor1.getCO2Level();
			int smokeLevel = sensor1.getSmokeLevel();
			String isActive;
			if (sensor1.isActive()) {
				isActive = "Active";
			} else {
				isActive = "Not Active";
			}
			Object[] objs = { sensorId, sensorName, isActive, floorNo, roomNo, CO2Level, smokeLevel };
			tableModel.addRow(objs);
			if (CO2Level > 5 || smokeLevel > 5) {
				status = 1;
			}
		}

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionForeground"));
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 742, 68);
		panel.setLayout(null);
		panel_1.add(panel);

		JButton btnLogin = new JButton("Log in");
		btnLogin.setBounds(617, 17, 113, 33);
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(204, 204, 204));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(null,
							"Confirm if you really want to navigate to the login window.",
							"Login window navigation confirmation",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						LoginUI loginUI = new LoginUI();
						loginUI.displayFrame();
						disposeFrame();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnLogin);

		JLabel lblTopic = new JLabel("Sensor Details");
		lblTopic.setBounds(0, 11, 740, 57);
		lblTopic.setBackground(UIManager.getColor("menu"));
		lblTopic.setForeground(SystemColor.textHighlightText);
		lblTopic.setHorizontalAlignment(SwingConstants.CENTER);
		lblTopic.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		panel.add(lblTopic);
	}

	public ArrayList<com.rmi.Sensor> refreshTable() throws IOException {
		ISensorService iSensorService = (ISensorService) new SensorService();
		ArrayList<com.rmi.Sensor> sensorsList = new ArrayList<com.rmi.Sensor>();
		sensorsList = iSensorService.getSensorsList();
		return sensorsList;
	}

	public void disposeFrame() {
		super.dispose();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SensorClientRMI sensorClientRMI = new SensorClientRMI();
					sensorClientRMI.displayClientNo();
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
