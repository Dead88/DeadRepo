package fr.wretchedlife.obj;

public class ItemProperty {
	
	public static enum Code {
		Vie,
		Faim,
		Soif,
		Force,
		Agilité,
		Savoir
	}
	
	private Code code;
	private String value;
	
	public ItemProperty(Code code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public Code getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
