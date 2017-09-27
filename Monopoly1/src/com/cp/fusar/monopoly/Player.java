package com.cp.fusar.monopoly;

public class Player {
	
	private String name;
	private int position;
	private int numMove;
	private int order;
	private boolean orderChanged;
	
	public Player(String name) {
		this.name = name;
		this.position = 0;
		this.numMove = 0;
		
		this.order = -1;
		this.orderChanged = false;
	}

	public void move(int score){
		this.position = (this.position + score) % Monopoly.MAX_POSITION;
		numMove++;
	}
	
	public void setOrder(int order){
		int prev = this.order;
		this.order = order;
		orderChanged |= (prev != this.order && prev != -1);
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public int getNumMove(){
		return this.numMove;
	}
	
	public boolean isOrderChanged(){
		return this.orderChanged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}

}
