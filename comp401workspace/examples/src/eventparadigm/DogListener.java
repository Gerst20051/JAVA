package eventparadigm;

public interface DogListener {
	public void dogHungry(DogEvent e);
	public void dogThirsty(DogEvent e);
	public void dogLonely(DogEvent e);
	public void dogSleeps(DogEvent e);
	
	/**
	 * If it's a DogEvent the interface
	 * should be named DogListener.
	 * If it's a ActionEvent the interface
	 * should be named ActionListener.
	 */
}