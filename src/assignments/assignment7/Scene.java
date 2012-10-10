package assignment7;

import java.awt.Color;
import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class Scene {
	OEFrame OE;
	Canvas canvas = new Canvas();
	Scanner scanner = new Scanner("");
	Parser parser = new Parser(canvas, scanner);
	ATable table = new ATable();

	public Scene() {
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	public Parser getParser() {
		return parser;
	}
	
	public ATable getTable() {
		return table;
	}
	
	private void animateScene() {
		String[] animate = new String[5];
		animate[0] = "MoVe Dorothy 100 100";
		animate[1] = "saY DOROTHY \"hi5!\"";
		animate[2] = "MoVe WIZARD -50 -50";
		animate[3] = "say scarecrow \"I'm scary!\"";
		animate[4] = "move ScareCrow 10 10";
		for (int i = 0; i < animate.length; i++) {
			parser.setString(animate[i]);
			ThreadSupport.sleep(2000);
		}
	}
	
	private void animateDatabase() {
		table.put("line", new ALine(0, 0, 5, 10));
		table.put("line2", new ALine(1, 2, 50, 100));
		System.out.println(((ALine) table.get("line")).getWidth());
		System.out.println(((ALine) table.get("line2")).getHeight());
		System.out.println("Database has "+table.size()+" entries!");
		table.put("line", new ALine(25, 25, 25, 25));
		System.out.println(((ALine) table.get("line")).getX());
		System.out.println("Database has "+table.size()+" entries!");
		table.delete("line");
		System.out.println("Database has "+table.size()+" entries!");
		table.put("dorothy", new Avatar("Dorothy.jpg"));
		System.out.println("Database has "+table.size()+" entries!");
		((Avatar) table.get("dorothy")).setColor(Color.BLUE);
		System.out.println(((Avatar) table.get("dorothy")).getColor());
		System.out.println("Member line: "+table.member("line"));
		System.out.println("Member line2: "+table.member("line2"));
		System.out.println("IndexOf line: "+table.indexOf("line"));
		System.out.println("IndexOf line2: "+table.indexOf("line2"));
		System.out.println("IndexOf dorothy: "+table.indexOf("dorothy"));
		table.clear();
		System.out.println("Database has "+table.size()+" entries!");
		table.get("dorothy");
		System.out.println("IndexOf dorothy: "+table.indexOf("dorothy"));
		System.out.println("Member dorothy: "+table.member("dorothy"));
	}
	
	public void init() {
		canvas.reference(OE);
		scanner.reference(OE);
		//parser.reference(OE);
		canvas.init();
		//canvas.animate();
		animateScene();
		//animateDatabase();
	}
	
	public void reference(OEFrame editor) {
		OE = editor;
	}
}