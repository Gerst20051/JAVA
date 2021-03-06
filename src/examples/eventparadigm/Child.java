package eventparadigm;

public class Child {
	private DogAdapter listener = new DogAdapter() {
		public void dogSleeps(DogEvent e) {
			System.out.println(": " + name + " bounces on and frightens " + e.getSource().getName());
		}
	};
	
	private String name;
	
	public Child(String name) {
		this.name = name;
	}
	
	public DogAdapter getListener() {
		return listener;
	}
}