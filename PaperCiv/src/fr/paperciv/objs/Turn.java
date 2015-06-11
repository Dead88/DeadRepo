package fr.paperciv.objs;

public class Turn 
{
	public enum PhaseNames {
		Phase_Production,
		Phase_Ordres,
		Phase_Resolution_des_Ordres,
		Phase_Collecte_des_ressources;
	}
	
	public class Phase 
	{
		private String Name;
		private boolean canProduct;
		private boolean canGiveOrders;
		private boolean canCollectRessources;
	
		public String getName() {return Name;}
		public void setName(String name) {Name = name;}
		
		public boolean isCanProduct() {return canProduct;}
		public void setCanProduct(boolean canProduct) {this.canProduct = canProduct;}
		
		public boolean isCanGiveOrders() {return canGiveOrders;}
		public void setCanGiveOrders(boolean canGiveOrders) {this.canGiveOrders = canGiveOrders;}
		
		public boolean isCanCollectRessources() {return canCollectRessources;}
		public void setCanCollectRessources(boolean canCollectRessources) {this.canCollectRessources = canCollectRessources;}
		
		public Phase(){}
		public Phase(String name, boolean canProduct,
				boolean canGiveOrders, boolean canCollectRessources) {
			this.Name = name;
			this.canProduct = canProduct;
			this.canGiveOrders = canGiveOrders;
			this.canGiveOrders = canGiveOrders;
			this.canCollectRessources = canCollectRessources;
		}	
	}
	
	private int Number;
	private Phase Phase;
	
	public int getNumber() {return Number;}
	public void setNumber(int number) {Number = number;}
	
	public Phase getPhase() {return Phase;}
	public void setPhase(Phase phase) {Phase = phase;}
	
	public Turn(){}
	public Turn(int number, fr.paperciv.objs.Turn.Phase phase) {
		super();
		Number = number;
		Phase = phase;
	}
}
