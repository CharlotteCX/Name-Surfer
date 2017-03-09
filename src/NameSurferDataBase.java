/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import java.util.*;
import java.io.*;
import acm.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	
	private HashMap <String, NameSurferEntry> nameDataBase= new HashMap <String, NameSurferEntry>();
	
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try{
			BufferedReader rd=new BufferedReader(new FileReader(filename));
			while(true){
				String line=rd.readLine();
				if (line==null) break;
				NameSurferEntry entry=new NameSurferEntry(line);
				nameDataBase.put(entry.getName(), entry);
				
			}
			rd.close();	
		}catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		char ch=name.charAt(0);
		ch=Character.toUpperCase(ch);
		name=name.toLowerCase();
		name=ch+name.substring(1);
		
		if (nameDataBase.containsKey(name)){
			return nameDataBase.get(name);
		}else{
			return null;
		}
	}
}

