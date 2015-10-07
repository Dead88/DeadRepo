package fr.wretchedlife.ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.wretchedlife.Constants;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -5684147110040142375L;
	private Image backgroundImage;
	private PreviewPanel previewPanel;
	private MessagePanel messagePanel;
	
	public InfoPanel() {
		this.backgroundImage = Constants.getTexture( ".//img//inventoryBkg.jpg" ).getImage();
		this.setLayout( new GridLayout(1,2) );
		
		previewPanel = new PreviewPanel();
		messagePanel = new MessagePanel();
		
		this.add( previewPanel );
		this.add( messagePanel );
	}
	
	public class PreviewPanel extends JPanel {
		private static final long serialVersionUID = -2432938420368107744L;
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage( backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	public PreviewPanel getPreviewPanel() {return previewPanel;}
	public void setPreviewPanel(PreviewPanel previewPanel) {this.previewPanel = previewPanel;}

	public void log( String message ) {
		this.messagePanel.log( message );
	}
	
	public class MessagePanel extends JPanel {
		private static final long serialVersionUID = 6186981376029056855L;
		private JLabel messageLabel;
		private String messageText;
		private JScrollPane scroller;
		
		public MessagePanel() {
			super();
			messageLabel = new JLabel();
			messageLabel.setOpaque( true );
			messageLabel.setForeground( Constants.goldColor );
			messageLabel.setBackground( Color.BLACK );
			messageText = "";
			
			scroller = new JScrollPane( messageLabel, 
				      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroller.setPreferredSize( new Dimension( 300,240 ) );
			scroller.getViewport().add( messageLabel );
			scroller.getVerticalScrollBar().addAdjustmentListener( new AdjustmentListener() {
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
					e.getAdjustable().setValue( e.getAdjustable().getMaximum() );
				}
			});
			
			this.add( scroller );
		}
		
		private void log( String message ) {
			messageText += "<br />" +message;
			messageLabel.setText( "<html>" + messageText + "</html>" );
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage( backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
