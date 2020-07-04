
import java.io.*;


public class conv{  

public String myString = null;

    public static void main(String args[]){  
	
		System.out.println("Converting " + args[0] + " to " + args[1]);  
		//System.out.println(args[2]);  
		conv c = new conv();
		c.readAllLines(args[0]);
		c.writeAllLines(args[1]);
	 }  

public void writeAllLines(String fileName) {
	Writer out = null;
	System.out.println("Creating " + fileName);
	try {
		out = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(fileName), "UTF-8"));
		out.write(myString);
		out.close();
	}
	catch (IOException ex1){
		System.out.println(ex1.toString());  
	}
}

public void readAllLines( String fileName){
	String text = "";

	try{

	BufferedReader buf = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileName), "UTF8"));
	
	String line = buf.readLine();
	StringBuilder sb = new StringBuilder();
	
	while(line != null){ 
		text = changeToText(line);
		sb.append(text).append("\n"); 
		line = buf.readLine(); 
	} 
	
	myString = sb.toString();
	//System.out.println(myString);
	} catch (IOException ex) {}
}

public String changeToText(String line){
	String text = "";
	String unit = "";
	int cb = -1;
	char prev = 0;
	
	char[] stringToCharArray = (line+" ").toCharArray();
	for (char current : stringToCharArray) {
	//System.out.println("Prev: [" + prev + "]    Current: [" + current + "]");
	if (prev != 0){
		if (prev == '\\' )
			if ((current == 'u' || current == 'U'))
		{
			unit = "";
			cb = 0;
		}

		if (cb >= 0)
		{
			if((current >='0' && current <='9') || (current >='a' && current <='f') || (current >='A' && current <='F'))
			{
				unit+= current;
			}
			else
			{
				if (unit.length() > 0){
				int hexVal = Integer.parseInt(unit, 16);
				text += (char)hexVal;
				//System.out.println("Added (" + unit + ")=" + (char)hexVal);
		
				unit = ""; cb = -1;}
			}
		} else {
			//System.out.println("Added: [" + prev + "]");
			text+= (char)prev;
			
		}
	}
	prev = current;
	}
	return text;
}

}

