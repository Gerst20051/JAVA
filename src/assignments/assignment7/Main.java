package assignment7;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Main {
	public static void main (String[] args) {
		Scene scene = new Scene();
		OEFrame SceneOE = ObjectEditor.edit(scene);
		SceneOE.hideMainPanel();
		SceneOE.setSize(650, 695);
		scene.reference(SceneOE);
		scene.init();
	}
}