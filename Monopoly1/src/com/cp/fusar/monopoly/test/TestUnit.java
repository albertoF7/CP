package com.cp.fusar.monopoly.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.cp.fusar.monopoly.Monopoly;
import com.cp.fusar.monopoly.Player;

public class TestUnit {

	@Test
	public void testMove1() {
		Player p = new Player("test");
		p.move(7);
		assertEquals(7, p.getPosition());
	}

	@Test
	public void testMove2() {
		Player p = new Player("test");
		p.move(39);
		p.move(6);
		assertEquals(5, p.getPosition());
	}
	
	@Test
	public void testPlayers1() {
		Monopoly m = new Monopoly();
		m.addPlayer(new Player("Horse"));
		m.addPlayer(new Player("Car"));
		assertEquals(true, m.checkPlayers());
	}
	
	@Test
	public void testPlayers2() {
		Monopoly m = new Monopoly();
		m.addPlayer(new Player("Horse"));
		assertEquals(false, m.checkPlayers());
	}
	
	@Test
	public void testPlayers3() {
		Monopoly m = new Monopoly();
		m.addPlayer(new Player("test1"));
		m.addPlayer(new Player("test2"));
		m.addPlayer(new Player("test3"));
		m.addPlayer(new Player("test4"));
		m.addPlayer(new Player("test5"));
		m.addPlayer(new Player("test6"));
		m.addPlayer(new Player("test7"));
		m.addPlayer(new Player("test8"));
		m.addPlayer(new Player("test9"));
		assertEquals(false, m.checkPlayers());
	}
	
	@Test
	public void testPlayers4() {
		Monopoly m = new Monopoly();
		Player h = new Player("Horse");
		Player c = new Player("Car");
		m.addPlayer(h);
		m.addPlayer(c);
		boolean cfirst = false;
		boolean hfirst = false;
		for(int y=0; y<1000; y++){
			List<Player> l = m.buildOrderOfPlayers();
			cfirst |= l.get(0).equals(c);
			hfirst |= l.get(0).equals(h);
			if(cfirst && hfirst){
				break;
			}
		}
		assertEquals(true, cfirst && hfirst);
	}
	
	@Test
	public void testGame1() {
		Monopoly m = new Monopoly();
		Player h = new Player("Horse");
		Player c = new Player("Car");
		m.addPlayer(h);
		m.addPlayer(c);
		m.play();
		assertEquals(20, m.getNumTurns());
		assertEquals(20, h.getNumMove());
		assertEquals(20, c.getNumMove());
	}
	
	@Test
	public void testGame2() {
		Monopoly m = new Monopoly();
		Player h = new Player("Horse");
		Player c = new Player("Car");
		m.addPlayer(h);
		m.addPlayer(c);
		m.play();
		assertEquals(false, h.isOrderChanged() || c.isOrderChanged());
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestUnit.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}
