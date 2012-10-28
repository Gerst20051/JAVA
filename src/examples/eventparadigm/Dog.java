package eventparadigm;

import java.util.Set;
import java.util.HashSet;

public class Dog {
	private Thread life = new Thread() {
		public void run() {
			while (true) {
				int roll = (int) (Math.random() * 4.0);
				switch (roll) {
					case 0: fireDogHungryEvent(); break;
					case 1: fireDogThirstyEvent(); break;
					case 2: fireDogLonelyEvent(); break;
					case 3: fireDogSleepsEvent();
				}
				try {
					sleep(5000);
				} catch (Exception e) {}
			}
		}
	};
	
	private Set<DogListener> listeners;
	private String name;
	
	public Dog(String name) {
		this.name = name;
		listeners = new HashSet<DogListener>();
		life.start();
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Synchronized incase using multiple threads
	 * and you don't want concurrent accesses to happen
	 * on this set.
	 * 
	 * Using a set because you don't have to check for duplicates.
	 * Set automatically replaces any duplicates.
	 * It doesn't make sense to have two of the same listeners.
	 */
	
	public synchronized void addDogListener(DogListener listener) {
		listeners.add(listener);
	}
	
	public synchronized void removeDogListener(DogListener listener) {
		listeners.remove(listener);
	}
	
	protected synchronized void fireDogHungryEvent() {
		System.out.println(name + " is hungry.");
		DogEvent e = new DogEvent(this);
		for (DogListener listener : listeners) {
			listener.dogHungry(e);
		}
	}
	
	protected synchronized void fireDogThirstyEvent() {
		System.out.println(name + " is thirsy.");
		DogEvent e = new DogEvent(this);
		for (DogListener listener : listeners) {
			listener.dogThirsty(e);
		}
	}
	
	protected synchronized void fireDogLonelyEvent() {
		System.out.println(name + " feels lonely.");
		DogEvent e = new DogEvent(this);
		for (DogListener listener : listeners) {
			listener.dogLonely(e);
		}
	}
	
	protected synchronized void fireDogSleepsEvent() {
		System.out.println(name + " fell asleep.");
		DogEvent e = new DogEvent(this);
		for (DogListener listener : listeners) {
			listener.dogSleeps(e);
		}
	}
}