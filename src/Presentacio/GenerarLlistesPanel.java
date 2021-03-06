package Presentacio;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Cursor;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.SliderUI;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import Domini.GirvanNewman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class GenerarLlistesPanel extends JPanel {

	private JLabel labelSliderDuracio = null, 
			labelSliderAny = null, 
			labelSliderEstil = null,
			labelSliderPublic = null,
			labelSliderProximitat = null,
			labelSliderAutor = null;

	private JPanel panelGirvanNewmanSlider;
	private final ButtonGroup algorismeGroup = new ButtonGroup();
	private JRadioButton Girvan, louvain, clique;
	private final JSlider sliderComunitatsGN;
	private JSlider sliderDuracio, sliderAny,sliderEstil, sliderPublic, sliderProximitat, sliderAutor;
	private JTextField textComunitatsGN;
	
	public static boolean done = false, aborted = false;
	
	public GenerarLlistesPanel()
	{
		super();
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(0, 0, 480, 510);
		panelLeft.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panelLeft);
		panelLeft.setLayout(null);
		
		JLabel lblCriteris = new JLabel("Criteris:");
		lblCriteris.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCriteris.setBounds(35, 11, 410, 14);
		panelLeft.add(lblCriteris);
		
		JPanel panelCriteris = new JPanel();
		panelCriteris.setBounds(35, 35, 435, 434);
		panelCriteris.setPreferredSize(new Dimension(410, 470));
		panelCriteris.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelLeft.add(panelCriteris);
		panelCriteris.setLayout(null);
		
		JPanel criterisSliderContainer = new JPanel();
		criterisSliderContainer.setBounds(15, 10, 408, 420);
		panelCriteris.add(criterisSliderContainer);
		criterisSliderContainer.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 70, 400, 4);
		criterisSliderContainer.add(separator_2);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(0, 135, 400, 4);
		criterisSliderContainer.add(separator_7);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 205, 400, 4);
		criterisSliderContainer.add(separator_6);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 275, 400, 4);
		criterisSliderContainer.add(separator_5);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 345, 400, 4);
		criterisSliderContainer.add(separator_3);
		
		JPanel panelDuracio = new JPanel();
		panelDuracio.setBounds(0, 0, 398, 65);
		criterisSliderContainer.add(panelDuracio);
		panelDuracio.setLayout(null);
		
		JLabel lblDuracio = new JLabel("Duracio:");
		lblDuracio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuracio.setBounds(5, 5, 70, 15);
		panelDuracio.add(lblDuracio);
		
		JLabel lblCanonsAmbRelacio = new JLabel("Cancons amb duracio semblant");
		lblCanonsAmbRelacio.setBounds(5, 22, 374, 15);
		panelDuracio.add(lblCanonsAmbRelacio);
		lblCanonsAmbRelacio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
    	sliderDuracio = new JSlider();
		sliderDuracio.setBounds(0, 40, 332, 16);
		panelDuracio.add(sliderDuracio);
		sliderDuracio.setMajorTickSpacing(1);
		sliderDuracio.setMinorTickSpacing(1);
		sliderDuracio.setMaximum(10);
		sliderDuracio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		labelSliderDuracio = new JLabel("10");
		labelSliderDuracio.setBounds(350, 40, 31, 15);
		panelDuracio.add(labelSliderDuracio);
		labelSliderDuracio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JPanel panelAny = new JPanel();
		panelAny.setBounds(0, 70, 398, 65);
		panelAny.setLayout(null);
		criterisSliderContainer.add(panelAny);
		
		JLabel lblAny = new JLabel("Any:");
		lblAny.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAny.setBounds(5, 5, 70, 15);
		panelAny.add(lblAny);
		
		JLabel lblCanonsAmbAnys = new JLabel("Cancons amb anys de creacio semblant");
		lblCanonsAmbAnys.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbAnys.setBounds(5, 22, 374, 15);
		panelAny.add(lblCanonsAmbAnys);
		
		 sliderAny = new JSlider();
		sliderAny.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderAny.setMinorTickSpacing(1);
		sliderAny.setMaximum(10);
		sliderAny.setMajorTickSpacing(1);
		sliderAny.setBounds(0, 40, 332, 16);
		panelAny.add(sliderAny);
		
		labelSliderAny = new JLabel("10");
		labelSliderAny.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderAny.setBounds(350, 40, 31, 15);
		panelAny.add(labelSliderAny);
		
		JPanel panelEstil = new JPanel();
		panelEstil.setBounds(0, 140, 398, 65);
		panelEstil.setLayout(null);
		criterisSliderContainer.add(panelEstil);
		
		JLabel lblEstil = new JLabel("Estil:");
		lblEstil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstil.setBounds(5, 5, 70, 15);
		panelEstil.add(lblEstil);
		
		JLabel lblCanonsAmbEstil = new JLabel("Cancons amb estil semblant");
		lblCanonsAmbEstil.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbEstil.setBounds(5, 22, 374, 15);
		panelEstil.add(lblCanonsAmbEstil);
		
		 sliderEstil = new JSlider();
		sliderEstil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderEstil.setMinorTickSpacing(1);
		sliderEstil.setMaximum(10);
		sliderEstil.setMajorTickSpacing(1);
		sliderEstil.setBounds(0, 40, 332, 16);
		panelEstil.add(sliderEstil);
		
		labelSliderEstil = new JLabel("10");
		labelSliderEstil.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderEstil.setBounds(350, 40, 31, 15);
		panelEstil.add(labelSliderEstil);
		
		JPanel panelPublic = new JPanel();
		panelPublic.setBounds(0, 210, 398, 65);
		panelPublic.setLayout(null);
		criterisSliderContainer.add(panelPublic);
		
		JLabel Public = new JLabel("Public:");
		Public.setFont(new Font("Tahoma", Font.BOLD, 11));
		Public.setBounds(5, 5, 70, 15);
		panelPublic.add(Public);
		
		JLabel lblCanonsAmbPublic = new JLabel("Cancons amb public d'edats semblants");
		lblCanonsAmbPublic.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbPublic.setBounds(5, 22, 374, 15);
		panelPublic.add(lblCanonsAmbPublic);
		
		 sliderPublic = new JSlider();
		sliderPublic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderPublic.setMinorTickSpacing(1);
		sliderPublic.setMaximum(10);
		sliderPublic.setMajorTickSpacing(1);
		sliderPublic.setBounds(0, 40, 332, 16);
		panelPublic.add(sliderPublic);
		
		labelSliderPublic = new JLabel("10");
		labelSliderPublic.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderPublic.setBounds(350, 40, 31, 15);
		panelPublic.add(labelSliderPublic);
		
		JPanel panelProximitat = new JPanel();
		panelProximitat.setBounds(0, 280, 398, 65);
		panelProximitat.setLayout(null);
		criterisSliderContainer.add(panelProximitat);
		
		JLabel lblProximitat = new JLabel("Proximitat:");
		lblProximitat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProximitat.setBounds(5, 5, 113, 15);
		panelProximitat.add(lblProximitat);
		
		JLabel lblCanonsEscoltadesNormalment = new JLabel("Cancons escoltades juntes en periodes curts de temps");
		lblCanonsEscoltadesNormalment.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsEscoltadesNormalment.setBounds(5, 22, 374, 15);
		panelProximitat.add(lblCanonsEscoltadesNormalment);
		
		 sliderProximitat = new JSlider();
		sliderProximitat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderProximitat.setMinorTickSpacing(1);
		sliderProximitat.setMaximum(10);
		sliderProximitat.setMajorTickSpacing(1);
		sliderProximitat.setBounds(0, 40, 332, 16);
		panelProximitat.add(sliderProximitat);
		
		labelSliderProximitat = new JLabel("10");
		labelSliderProximitat.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderProximitat.setBounds(350, 40, 31, 15);
		panelProximitat.add(labelSliderProximitat);
		
		JPanel panelAutor = new JPanel();
		panelAutor.setBounds(0, 350, 398, 65);
		panelAutor.setLayout(null);
		criterisSliderContainer.add(panelAutor);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAutor.setBounds(5, 5, 70, 15);
		panelAutor.add(lblAutor);
		
		JLabel lblCanonsAmbMateix = new JLabel("Cancons amb mateix autor");
		lblCanonsAmbMateix.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbMateix.setBounds(5, 22, 374, 15);
		panelAutor.add(lblCanonsAmbMateix);
		
		 sliderAutor = new JSlider();
		sliderAutor.setMinorTickSpacing(1);
		sliderAutor.setMaximum(10);
		sliderAutor.setMajorTickSpacing(1);
		sliderAutor.setBounds(0, 40, 332, 16);
		panelAutor.add(sliderAutor);
		
		labelSliderAutor = new JLabel("10");
		labelSliderAutor.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderAutor.setBounds(350, 40, 31, 15);
		panelAutor.add(labelSliderAutor);
		
		JButton btnTotsAZero = new JButton("Tots a zero");
		btnTotsAZero.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTotsAZero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				sliderAutor.setValue(0);
				sliderPublic.setValue(0);
				sliderAny.setValue(0);
				sliderDuracio.setValue(0);
				sliderEstil.setValue(0);
				sliderProximitat.setValue(0);
			}
		});
		btnTotsAZero.setBounds(146, 481, 171, 25);
		panelLeft.add(btnTotsAZero);
		
		JButton btnGenerar = new JButton("Generar llista");
		btnGenerar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				generateSolution();
			}
		});
		btnGenerar.setBounds(545, 443, 171, 25);
		add(btnGenerar);

		
		JPanel panelAlgorisme = new JPanel();
		panelAlgorisme.setBounds(500, 38, 250, 125);
		add(panelAlgorisme);
		panelAlgorisme.setPreferredSize(new Dimension(250, 410));
		panelAlgorisme.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelAlgorisme.setLayout(null);
		
		JLabel lblAlgorisme = new JLabel("Algorisme:");
		lblAlgorisme.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAlgorisme.setBounds(499, 13, 251, 14);
		add(lblAlgorisme);
		

		Girvan = new JRadioButton("Girvan Newman");
		Girvan.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if(Girvan.isSelected())
				{
					panelGirvanNewmanSlider.setVisible(true);
				}
				else
				{
					panelGirvanNewmanSlider.setVisible(false);
				}
			}
		});
		Girvan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Girvan.setSize(234, 20);
		Girvan.setLocation(6, 7);
		algorismeGroup.add(Girvan);
		panelAlgorisme.add(Girvan);
		
		clique = new JRadioButton("Clique Percolation");
		clique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clique.setLocation(6, 48);
		clique.setSize(234, 20);
		algorismeGroup.add(clique);
		panelAlgorisme.add(clique);
		
		louvain = new JRadioButton("Louvain");
		louvain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		louvain.setLocation(6, 90);
		louvain.setSize(234, 20);
		algorismeGroup.add(louvain);
		panelAlgorisme.add(louvain);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 37, 225, 2);
		panelAlgorisme.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 80, 225, 2);
		panelAlgorisme.add(separator_1);
		
		panelGirvanNewmanSlider = new JPanel();
		panelGirvanNewmanSlider.setLayout(null);
		panelGirvanNewmanSlider.setBounds(505, 175, 250, 137);
		add(panelGirvanNewmanSlider);
		
		JLabel lblNombreComunitatsGirvan = new JLabel("Llistes Girvan Newman:");
		lblNombreComunitatsGirvan.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNombreComunitatsGirvan.setBounds(12, 13, 186, 15);
		panelGirvanNewmanSlider.add(lblNombreComunitatsGirvan);
		
		sliderComunitatsGN = new JSlider();
		sliderComunitatsGN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderComunitatsGN.setMinorTickSpacing(1);
		sliderComunitatsGN.setMaximum(10);
		sliderComunitatsGN.setMajorTickSpacing(1);
		sliderComunitatsGN.setBounds(0, 40, 238, 16);
		panelGirvanNewmanSlider.add(sliderComunitatsGN);
		
		Girvan.setSelected(true);
		panelGirvanNewmanSlider.setVisible(true);
		
		textComunitatsGN = new JTextField();
		textComunitatsGN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				new Thread(new Runnable() { public void run() { try{Thread.sleep(100);}catch(Exception e){}
				int nComunitats = -1;
				try
				{
					nComunitats = Integer.parseInt(textComunitatsGN.getText());
				}
				catch(NumberFormatException exc){ return; }
				
				int numNodes = PresentationManager.getSongsNum();
				if(nComunitats < 1 || nComunitats > numNodes) return;
				sliderComunitatsGN.setValue(nComunitats);
				}}).start();
			}
		});
		textComunitatsGN.setBounds(191, 12, 47, 19);
		panelGirvanNewmanSlider.add(textComunitatsGN);
		textComunitatsGN.setColumns(10);
		
		JTextPane txtpnminimesLlistesDe = new JTextPane();
		txtpnminimesLlistesDe.setBackground(new Color(0,0,0,0));
		txtpnminimesLlistesDe.setEditable(false);
		txtpnminimesLlistesDe.setText("(Minimes llistes de reproduccio que tindra la reproduccio)");
		txtpnminimesLlistesDe.setBounds(12, 68, 226, 57);
		panelGirvanNewmanSlider.add(txtpnminimesLlistesDe);

		sliderComunitatsGN.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				textComunitatsGN.setText( String.valueOf(sliderComunitatsGN.getValue()) );
				textComunitatsGN.setCaretPosition(textComunitatsGN.getText().length());
			}
		});
		
		sliderComunitatsGN.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(textComunitatsGN != null)
					textComunitatsGN.setText( String.valueOf(sliderComunitatsGN.getValue()) );
			}
		});
		
		sliderDuracio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderDuracio != null)
					labelSliderDuracio.setText( String.valueOf(sliderDuracio.getValue()) );
			}
		});

		sliderAny.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderAny != null)
					labelSliderAny.setText( String.valueOf(sliderAny.getValue()) );
			}
		});

		sliderEstil.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderEstil != null)
					labelSliderEstil.setText( String.valueOf(sliderEstil.getValue()) );
			}
		});

		sliderPublic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderPublic != null)
					labelSliderPublic.setText( String.valueOf(sliderPublic.getValue()) );
			}
		});

		sliderProximitat.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderProximitat!= null)
					labelSliderProximitat.setText( String.valueOf(sliderProximitat.getValue()) );
			}
		});

		sliderAutor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderAutor!= null)
					labelSliderAutor.setText( String.valueOf(sliderAutor.getValue()) );
			}
		});
	}
	
	private void generateSolution()
	{ 
		if(sliderAny.getValue() <= 0 && sliderAutor.getValue() <= 0 && sliderDuracio.getValue() <= 0 &&
		   sliderEstil.getValue() <= 0 && sliderProximitat.getValue() <= 0 && sliderPublic.getValue() <= 0)
		{
			
			
			PresentationManager.errorWindow("Almenys algun criteri ha de ser no nul.");
			return;
		}
		
		final String msg = "Generant solucio";
        final DialogGenerantSolucio dialog = new DialogGenerantSolucio();
        
        SwingWorker worker = new SwingWorker() 
        {
            @Override protected void done() 
            {
            	done = aborted = false;
                dialog.dispose();
            }
            @Override protected void process(List chunks)  { }
            @Override protected Object doInBackground() throws Exception 
            {
            	int i = 0;
            	while(!done && !aborted) 
            	{
            		i = (i+1)%4;
            		String newmsg = msg;
            		for(int j = 0; j < i; ++j) newmsg += ".";
            		dialog.label.setText(newmsg);
            		try { Thread.sleep(500); } catch(Exception e) {}
            	}
                return null;
            }
        };

        MainWindow.frmYoutube.setEnabled(false);
    	done = false;
        worker.execute();
        
    	final Thread t = new Thread( new Runnable() { 
    		public void run() 
    		{ 
    			workoutSolution(); 
    			done = true;
    			MainWindow.frmYoutube.setEnabled(true);
    		} 
    	} );
    	t.start();
    	
    	new Thread( new Runnable() { 
    		public void run() 
    		{ 
    			while(true)
    			{
    				if(aborted) 
					{
    					t.interrupt();
            			MainWindow.frmYoutube.setEnabled(true);
            			break;
					}
            		try { Thread.sleep(100); } catch(Exception e) {}
    			}
    		} 
    	} ).start();
        
	}
	
	private void workoutSolution()
	{
		char algorisme = '-';
		if(louvain.isSelected()) algorisme = 'L';
		else if(clique.isSelected()) algorisme = 'C';
		else if(Girvan.isSelected()) algorisme = 'G';
		else 
		{
			PresentationManager.errorWindow("Selecciona un algorisme primer!");
			return;
		}
		
		int durationP = sliderDuracio.getValue();
		int yearP = sliderAny.getValue();
		int styleP = sliderEstil.getValue();
		int publicP = sliderPublic.getValue();
		int proximityP = sliderProximitat.getValue();
		int authorP = sliderAutor.getValue();
		
		PresentationManager.generateSolution(algorisme, durationP, yearP, styleP, publicP, proximityP, authorP, sliderComunitatsGN.getValue());
	}

	public void refresh() 
	{
		int numNodes = PresentationManager.getSongsNum();
		sliderComunitatsGN.setMinimum(1);
		sliderComunitatsGN.setMaximum(numNodes);
		sliderComunitatsGN.setValue((int)Math.sqrt(numNodes));
	}
}
