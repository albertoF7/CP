package com.cp.fusar.monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monopoly {

	public static final int MAX_POSITION = 40;
	public static final int MAX_PLAYERS = 8;
	public static final int NUM_TURNS = 20;

	private Set<Player> players;
	private List<Player> playersOrder;
	
	int numTurns = 0;
	boolean debugEnabled = false;
	int order;

	public Monopoly() {
		this.players = new HashSet<>();
		playersOrder = new ArrayList<>();
	}
	
	public void setDebugEnabled(){
		this.debugEnabled = true;
	}

	public void addPlayer(Player player) {
		if (players.add(player)) {
			debug("Added " + player);
		} else {
			debug(player.toString() + " already exists.");
		}
	}

	public boolean checkPlayers() {
		debug("Players are: " + this.players);
		if (players.size() < 2 || players.size() > MAX_PLAYERS) {
			debug("Invalid number of players: " + players.size());
			return false;
		}
		return true;
	}

	public List<Player> buildOrderOfPlayers() {
		playersOrder.clear();
		playersOrder.addAll(players);
		Collections.shuffle(playersOrder);
		return playersOrder;
	}

	private void playTurn() {		
		order = 0;
		this.playersOrder.forEach(p -> {
			p.setOrder(order);
			int score = (int) (Math.random() * 11) + 2;
			int prev = p.getPosition();
			p.move(score);
			debug(String.format("%s: score %d: %d --> %d", p.toString(), score, prev, p.getPosition()));
			order++;
		});
	}

	public void play() {
		if (checkPlayers()) {
			debug("Order of players: " + buildOrderOfPlayers());
			while (numTurns < NUM_TURNS) {
				debug("***** Turn " + numTurns);
				playTurn();
				numTurns++;
			}
		}
	}
	
	public int getNumTurns(){
		return this.numTurns;
	}
	
	public void debug(String msg){
		if(this.debugEnabled){
			System.out.println(msg);
		}
	}

	public static void main(String[] args) {
		Monopoly m = new Monopoly();
		m.setDebugEnabled();
		if (args != null && args.length > 0) {
			for (String n : args) {
				m.addPlayer(new Player(n));
			}
		}
		m.play();
	}

}
