package eventparadigm;

public class Person implements DogListener {
	private String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	public void dogHungry(DogEvent e) {
		System.out.println(": " + name + " feeds " + e.getSource().getName());
	}

	public void dogThirsty(DogEvent e) {
		System.out.println(": " + name + " lays down a bowl of water for " + e.getSource().getName());
	}

	public void dogLonely(DogEvent e) {
		System.out.println(": " + name + " plays with " + e.getSource().getName() + " for a while.");
	}

	public void dogSleeps(DogEvent e) {
		System.out.println(": " + name + " pets " + e.getSource().getName() + " on the head.");
		// throw new UnsupportedOperationException("Not supported yet.");
	}
}