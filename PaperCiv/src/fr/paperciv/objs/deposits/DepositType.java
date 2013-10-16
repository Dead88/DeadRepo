package fr.paperciv.objs.deposits;

public class DepositType 
{
	private int Id;
	private String Name;
	private String File;
	private String Texture;
	
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	
	public String getName() {return Name;}
	public void setName(String name) {Name = name;}
	
	public String getFile() {return File;}
	public void setFile(String file) {File = file;}
	
	public String getTexture() {return Texture;}
	public void setTexture(String texture) {Texture = texture;}

	public DepositType() {}
	public DepositType(int id, String name, String file, String texture) 
	{
		this.Id = id;
		this.Name = name;
		this.File = file;
		this.Texture = texture;
	}
}
