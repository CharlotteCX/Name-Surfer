import acm.program.*;

public class textEntry extends ConsoleProgram {

	private NameSurferEntry entry; 
	
	public void run(){

   entry= new NameSurferEntry(test);
   println("get name: "+entry.getName());
   println("get rank:" +entry.getRank(2));
   println("to string " +entry.toString());
}

String test="Sam 58 69 99 131 168 236 278 380 467 408 466 997";
}
