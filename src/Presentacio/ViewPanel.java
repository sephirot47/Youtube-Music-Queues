package Presentacio;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class ViewPanel extends JPanel {

	public static ViewPanel viewPanel;
	public JPanel cardContainer;
	private GestioCanconsPanel gcp;
	private GestioUsuarisPanel gup;
	private GenerarLlistesPanel recp;
	private EstadistiquesPanel estp;
	private CreditsPanel credp;
	private NouUsuariPanel nup;
	private EditarUsuarisPanel edup;
	private EditarSolucioPanel esp;
	private NovaCancoPanel ncp;
	private EditarCanconsPanel ecp;
	private HistorialPanel hp;
	private ConsultarSolucioPanel csp;
	private ViewGraphPanel vgp;
	
	public ViewPanel() 
	{
		viewPanel = this;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		cardContainer = new JPanel();
		add(cardContainer);
		cardContainer.setLayout(new CardLayout(0, 0));
		
		JPanel frame = new JPanel();
		frame.setMaximumSize(new Dimension(32767, 20));
		add(frame);
		frame.setLayout(new BorderLayout(0, 0));
		
		frame.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(frame, BorderLayout.WEST);
		JButton btnEnrerepanel = new JButton("Enrere");
		btnEnrerepanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnrerepanel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goBack(true);
			}
		});
		btnEnrerepanel.setHorizontalAlignment(SwingConstants.LEFT);
		frame.add(btnEnrerepanel, BorderLayout.WEST);

		gcp = new GestioCanconsPanel();
		gup = new GestioUsuarisPanel();
		recp = new GenerarLlistesPanel();
		estp = new EstadistiquesPanel();
		credp = new CreditsPanel();
		nup = new NouUsuariPanel();
		edup= new EditarUsuarisPanel();
		esp = new EditarSolucioPanel();
		ncp = new NovaCancoPanel();
		ecp = new EditarCanconsPanel();
		hp = new HistorialPanel();
		csp = new ConsultarSolucioPanel();
		vgp = new ViewGraphPanel();
		
		cardContainer.add(gcp, GestioCanconsPanel.class.getSimpleName());
		cardContainer.add(gup, GestioUsuarisPanel.class.getSimpleName());
		cardContainer.add(recp, GenerarLlistesPanel.class.getSimpleName());
		cardContainer.add(estp, EstadistiquesPanel.class.getSimpleName());
		cardContainer.add(credp, CreditsPanel.class.getSimpleName());
		cardContainer.add(nup, NouUsuariPanel.class.getSimpleName());
		cardContainer.add(edup, EditarUsuarisPanel.class.getSimpleName());
		cardContainer.add(esp, EditarSolucioPanel.class.getSimpleName());
		cardContainer.add(ncp, NovaCancoPanel.class.getSimpleName());
		cardContainer.add(ecp, EditarCanconsPanel.class.getSimpleName());
		cardContainer.add(hp, HistorialPanel.class.getSimpleName());
		cardContainer.add(csp, ConsultarSolucioPanel.class.getSimpleName());
		cardContainer.add(vgp, ViewGraphPanel.class.getSimpleName());
	}
	
	public void refreshInsidePanels(String cardName)
	{
		if(cardName.equals(GestioCanconsPanel.class.getSimpleName())) gcp.refreshSongList();
		else if(cardName.equals(GestioUsuarisPanel.class.getSimpleName())) gup.refreshUserList();
		else if(cardName.equals(EditarCanconsPanel.class.getSimpleName())) ecp.refresh();
		else if(cardName.equals(HistorialPanel.class.getSimpleName())) hp.refresh();
		else if(cardName.equals(ConsultarSolucioPanel.class.getSimpleName())) csp.refresh();
		else if(cardName.equals(EditarSolucioPanel.class.getSimpleName())) esp.refresh();
		else if(cardName.equals(ViewGraphPanel.class.getSimpleName())) vgp.onEnterPanel();
		else if(cardName.equals(GenerarLlistesPanel.class.getSimpleName())) recp.refresh();
		else if(cardName.equals(EstadistiquesPanel.class.getSimpleName())) estp.refresh();
		else if(cardName.equals(CreditsPanel.class.getSimpleName())) credp.refresh();
		else if(cardName.equals(NouUsuariPanel.class.getSimpleName())) nup.refresh();
		else if(cardName.equals(EditarUsuarisPanel.class.getSimpleName())) edup.refresh();
		else if(cardName.equals(NovaCancoPanel.class.getSimpleName())) ncp.refresh();
	}
}
