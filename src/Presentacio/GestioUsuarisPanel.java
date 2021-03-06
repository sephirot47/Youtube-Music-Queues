package Presentacio;

import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import Domini.Pair;
import Domini.UserManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class GestioUsuarisPanel extends JPanel
{
	final private JTextField txtBuscar;
	final private JLabel labelNomValue, labelEdatValue;
	final private JList listUsers, listReproductions;
	
	public GestioUsuarisPanel()
	{
		super();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) 
			{
				refreshUserList();
			}
		});
		setPreferredSize(new Dimension(800, 555));
		setLayout(null);
		
		JPanel panelUsers = new JPanel();
		panelUsers.setBounds(30, 29, 311, 497);
		add(panelUsers);
		panelUsers.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
				new Thread(new Runnable()
				{
					public void run() 
					{
						try{ Thread.sleep(100); } catch(Exception e){}
						refreshUserList();
					}
				}
                ).start();
			}
		});
		txtBuscar.setBounds(12, 36, 216, 19);
		panelUsers.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		listUsers = new JList();
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listUsers.setAutoscrolls(false);
		listUsers.setAlignmentY(0.0f);
		listUsers.setAlignmentX(0.0f);
		listUsers.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				String username = (String) listUsers.getSelectedValue();
				populateUserDetails(username == null ? "" : username);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 76, 284, 363);
		panelUsers.add(scrollPane_1);
		listUsers.setBounds(12, 67, 278, 369);
		scrollPane_1.setViewportView(listUsers);
		
		JButton btnNouUsuari = new JButton("Nou usuari");
		btnNouUsuari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNouUsuari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goToCard(NouUsuariPanel.class.getSimpleName());
			}
		});
		btnNouUsuari.setBounds(12, 448, 111, 25);
		panelUsers.add(btnNouUsuari);
		
		JButton btnImportarFitxer = new JButton("Importar fitxer");
		btnImportarFitxer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.importUsers();
				refreshUserList();
			}
		});
		btnImportarFitxer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportarFitxer.setBounds(135, 448, 161, 25);
		panelUsers.add(btnImportarFitxer);
		
		JLabel lblBuscar = new JLabel("Buscar usuari:");
		lblBuscar.setBounds(12, 12, 145, 20);
		panelUsers.add(lblBuscar);
		
		JPanel panelUserDetail = new JPanel();
		panelUserDetail.setBounds(408, 29, 364, 497);
		add(panelUserDetail);
		panelUserDetail.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(12, 33, 36, 20);
		panelUserDetail.add(lblNom);
		
		JLabel lblEdat = new JLabel("Edat:");
		lblEdat.setBounds(12, 61, 48, 20);
		panelUserDetail.add(lblEdat);
		
		JLabel lblReproduccions = new JLabel("Reproduccions:");
		lblReproduccions.setBounds(12, 93, 119, 20);
		panelUserDetail.add(lblReproduccions);
		
		labelEdatValue = new JLabel("-");
		labelEdatValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelEdatValue.setBounds(72, 61, 48, 20);
		panelUserDetail.add(labelEdatValue);
		
		labelNomValue = new JLabel("-");
		labelNomValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelNomValue.setBounds(72, 33, 198, 20);
		panelUserDetail.add(labelNomValue);
		
		JButton buttonEditarUsuari = new JButton("Editar usuari");
		buttonEditarUsuari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String username = (String) listUsers.getSelectedValue();
				if(listUsers.getSelectedIndex() != -1 && username != null)
				{
					PresentationManager.goToEditUsuarisPanel(username);
				}
			}
		});
		buttonEditarUsuari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonEditarUsuari.setBounds(12, 443, 161, 25);
		panelUserDetail.add(buttonEditarUsuari);
		
		JButton btnEliminarUsuari = new JButton("Eliminar usuari");
		btnEliminarUsuari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(listUsers.getSelectedIndex() != -1)
				{
					String user = (String) listUsers.getSelectedValue();
					if(PresentationManager.confirmWindow("Estas segur que vols esborrar l'usuari '" + user + "' ?"))
					{
						try {
							PresentationManager.removeUser(user);
						} catch (IOException e1) 
						{
							e1.printStackTrace();
						}			
						refreshUserList();
					}
				}
			}
		});
		btnEliminarUsuari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarUsuari.setBounds(191, 443, 161, 25);
		panelUserDetail.add(btnEliminarUsuari);
		
		JLabel lblDetallsUsuari = new JLabel("Detalls usuari:");
		lblDetallsUsuari.setBounds(122, 0, 114, 20);
		panelUserDetail.add(lblDetallsUsuari);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 127, 340, 304);
		panelUserDetail.add(scrollPane);
		
		listReproductions = new JList();
		listReproductions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listReproductions.setAutoscrolls(false);
		listReproductions.setAlignmentY(0.0f);
		listReproductions.setAlignmentX(0.0f);
		listReproductions.setBounds(12, 127, 340, 304);
		scrollPane.setViewportView(listReproductions);
		
		JButton btnEliminarTotsEls = new JButton("Eliminar tots els usuaris");
		btnEliminarTotsEls.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarTotsEls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(PresentationManager.confirmWindow("Estas segur que vols eliminar TOTS els usuaris ?"))
				{
					PresentationManager.removeAllUsers();
					refreshUserList();
					populateUserDetails("");
				}
			}
		});
		btnEliminarTotsEls.setBounds(12, 472, 340, 25);
		panelUserDetail.add(btnEliminarTotsEls);
		
		refreshUserList();
	}
	
	public void refreshUserList()
	{	
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();

		ArrayList<String> names = PresentationManager.getUsersNames();
		Collections.sort(names);
		
		for(String name : names)
		{
			String search = txtBuscar.getText();
			boolean addIt = search.equals("") || name.contains(search);
			if(addIt) dlm.addElement(name);
		}
		
		listUsers.setModel(dlm);

		if(listUsers.getModel().getSize() > 0)
			listUsers.setSelectedIndex(0);
	}
	
	public void populateUserDetails(String username)
	{
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();

		int userAge = PresentationManager.getUserAge(username);
		labelNomValue.setText(username.equals("") ? "-" : username);
		labelEdatValue.setText(userAge == 0 ? "-" : String.valueOf(userAge));
		
		ArrayList<Pair<String, Long>> repros = new ArrayList<Pair<String, Long>>();
		try 
		{
			repros = PresentationManager.getUserReproductions(username);
		} 
		catch (Exception e) 
		{
			PresentationManager.errorWindow("No es troba l'arxiu de reproduccions d'aquest usuari");
			e.printStackTrace();
		}
		
		if(repros != null)
		{
			for(Pair<String, Long> r : repros)
			{
				dlm.addElement(r.getFirst() + ", " + String.valueOf(r.getSecond()) );
			}
		}
		
		listReproductions.setModel(dlm);
	}
}
