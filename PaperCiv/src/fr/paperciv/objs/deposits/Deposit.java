package fr.paperciv.objs.deposits;

import fr.paperciv.objs.map.Mesh;
import fr.paperciv.objs.map.Vertex;

public class Deposit extends Mesh
{
	public int Quantity;
	public DepositType Type;
	
	public int getQuantity() {return Quantity;}
	public void setQuantity(int amount) {Quantity = amount;}
	
	public DepositType getType() {return Type;}
	public void setType(DepositType type) {Type = type;}
	
	public Deposit()
	{
		super("Deposit", 0, 0.00, 0, null);
	}
	public Deposit(int x, double y, int z, Vertex[] vertices, int quantity, DepositType type) 
	{
		super("Deposit", x, y, z, vertices);
		Quantity = quantity;
		Type = type;
	}
}
