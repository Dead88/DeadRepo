package fr.paperciv.objs;

import fr.paperciv.objs.map.Vertex;

public class Order 
{
	public enum OrderType {
		Move,
		Attack
	}
	
	private int playerId;
	private int unitId;
	private OrderType Type;
	
	private Vertex position;
	private Vertex target;
	
	public Order(int playerId, int unitId, OrderType type, Vertex position,
			Vertex target) {
		super();
		this.playerId = playerId;
		this.unitId = unitId;
		Type = type;
		this.position = position;
		this.target = target;
	}
	
	public int getPlayerId() {return playerId;}
	public void setPlayerId(int playerId) {this.playerId = playerId;}
	
	public int getUnitId() {return unitId;}
	public void setUnitId(int unitId) {this.unitId = unitId;}
	
	public OrderType getType() {return Type;}
	public void setType(OrderType type) {Type = type;}
	
	public Vertex getPosition() {return position;}
	public void setPosition(Vertex position) {this.position = position;}
	
	public Vertex getTarget() {return target;}
	public void setTarget(Vertex target) {this.target = target;}
}
