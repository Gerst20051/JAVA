package assignment8;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Main {
	public static void main (String[] args) {
		AScene scene = new AScene();
		OEFrame SceneOE = ObjectEditor.edit(scene);
		SceneOE.hideMainPanel();
		SceneOE.setSize(650, 695);
		scene.init();
	}
}