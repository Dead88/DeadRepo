import java.util.ArrayList;


public class FlotteEnnemis 
{
	private int tailleInitiale;
	@SuppressWarnings("unused")
	private int taille;
	private ArrayList<Ennemi> flotte;
	private Carre body;
	private char Direction;
	
	private int epaisseurFlotte;
	private int hauteurFlotte;
	
	public FlotteEnnemis(int taille)
	{
		if(this.flotte != null)this.flotte.clear();
		if(this.body != null)this.body = null;
		
		int compteur = 1;
		int elevation = 0;
		ArrayList<Ennemi> f = new ArrayList<Ennemi>();
		Ennemi e = null; 
		
		Ennemi firstE = null;
		Ennemi lastE = null;
		
		while(compteur!=taille+1)
		{
			if(compteur==1)
			{
				e = new Ennemi(0,0);
				firstE = e;
				lastE = e;		
			}
			else if(compteur < 6)
			{
				e = new Ennemi((lastE.getEnnemiObject().getX() + lastE.getEnnemiObject().getW()) + 20, 
								lastE.getEnnemiObject().getY());
				lastE = e;
			}
			else if(compteur == 7 || compteur == 13)
			{
				elevation += 50;
				e = new Ennemi(0, firstE.getEnnemiObject().getY() + elevation);
				lastE = e;
			}
			else
			{
				e = new Ennemi((lastE.getEnnemiObject().getX() + lastE.getEnnemiObject().getW()) + 20, 
								firstE.getEnnemiObject().getY() + elevation);
				lastE = e;
			}
			
			f.add(e);
			e = null;
			compteur++;
		}
		
		this.epaisseurFlotte = ((taille/3 * 50) + ((taille/3) - 1) * 20);
		this.hauteurFlotte = (3*50)+40;
		
		this.body = new Carre(firstE.getEnnemiObject().getX(), firstE.getEnnemiObject().getY(), hauteurFlotte, epaisseurFlotte);
		this.setFlotte(f);
		this.setDirection('X');
		this.setTailleInitiale(taille);
	}

	public void GererFlotte()
	{	
		int pointFlotteAuBord = body.getX()+this.getEpaisseurFlotte()-50;
		
		if(this.getDirection()!='X')
		{
			if(this.getDirection()=='G' && body.getX()!=20)
			{
				body.setX(body.getX()-20);
			}
			else if (this.getDirection()=='D' && pointFlotteAuBord!=590)
			{
				body.setX(body.getX()+20);
			}
			else if(body.getX()==20)this.setDirection('D');
			else if(pointFlotteAuBord==590)this.setDirection('G');
		
			for(int i=0;i<this.getFlotte().size();i++)
			{
				Ennemi mechant = this.getFlotte().get(i);
			
				if(!mechant.isMort())mechant.setCompteurTir(mechant.getCompteurTir()+1);;
				
				if(mechant.getProjectileEnnemi()!=null)
				{
					Projectile p = mechant.getProjectileEnnemi();
					
					if(p.getProjectileObject().getY()!=685)
					{
						p.getProjectileObject().setY(p.getProjectileObject().getY()+p.getProjectileObject().getH());
					}
					else 
					{
						mechant.setProjectileEnnemi(null);
					}
				}
				if(mechant.getCompteurTir()==mechant.getDelaiTir() && this.getDirection()!='X')
				{
					mechant.setCompteurTir(0);
					mechant.setDelaiTir(CommonMethod.getNouveauDelai(30,80));
							
					if(mechant.getProjectileEnnemi()==null && !mechant.isMort())
					{
						Projectile p = new Projectile(mechant.getEnnemiObject().getX()+20, mechant.getEnnemiObject().getY()+10, true);
						mechant.setProjectileEnnemi(p);
					}
				}
				
				switch(this.getDirection())
				{
					case 'G':
					{
						mechant.getEnnemiObject().setX(mechant.getEnnemiObject().getX()-20);
					}break;
					case 'D':
					{
						mechant.getEnnemiObject().setX(mechant.getEnnemiObject().getX()+20);
					}break;
				}
			}
		}
	}
	
	public ArrayList<Ennemi> getFlotte() 
	{
		return flotte;
	}
	public void setFlotte(ArrayList<Ennemi> flotte) 
	{
		this.flotte = flotte;
	}
	
	public void setDirection(char Direction)
	{
		this.Direction=Direction;
	}
	public char getDirection()
	{
		return this.Direction;
	}
	
	public Carre getFlotteObject()
	{
		return this.body;
	}
	
	public int getTailleInitiale() 
	{
		return tailleInitiale;
	}
	public void setTailleInitiale(int tailleInitiale) 
	{
		this.tailleInitiale = tailleInitiale;
	}
	
	public int getEpaisseurFlotte() 
	{
		return epaisseurFlotte;
	}
	public void setEpaisseurFlotte(int epaisseurFlotte) 
	{
		this.epaisseurFlotte = epaisseurFlotte;
	}
	public int getHauteurFlotte() 
	{
		return hauteurFlotte;
	}
	public void setHauteurFlotte(int hauteurFlotte) 
	{
		this.hauteurFlotte = hauteurFlotte;
	}
	
	public int getTaille() 
	{
		int taille = 0;
		
		for(int i=0;i<this.flotte.size();i++)
		{
			if(!this.flotte.get(i).isMort())taille++;
		}
		
		return taille;
	}
}
