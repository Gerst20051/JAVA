package eventparadigm;

public class Application {
	public static void main(String[] args) {
		Person person1 = new Person("Max");
		Child child = new Child("Ron");
		Dog dog1 = new Dog("Husky");
		Dog dog2 = new Dog("Sam");
		dog1.addDogListener(person1);
		dog1.addDogListener(child.getListener());
		dog2.addDogListener(person1);
		dog2.addDogListener(child.getListener());
	}
}