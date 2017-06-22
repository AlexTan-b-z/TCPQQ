package util;

public class Protocol {
	
	public String backMsg(String receiveStr){
		Parser parser = new Parser();
		String returnStr = parser.factory(receiveStr);
		String res = returnStr + ":\n";
		return res;
	}
}
