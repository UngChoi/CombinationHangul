package Test;

import java.util.*;



public class ArrayHangul {
    private LinkedList<OneChar> sentence;
    private static StringBuilder input;
    
    static {
    	input = new StringBuilder();
    }
    public ArrayHangul() {
      super();
      this.sentence = new LinkedList<OneChar>();
   }
   public ArrayHangul(LinkedList<OneChar> sentence) {
      super();
      this.sentence = sentence;
   }
   private static final char[] CHOSUNG_LIST = {
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 
        'ㅅ', 'ㅆ', 'ㅇ' , 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };
     
    // 중성 리스트. 00 ~ 20
    private static final char[] JUNGSUNG_LIST = {
        'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 
        'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 
        'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 
        'ㅡ', 'ㅢ', 'ㅣ'
    };
     
    // 종성 리스트. 00 ~ 27 + 1(1개 없음)
    private static final char[] JONGSUNG_LIST = {
        ' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ',
        'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 
        'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 
        'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };
//    
    public static int compareChar(char input){
       for(int i =0; i<CHOSUNG_LIST.length;i++){
          if(CHOSUNG_LIST[i] == input)
             return 0;
       }
       for(int i =0; i<JUNGSUNG_LIST.length;i++){
          if(JUNGSUNG_LIST[i] == input)
             return 1;
       }
       for(int i =0; i<JONGSUNG_LIST.length;i++){
          if(JONGSUNG_LIST[i] == input)
             return 2;
       }
       return -1;
    }
    
    
   public LinkedList<OneChar> getSentence() {
      return sentence;
   }
   public void setSentence(LinkedList<OneChar> sentence) {
      this.sentence = sentence;
   }

   public static void main(String[] args) {

      
      while(true){
    	  Scanner keyboard = new Scanner(System.in);
    	  String line = keyboard.nextLine();
    	  String item = getSentenceByOne(line);
    	  System.out.println(item);
      }
      
   }
   
   public void setSentence(char[] lineArr){
      int pastInform = 0; //이전에 입력된게 어떤 종류인지 자음 모음 받침인지 확인하는 메소드 
      int nextInform=0;
      int thirdInform=0;
      OneChar temp = new OneChar(); //리스트에 넣을 임시 객체 
      for(int i=0;i<lineArr.length;i++){
         int inform = compareChar(lineArr[i]);
         if(i==0)
            pastInform = inform; //처음에는 처음 입력된 값과 같이 설정
         switch(inform){
         //입력된 문자가 어떤거인지 따라 분류
         case 0 : 
            //자음이 들어왔을 때 
            if(pastInform ==1 ){ //이전에 입력된게 모음일 경우 
               //temp.setChong(lineArr[i]);
               if(i<lineArr.length-2){
            	   System.out.println("@@!!!");
                  nextInform=compareChar(lineArr[i+1]);
                  if(nextInform==0){//자음 다음 들어온 문자가 또 자음일 경우
                     thirdInform=compareChar(lineArr[i+2]);
                     if(thirdInform==0){//세번 연속 자음이 들어올 경우
                        String doubleJong=String.valueOf(lineArr[i])+String.valueOf(lineArr[i+1]);
                        temp.setChong(makeDoubleJong(doubleJong).charAt(0));
                        temp.setChar();
                        OneChar aTemp = new OneChar(temp);
                        sentence.add(aTemp);
                        temp.clearChar();
                     }
                     else{//자음 자음 모음
                        temp.setChong(lineArr[i]);
                        temp.setChar();
                        OneChar aTemp = new OneChar(temp);
                        sentence.add(aTemp);
                        temp.clearChar();
                        System.out.println("------------");
                        temp.printInform();
                        System.out.println("------------");
                     }
                  }
                  else{//자음 다음 들어온 문자가 모음일 경우
                      temp.setChong(lineArr[i]);
                      temp.setChar();
                      sentence.add(new OneChar(temp));
                      temp.clearChar(); //고쳐도됨 바로 문자 넣는걸
                  }
               }
               //끝에 두개 남았을때는 아무것도 하지 않는다 
               else{
            	   System.out.println("aaaaaaaaz");
                   if(temp.getEachChar()[0] == ' '){
                	   System.out.println("aaa");
                	   temp.setChosung(lineArr[i]);
                   }
                   else{
                       temp.setChong(lineArr[i]);
                       temp.setChar();
                       sentence.add(new OneChar(temp));
                       temp.clearChar(); //고쳐도됨 바로 문자 넣는걸
                   }
               }
            }
            else{
               temp.setChosung(lineArr[i]);
            } //전에 입력된 글자가 모음이 아니면 자음 초성으로 값을 넣는다 
            break;
         case 1 :
            //모음이 들어왔을 때
//        	 System.out.println(temp.getEachChar()[0]+"@@@");
        	 System.out.println(temp.getEachChar()[0]+"/"+temp.getEachChar()[1]+"/"+lineArr[i]+"/"+i);
            if(temp.getEachChar()[0] == ' '){
                //지금 글자가 초성이 없을경우 
            	System.out.println("초성없다 ");
               OneChar rTemp = sentence.removeLast();
               temp.setChosung(rTemp.getChong());
               //맨 마지막 글자를 빼서 받침을 다음 글자의 초성에 넣는다 
               rTemp.setChar();
               sentence.add(rTemp);
               System.out.println(rTemp.getRealChar());
               //수정한 맨 마지막 글자를 다시 넣어준다 
            } //지금 글자가 초성이 없을경우 
            if(temp.getEachChar()[1] == ' ' && pastInform!=1){
               temp.setJung(lineArr[i]);
               System.out.println(lineArr[i]+"/"+i);
            }
            //모음이 비어있고 이전것이 모음이 아닐경우 모음을 글자에 넣어준다 
            break;
         case 2 :
            //받침이 들어왔을 때 좀더 잘 해결해야 한다
            temp.setChong(lineArr[i]);
            temp.setChar();
            sentence.add(new OneChar(temp));
            temp.clearChar();
            break;
         }
         pastInform = inform;
      }
      temp.printInform();
      temp.setChar();
      if(temp.getEachCharIndex()[0] !=-1 && temp.getEachCharIndex()[1] !=-1){
    	  if(temp.getEachCharIndex()[2] == -1){
    		  temp.setNoChong();
    	         temp.setChar();
    	  }
    	  sentence.add(temp);
      }
      Iterator<OneChar> iterator = sentence.iterator();
      StringBuilder returnItem = new StringBuilder();
      
      while(iterator.hasNext()){
    	  char tempChar = iterator.next().getRealChar();
    	  returnItem.append(tempChar);
      }
      
   }

 
   public static String getSentence(String item){
	   ArrayHangul ba = new ArrayHangul();
	   input.append(item);
	   System.out.println(input+"@@@");
	   item = input.toString();
	   item = item.replaceAll("\\s+","");
	   
	   
	   	char[] lineArr = item.toCharArray();
	      int pastInform = 0; //이전에 입력된게 어떤 종류인지 자음 모음 받침인지 확인하는 메소드 
	      int nextInform=0;
	      int thirdInform=0;
	      OneChar temp = new OneChar(); //리스트에 넣을 임시 객체 
	      for(int i=0;i<lineArr.length;i++){
	         int inform = compareChar(lineArr[i]);
	         if(i==0)
	            pastInform = inform; //처음에는 처음 입력된 값과 같이 설정
	         switch(inform){
	         //입력된 문자가 어떤거인지 따라 분류
	         case 0 : 
	            //자음이 들어왔을 때 
	            if(pastInform ==1 ){ //이전에 입력된게 모음일 경우 
	               //temp.setChong(lineArr[i]);
	               if(i<lineArr.length-2){
	            	   System.out.println("@@!!!");
	                  nextInform=compareChar(lineArr[i+1]);
	                  if(nextInform==0){//자음 다음 들어온 문자가 또 자음일 경우
	                     thirdInform=compareChar(lineArr[i+2]);
	                     if(thirdInform==0){//세번 연속 자음이 들어올 경우
	                        String doubleJong=String.valueOf(lineArr[i])+String.valueOf(lineArr[i+1]);
	                        char tempChar = ba.makeDoubleJong(doubleJong).charAt(0);
	                        temp.setChong(tempChar);
	                        temp.setChar();
	                        OneChar aTemp = new OneChar(temp);
	                        ba.getSentence().add(aTemp);
	                        temp.clearChar();
	                     }
	                     else{//자음 자음 모음
	                        temp.setChong(lineArr[i]);
	                        temp.setChar();
	                        OneChar aTemp = new OneChar(temp);
	                        ba.getSentence().add(aTemp);
	                        temp.clearChar();
	                        System.out.println("------------");
	                        temp.printInform();
	                        System.out.println("------------");
	                     }
	                  }
	                  else{//자음 다음 들어온 문자가 모음일 경우
	                      temp.setChong(lineArr[i]);
	                      temp.setChar();
	                      ba.getSentence().add(new OneChar(temp));
	                      temp.clearChar(); //고쳐도됨 바로 문자 넣는걸
	                  }
	               }
	               //끝에 두개 남았을때는 아무것도 하지 않는다 
	               else{
	            	   System.out.println("aaaaaaaaz");
	                   if(temp.getEachChar()[0] == ' '){
	                	   System.out.println("aaa");
	                	   temp.setChosung(lineArr[i]);
	                   }
	                   else{
	                       temp.setChong(lineArr[i]);
	                       temp.setChar();
	                       ba.getSentence().add(new OneChar(temp));
	                       temp.clearChar(); //고쳐도됨 바로 문자 넣는걸
	                   }
	               }
	            }
	            else{
	               temp.setChosung(lineArr[i]);
	            } //전에 입력된 글자가 모음이 아니면 자음 초성으로 값을 넣는다 
	            break;
	         case 1 :
	            //모음이 들어왔을 때
//	        	 System.out.println(temp.getEachChar()[0]+"@@@");
	        	 System.out.println(temp.getEachChar()[0]+"/"+temp.getEachChar()[1]+"/"+lineArr[i]+"/"+i);
	            if(temp.getEachChar()[0] == ' '){
	                //지금 글자가 초성이 없을경우 
	            	System.out.println("초성없다 ");
	               OneChar rTemp = ba.getSentence().removeLast();
	               temp.setChosung(rTemp.getChong());
	               //맨 마지막 글자를 빼서 받침을 다음 글자의 초성에 넣는다 
	               rTemp.setChar();
	               ba.getSentence().add(rTemp);
	               System.out.println(rTemp.getRealChar());
	               //수정한 맨 마지막 글자를 다시 넣어준다 
	            } //지금 글자가 초성이 없을경우 
	            if(temp.getEachChar()[1] == ' ' && pastInform!=1){
	               temp.setJung(lineArr[i]);
	               System.out.println(lineArr[i]+"/"+i);
	            }
	            //모음이 비어있고 이전것이 모음이 아닐경우 모음을 글자에 넣어준다 
	            break;
	         case 2 :
	            //받침이 들어왔을 때 좀더 잘 해결해야 한다
	            temp.setChong(lineArr[i]);
	            temp.setChar();
	            ba.getSentence().add(new OneChar(temp));
	            temp.clearChar();
	            break;
	         }
	         pastInform = inform;
	      }
	      temp.printInform();
	      temp.setChar();
	      if(temp.getEachCharIndex()[0] !=-1 && temp.getEachCharIndex()[1] !=-1){
	    	  if(temp.getEachCharIndex()[2] == -1){
	    		  temp.setNoChong();
	    	         temp.setChar();
	    	  }
	    	  ba.getSentence().add(temp);
	      }
	      Iterator<OneChar> iterator = ba.getSentence().iterator();
	      StringBuilder returnItem = new StringBuilder();
	      
	      while(iterator.hasNext()){
	    	  char tempChar = iterator.next().getRealChar();
	    	  returnItem.append(tempChar);
	      }
	      return returnItem.toString();
	   }
   //입력이 완료된 자음 모음을 문장으로 바꾸는 메소드
   
   public static String getSentenceByOne(String item){
	   
	   ArrayHangul ba = new ArrayHangul();
	   input.append(item);
//	   System.out.println(input+"@@@");
	   item = input.toString();
	   item = item.replaceAll("\\s+","");
	   
	   
	   	char[] lineArr = item.toCharArray();
	      int pastInform = 0; //이전에 입력된게 어떤 종류인지 자음 모음 받침인지 확인하는 메소드 
	      int nextInform=0;
	      int thirdInform=0;
	      OneChar temp = new OneChar(); //리스트에 넣을 임시 객체 
	      for(int i=0;i<lineArr.length;i++){
	         int inform = compareChar(lineArr[i]);
	         if(i==0)
	            pastInform = inform; //처음에는 처음 입력된 값과 같이 설정
	         switch(inform){
	         //입력된 문자가 어떤거인지 따라 분류
	         case 0 : 
	            //자음이 들어왔을 때 
	            if(pastInform ==1 ){ //이전에 입력된게 모음일 경우 
	               //temp.setChong(lineArr[i]);
	               if(i<lineArr.length-2){
	            	   System.out.println("@@!!!");
	                  nextInform=compareChar(lineArr[i+1]);
	                  if(nextInform==0){//자음 다음 들어온 문자가 또 자음일 경우
	                     thirdInform=compareChar(lineArr[i+2]);
	                     if(thirdInform==0){//세번 연속 자음이 들어올 경우
	                        String doubleJong=String.valueOf(lineArr[i])+String.valueOf(lineArr[i+1]);
	                        System.out.println(doubleJong+"@@@!@!@!@!2");
	                        temp.setChong(ba.makeDoubleJong(doubleJong).charAt(0));
	                        temp.setChar();
	                        OneChar aTemp = new OneChar(temp);
	                        ba.getSentence().add(aTemp);
	                        temp.clearChar();
	                     }
	                     else{//자음 자음 모음
	                        temp.setChong(lineArr[i]);
	                        temp.setChar();
	                        OneChar aTemp = new OneChar(temp);
	                        ba.getSentence().add(aTemp);
	                        temp.clearChar();
	                        System.out.println("------------");
	                        temp.printInform();
	                        System.out.println("------------");
	                     }
	                  }
	                  else{//자음 다음 들어온 문자가 모음일 경우
	                      temp.setChong(lineArr[i]);
	                      temp.setChar();
	                      ba.getSentence().add(new OneChar(temp));
	                      temp.clearChar(); //고쳐도됨 바로 문자 넣는걸
	                  }
	               }
	               //끝에 두개 남았을때는 아무것도 하지 않는다 
	               else{
	            	   System.out.println("aaaaaaa?????az");
	                   if(temp.getEachChar()[0] == ' '){
	                	   System.out.println("aaa");
	                	   temp.setChosung(lineArr[i]);
	                	   System.out.println("여깁니다 ");
	                   }
	                   else{
	                	   
	                       temp.setChong(lineArr[i]);
	                       temp.setChar();
	                       ba.getSentence().add(new OneChar(temp));
	                       temp.clearChar(); //고쳐도됨 바로 문자 넣는걸
	                   }
	               }
	            }
	            else{
	            	if(i==0){
	            		temp.setChosung(lineArr[i]);
	            		System.out.println("첫초성 ");
	            	}
	            	else{
	            		OneChar rTemp = ba.getSentence().removeLast();
		            	String doubleJong=ba.makeDoubleJong(String.valueOf(rTemp.getChong())+String.valueOf(lineArr[i]));
		            	System.out.println(doubleJong+"@@@!@!@!@!");
		            	System.out.println(doubleJong.length());
		            	if(doubleJong.length()==1){
		            		
		            		rTemp.setChong(doubleJong.charAt(0));
		            		rTemp.setChar();
		            		ba.getSentence().add(rTemp);
		                    temp.clearChar();
		            	}
		            	else {
		            		rTemp.setChong(doubleJong.charAt(0));
		            		ba.getSentence().add(rTemp);
		                    temp.setChosung(doubleJong.charAt(1));
		            	}
	            	}
                    
//	               temp.setChosung(lineArr[i]);
	            } //전에 입력된 글자가 모음이 아니면 자음 초성으로 값을 넣는다
	            //이게아니라 여기서는 그냥 이중모음을 만든다.
	            break;
	         case 1 :
	            //모음이 들어왔을 때
//	        	 System.out.println(temp.getEachChar()[0]+"@@@");
	        	 System.out.println(temp.getEachChar()[0]+"/"+temp.getEachChar()[1]+"/"+lineArr[i]+"/"+i);
	            if(temp.getEachChar()[0] == ' '){
	                //지금 글자가 초성이 없을경우 
	            	System.out.println("초성없다 ");
	               OneChar rTemp = ba.getSentence().removeLast();
	               String tempChar = doubleJongMakeCho(rTemp.getChong());
	               if(tempChar.length()<=1){
//	            	   ba.getSentence().add(rTemp);
	            	   temp.setJung(lineArr[i]);
	            	   temp.setChosung(tempChar.charAt(0));
	               }
	               else{
	            	   rTemp.setChong(tempChar.charAt(0));
	            	   temp.setChosung(tempChar.charAt(1));
	               }
	               
	               //맨 마지막 글자를 빼서 받침을 다음 글자의 초성에 넣는다 
	               rTemp.setChar();
	               ba.getSentence().add(rTemp);
	               System.out.println(rTemp.getRealChar());
	               //수정한 맨 마지막 글자를 다시 넣어준다 
	               //여기서 이중모음이면 분해시켜서 다시 넣어주는 작업을 해야한다.
	            } //지금 글자가 초성이 없을경우 
	            if(temp.getEachChar()[1] == ' ' && pastInform!=1){
	               temp.setJung(lineArr[i]);
	               System.out.println(lineArr[i]+"/"+i);
	            }
	            //모음이 비어있고 이전것이 모음이 아닐경우 모음을 글자에 넣어준다 
	            break;
	         case 2 :
	            //받침이 들어왔을 때 좀더 잘 해결해야 한다
	            temp.setChong(lineArr[i]);
	            temp.setChar();
	            ba.getSentence().add(new OneChar(temp));
	            temp.clearChar();
	            break;
	         }
	         pastInform = inform;
	      }
	      temp.printInform();
	      temp.setChar();
	      if(temp.getEachCharIndex()[0] !=-1 && temp.getEachCharIndex()[1] !=-1){
	    	  if(temp.getEachCharIndex()[2] == -1){
	    		  temp.setNoChong();
	    	         temp.setChar();
	    	  }
	    	  ba.getSentence().add(temp);
	      }
	      Iterator<OneChar> iterator = ba.getSentence().iterator();
	      StringBuilder returnItem = new StringBuilder();
	      
	      while(iterator.hasNext()){
	    	  char tempChar = iterator.next().getRealChar();
	    	  returnItem.append(tempChar);
	      }
	      return returnItem.toString();
	   }
   //자음 모음을 하나씩 입력해서 조합하는 메소드
   
   public String makeDoubleJong(String doubleJong){
	      if(doubleJong.equals("ㄱㅅ")){
	         return "ㄳ";
	      }
	      else if(doubleJong.equals("ㄴㅈ")){
	         return "ㄵ";
	      }
	      else if(doubleJong.equals("ㄴㅎ")){
	         return"ㄶ";
	      }
	      else if(doubleJong.equals("ㄹㄱ")){
	         return "ㄺ";
	      }
	      else if(doubleJong.equals("ㄹㅁ")){
	         return "ㄻ";
	      }
	      else if(doubleJong.equals("ㄹㅂ")){
	         return "ㄼ";
	      }
	      else if(doubleJong.equals("ㄹㅅ")){
	         return "ㄽ";
	      }
	      else if(doubleJong.equals("ㄹㅌ")){
	         return "ㄾ";
	      }
	      else if(doubleJong.equals("ㄹㅍ")){
	         return "ㄿ";
	      }
	      else if(doubleJong.equals("ㄹㅎ")){
	         return "ㅀ";
	      }
	      else if(doubleJong.equals("ㅂㅅ")){
	         return "ㅄ";
	      }
	      else 
	    	  return doubleJong;
	   } //자음 두개를 이중받침으로 바꿔주는 메소드
   public static String doubleJongMakeCho(char doubleJong){
	      if(doubleJong=='ㄳ'){
	         return "ㄱㅅ";
	      }
	      else if(doubleJong=='ㄵ'){
	         return "ㄴㅈ";
	      }
	      else if(doubleJong=='ㄶ'){
	         return "ㄴㅎ";
	      }
	      else if(doubleJong=='ㄺ'){
	         return "ㄹㄱ";
	      }
	      else if(doubleJong=='ㄻ'){
	         return "ㄹㅁ";
	      }
	      else if(doubleJong=='ㄼ'){
	         return "ㄹㅂ";
	      }
	      else if(doubleJong=='ㄽ'){
	         return "ㄹㅅ";
	      }
	      else if(doubleJong=='ㄾ'){
	         return "ㄹㅌ";
	      }
	      else if(doubleJong=='ㄿ'){
	         return "ㄹㅍ";
	      }
	      else if(doubleJong=='ㅀ'){
	         return "ㄹㅎ";
	      }
	      else if(doubleJong=='ㅄ'){//(doubleJong.equals("ㅂㅅ"))
	         return "ㅂㅅ";
	      }
	      else 
	    	  return String.valueOf(doubleJong);
	   }
}
