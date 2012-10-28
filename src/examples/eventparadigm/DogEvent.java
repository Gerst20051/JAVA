package eventparadigm;

public class DogEvent {
	/**
	 * Almost always want to create a reference to the source.
	 * Points to the source that generated the event.
	 */
	private Dog source;

	public DogEvent(Dog source) {
		this.source = source;
	}
	
	public Dog getSource() {
		return source;
	}
}