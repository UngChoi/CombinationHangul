package Test;

import java.util.ArrayList;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		
		
		String listString = "";

		for (String s : list)
		{
		    listString += s;
		}

		System.out.println(listString);
		System.out.println("@");
		//System.out.println(list.toString());
		//System.out.println(list.)
	}
	static String arrayList2String(ArrayList<String> list){
		String listString = "";
		for (String s : list){
		    listString += s;
		}
		return listString;
	}
}
