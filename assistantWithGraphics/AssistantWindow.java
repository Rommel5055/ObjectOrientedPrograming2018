package assistantWithGraphics;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;


import java.awt.Color;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AssistantWindow {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssistantWindow window = new AssistantWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AssistantWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Random rand = new Random();
		User mySelf = new User();
		ReturnList retList = new ReturnList();
		
		/*Creates Lists of both calls and news for the assistant */
		retList = retList.CreateCalls(retList);
		retList = retList.CreateNews(retList);
		List<Object> calls = retList.listObjectCalls;
		List<String> news = retList.listStringNews;
		/*********************************************************/
		
		/*Declare lists which will be filled with missed calls and news, 
		 * when the use is "busy"*/
		List<Object> missedCalls = new ArrayList<Object>();
		List<Object> missedNews = new ArrayList<Object>();
		/**********************************************************/
		String userStatus = mySelf.status;
		String callsString = "";
		
		
		
		
		
		
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnCambiarDeEstado = new JButton("Cambiar de Estado");
		
		GridBagConstraints gbc_btnCambiarDeEstado = new GridBagConstraints();
		gbc_btnCambiarDeEstado.insets = new Insets(0, 0, 5, 5);
		gbc_btnCambiarDeEstado.gridx = 1;
		gbc_btnCambiarDeEstado.gridy = 1;
		frame.getContentPane().add(btnCambiarDeEstado, gbc_btnCambiarDeEstado);
		
		JButton btnProximaIteracion = new JButton("Proxima Iteracion");
		
		GridBagConstraints gbc_btnProximaIteracion = new GridBagConstraints();
		gbc_btnProximaIteracion.insets = new Insets(0, 0, 5, 0);
		gbc_btnProximaIteracion.gridx = 4;
		gbc_btnProximaIteracion.gridy = 1;
		frame.getContentPane().add(btnProximaIteracion, gbc_btnProximaIteracion);
		
		JLabel lblStatus = new JLabel("Status: ");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 2;
		gbc_lblStatus.gridy = 2;
		frame.getContentPane().add(lblStatus, gbc_lblStatus);
		
		JLabel lblCurrentStatus = new JLabel("");			/***************************/
		GridBagConstraints gbc_lblCurrentStatus = new GridBagConstraints();
		gbc_lblCurrentStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentStatus.gridx = 3;
		gbc_lblCurrentStatus.gridy = 2;
		frame.getContentPane().add(lblCurrentStatus, gbc_lblCurrentStatus);
		
		JLabel lblNoticias = new JLabel("Noticias");
		GridBagConstraints gbc_lblNoticias = new GridBagConstraints();
		gbc_lblNoticias.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoticias.gridx = 1;
		gbc_lblNoticias.gridy = 4;
		frame.getContentPane().add(lblNoticias, gbc_lblNoticias);
		
		JLabel lblLlamadas = new JLabel("Llamadas");
		GridBagConstraints gbc_lblLlamadas = new GridBagConstraints();
		gbc_lblLlamadas.insets = new Insets(0, 0, 5, 0);
		gbc_lblLlamadas.gridx = 4;
		gbc_lblLlamadas.gridy = 4;
		frame.getContentPane().add(lblLlamadas, gbc_lblLlamadas);
		
		JTextPane textPane = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 0, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 5;
		frame.getContentPane().add(textPane, gbc_textPane);
		
		JTextPane textPane_1 = new JTextPane();
		GridBagConstraints gbc_textPane_1 = new GridBagConstraints();
		gbc_textPane_1.fill = GridBagConstraints.BOTH;
		gbc_textPane_1.gridx = 4;
		gbc_textPane_1.gridy = 5;
		frame.getContentPane().add(textPane_1, gbc_textPane_1);
		
		
		
		
		
		
		btnCambiarDeEstado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mySelf.changeStatus();
				lblCurrentStatus.setText(userStatus);
			}
		});
		btnProximaIteracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**********************************************************/
				
				
			}
		});
		
		
	}
}