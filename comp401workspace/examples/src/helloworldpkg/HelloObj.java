package helloworldpkg;

/** Comment
 * Displays "Hello World!" to the standard output. 

 */
public class HelloObj {
      String output = "";
      static HelloObj helloObj; // Line 1 

      public HelloObj(){
            output = "Hello World!";
      } 

      public String printMessage(){
            return output;
      } 

      public static void main (String args[]) {
            helloObj = new HelloObj(); // Line 2
            System.out.println(helloObj.printMessage());
      }

}