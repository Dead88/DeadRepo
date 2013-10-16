import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Fenetre extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuPrincipal = new JMenuBar();
		private JMenu menuFichier = new JMenu("Fichier");
			private JMenuItem nouveauJeu = new JMenuItem("Nouveau Jeu");
			private JMenuItem quitter = new JMenuItem("Quitter");
		private JMenu menuAide = new JMenu("Aide");
			private JMenuItem controle = new JMenuItem("Contrôles");
			private JMenuItem apropos = new JMenuItem("A Propos");

	int score = 0;
	int level = 1;
	int tailleFlotte = 18;
	int delaiRenfort = 0;
	int compteurRenfort = 0;
	int nombreProjectile = 1;
	
	private JLabel LabelScore=new JLabel("Score : "+score+" | Niveau : "+level);
	
	public PlateauJeu plateau;
	public Vaisseau joueur = new Vaisseau(nombreProjectile);

	public FlotteEnnemis flotteEnnemis = null;
	public Ennemi premierMechant = null;
	public Soucoupe soucoupe = null;
	
	public Fenetre()
	{
		flotteEnnemis = new FlotteEnnemis(tailleFlotte);
		premierMechant = flotteEnnemis.getFlotte().get(0);
		
		this.setTitle("Space Invaders");
		this.setLocation(200,30);
		this.setSize(650,800);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(premierMechant.getTexture());
		
		initMenu();

		flotteEnnemis.setDirection('X');
		delaiRenfort = CommonMethod.getNouveauDelai(50, 100);
		
		joueur.setDirection('X');
		
		plateau = new PlateauJeu(joueur,flotteEnnemis,null);
		this.add(plateau,BorderLayout.CENTER);
		plateau.repaint();
		
		LabelScore.setBorder(BorderFactory.createBevelBorder(1));
		LabelScore.setFont(new Font("Arial",Font.BOLD,18));
		LabelScore.setForeground(Color.BLUE);
		this.add(LabelScore,BorderLayout.SOUTH);
		
		this.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			
			public void keyReleased(KeyEvent arg0) {}
			
			public void keyPressed(KeyEvent arg0)
			{
				if(flotteEnnemis.getDirection()=='X') flotteEnnemis.setDirection('D');
				
				if(arg0.getKeyCode()==39 && joueur.getDirection()=='X')
				{
					joueur.setDirection('D');
				}
				if(arg0.getKeyCode()==37 && joueur.getDirection()=='X')
				{
					joueur.setDirection('G');
				}
				if(arg0.getKeyCode()==32 && joueur.getProjectilesJoueur().size() < joueur.getNombreProjectile())
				{
					Projectile p = new Projectile(joueur.getJoueurObject().getX()+15, joueur.getJoueurObject().getY()+25,false);
					joueur.getProjectilesJoueur().add(p);
				}	
			}
		});
	}
	
	public void initMenu()
	{
		menuPrincipal.add(menuFichier);
		menuPrincipal.add(menuAide);
		
		menuFichier.add(nouveauJeu);
		menuFichier.add(quitter);
		
		menuAide.add(controle);
		menuAide.add(apropos);
		
		this.setJMenuBar(menuPrincipal); 
		
		nouveauJeu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				score=0;
				level=1;
				compteurRenfort = 0;
				delaiRenfort = CommonMethod.getNouveauDelai(50,100);
				
				joueur.Réinitialiser();
				
				flotteEnnemis = new FlotteEnnemis(tailleFlotte);
				soucoupe = null;
				plateau.setFlotteEnnemis(flotteEnnemis);
				plateau.setSoucoupe(null);
			}
		});
        quitter.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
        controle.addActionListener(new ActionListener() 
        {
        	@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) 
			{			
        		JOptionPane infos = new JOptionPane();
				String texteControle = "Flèche Gauche : Déplacement à gauche.\n";
				texteControle += "Flèche Droite : Déplacement à droite.\n";
				texteControle += "Espace : Tirer.";
				infos.showMessageDialog(null,texteControle,"Contrôles",JOptionPane.INFORMATION_MESSAGE);
			}
		});
       apropos.addActionListener(new ActionListener() 
       {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane infos = new JOptionPane();
				infos.showMessageDialog(null,"Space Invaders©\nRéaliser par: Dublineau Nicolas","Information",JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	public void Jouer(MainThread mT)
	{	
		try
		{	
			compteurRenfort++;
			LabelScore.setText("Score : "+score+" | Niveau : "+level);
			
			if(compteurRenfort == delaiRenfort)
			{
				compteurRenfort = 0;
				delaiRenfort = CommonMethod.getNouveauDelai(50,100);
				
				if(flotteEnnemis.getDirection()!='X' && flotteEnnemis.getTaille()!=flotteEnnemis.getFlotte().size())
				{
					RenforcerFlotteEnnemis();
				}
			}
			
			joueur.DeplacerJoueur();
			joueur.DeplacerProjectileJoueur();
			
			flotteEnnemis.GererFlotte();
			
			if(soucoupe != null)
			{
				soucoupe.DeplacerSoucoupe();
				soucoupe.DeplacerProjectileSoucoupe();
			}
			
			GestionCollision();
			
			plateau.repaint();
			
			GererEvennement(mT);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	
	@SuppressWarnings("static-access")
	public void GameOver()
	{
		JOptionPane infos = new JOptionPane();
	
		level=1;
		
		compteurRenfort = 0;
		delaiRenfort = CommonMethod.getNouveauDelai(50, 100);
		
		joueur.Réinitialiser();
		
		flotteEnnemis = new FlotteEnnemis(tailleFlotte);
		soucoupe = null;
		
		plateau.setFlotteEnnemis(flotteEnnemis);
		plateau.setSoucoupe(soucoupe);
		
		plateau.repaint();
		
		infos.showMessageDialog(null,"GAME OVER\n	Votre Score est : "+score,"Information",JOptionPane.INFORMATION_MESSAGE);
		score=0;
	}
	
	public void RenforcerFlotteEnnemis()
	{
		int tailleInitial = flotteEnnemis.getTailleInitiale();
		Ennemi nouveauMechant  = null;
		Ennemi currentE = null;	
		
		if(flotteEnnemis.getTaille()==0)
		{
			flotteEnnemis = new FlotteEnnemis(tailleInitial);
			flotteEnnemis.setDirection('D');
			
			if(soucoupe != null && soucoupe.isMort())
			{
				soucoupe = new Soucoupe(660, 160);
				plateau.setSoucoupe(soucoupe);
			}
			plateau.setFlotteEnnemis(flotteEnnemis);			
		}
		else
		{
			for(int i=0;i<flotteEnnemis.getFlotte().size();i++)
			{
				currentE = flotteEnnemis.getFlotte().get(i);
				
				if(currentE.isMort())
				{
					nouveauMechant = new Ennemi(currentE.getEnnemiObject().getX(),currentE.getEnnemiObject().getY());
					flotteEnnemis.getFlotte().remove(i);
					break;
				}
			}
			
			if(nouveauMechant!=null)
			{
				flotteEnnemis.getFlotte().add(nouveauMechant);
			}
		}
	}
	
	public void GestionCollision()
	{
		Carre j = joueur.getJoueurObject();
		
		if(soucoupe!=null && !soucoupe.isMort() && soucoupe.getProjectileSoucoupe()!=null)
		{
			Carre p = soucoupe.getProjectileSoucoupe().getProjectileObject();
			
			if( p.getX() > (j.getX() + j.getW()) 
			|| j.getX() > (p.getX() + p.getW())
			|| p.getY() > (j.getY() + j.getH())
			|| j.getY() > (p.getY() + p.getH()))
			{
				// pas collision
			}	
			else 
			{
				soucoupe.setProjectileSoucoupe(null);
				GameOver();
			}	
		}
		
		for(int h=0;h<flotteEnnemis.getFlotte().size();h++)
		{
			if (flotteEnnemis.getFlotte().get(h).getProjectileEnnemi()!=null)
			{
				Carre p = flotteEnnemis.getFlotte().get(h).getProjectileEnnemi().getProjectileObject();
				
				if( p.getX() > (j.getX() + j.getW()) 
				|| j.getX() > (p.getX() + p.getW())
				|| p.getY() > (j.getY() + j.getH())
				|| j.getY() > (p.getY() + p.getH()))
				{
					// pas collision
				}	
				else 
				{
					flotteEnnemis.getFlotte().get(h).setProjectileEnnemi(null);
					GameOver();
				}	
			}		
		}
		
		if(joueur.getProjectilesJoueur().size() > 0)
		{
			for(int h=0;h<joueur.getProjectilesJoueur().size();h++)
			{
				Carre p = joueur.getProjectilesJoueur().get(h).getProjectileObject();
				
				if(soucoupe!=null && !soucoupe.isMort())
				{
					Carre s = soucoupe.getSoucoupeObject();
					
					if(p.getX() > (s.getX() + s.getW()) 
					|| s.getX() > (p.getX() + p.getW())
					|| p.getY() > (s.getY() + s.getH())
					|| s.getY() > (p.getY() + p.getH()))
					{
						// pas collision
					}	
					else
					{
						int initialL = soucoupe.getPointVie();
						
						joueur.getProjectilesJoueur().remove(h);
						soucoupe.setPointVie(initialL-1);

						if(soucoupe.getPointVie()==0)
						{
							soucoupe.setMort(true);
							score+=3;	
						}	
					}
				}
				
				for(int i=0;i<flotteEnnemis.getFlotte().size();i++ )
				{
					Carre unMechant = flotteEnnemis.getFlotte().get(i).getEnnemiObject();
					
					if(!flotteEnnemis.getFlotte().get(i).isMort())
					{
						if( p.getX() > (unMechant.getX() + unMechant.getW()) 
						|| unMechant.getX() > (p.getX() + p.getW())
						|| p.getY() > (unMechant.getY() + unMechant.getH())
						|| unMechant.getY() > (p.getY() + p.getH()))
						{
							// pas collision
						}	
						else
						{
							joueur.getProjectilesJoueur().remove(h);
							flotteEnnemis.getFlotte().get(i).tuer(true);
							score+=1;
							break;
						}
					}
				}
			}	
		}
	}
	
	public void GererEvennement(MainThread mT)
	{
		if(score==5 || (level == 1 && score>5))
		{
			mT.setVitesse(100);
			level = 2;
		}
		else if(score==10 || (level == 2 && score>10))
		{
			mT.setVitesse(75);
			joueur.setNombreProjectile(2);
			
			if(soucoupe == null)
			{
				soucoupe = new Soucoupe(660, 160);
				plateau.setSoucoupe(soucoupe);
			}
			
			level = 3;
		}
		else if(score==15 || (level == 3 && score>15))
		{
			mT.setVitesse(60);
			level = 4;
		}
		else if(score==20 || (level == 4 && score>20))
		{
			mT.setVitesse(40);
			joueur.setNombreProjectile(3);
			
			if(soucoupe.isMort())
			{
				soucoupe = new Soucoupe(660, 160);
				plateau.setSoucoupe(soucoupe);
			}
			
			level = 5;
		}
		else if(score==0)
		{
			mT.setVitesse(130);
		}
	}
}
