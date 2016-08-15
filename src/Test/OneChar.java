package Test;

public class OneChar {
	private int[] eachCharIndex; // ㄱㅏㅁ의 유니코드 값을 각각 저장하는 배
	private char[] eachChar; // 예를 들어 감 이라는 글자는 ㄱㅏㅁ 이 조합되서 만들어진 글자다 배에 ㄱㅏㅁ 순서대로 넣는다  
	private char realChar; //감이라는 char를 실제로 저장 
	
    private static final int BASE_CODE = 44032;
    private static final int CHOSUNG = 588;
    private static final int JUNGSUNG = 28;
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
    
    
	public OneChar() {
		super();
		eachCharIndex = new int[3];
		eachCharIndex[0] = eachCharIndex[1] = eachCharIndex[2] = -1;
		eachChar = new char[3];
		eachChar[0] = eachChar[1] = eachChar[2] = ' '; 
		realChar = ' ';
	}

	public OneChar(int[] eachCharIndex, char[] eachChar, char realChar) {
		super();
		this.eachCharIndex = eachCharIndex;
		this.eachChar = eachChar;
		this.realChar = realChar;
	}
	
	public OneChar(OneChar src){
		this.eachCharIndex = new int[3];
		this.eachChar = new char[3];
		for(int i=0;i<3;i++){
			System.out.println("생성" +src.getEachCharIndex()[i]);
			this.eachChar[i] = src.getEachChar()[i];
			this.eachCharIndex[i] = src.getEachCharIndex()[i];
		}
		this.realChar = src.getRealChar();
	}
	
	private void setEachCharArr(){
		eachCharIndex[0] = this.compareCHO(eachChar[0]);
		eachCharIndex[1] = this.compareJUNG(eachChar[1]);
		eachCharIndex[2] = this.compareJONG(eachChar[2]);
		System.out.println(eachCharIndex[0]+"/"+eachCharIndex[1]+"/"+eachCharIndex[2]);
	} //글자에 각 char를 유니코드 값에 대응시켜 배열에 저장한다 
	
	public void setChar(){
		this.setEachCharArr();
		if(eachCharIndex[0] == -1 || eachCharIndex[1] == -1 || eachCharIndex[2] == -1){
			realChar = '*';
			return;
		}
		realChar = (char)((eachCharIndex[0]*CHOSUNG)+(eachCharIndex[1]*JUNGSUNG)+(eachCharIndex[2])+BASE_CODE);
		System.out.println("글자 : "+realChar);
	} //객체가 가지고 있는 모음 자음의 유니코드 값을 조합하여 글자를 만든다 
	
	public int[] getEachCharIndex() {
		return eachCharIndex;
	}

	public void setEachCharIndex(int[] eachCharIndex) {
		this.eachCharIndex = eachCharIndex;
	}

	public char getRealChar() {
		return realChar;
	}

	public void setRealChar(char realChar) {
		this.realChar = realChar;
	}
	
	public char[] getEachChar() {
		return eachChar;
	}

	public void setEachChar(char[] eachChar) {
		this.eachChar = eachChar;
	}

	public void setNoChong(){
		this.eachChar[2] = ' ';
		this.eachCharIndex[2] = 0;
	} //종성 (받침)이 없을 경우 유니코드 값 설정 
	
	public int getChongIndex(){
		return eachCharIndex[2];
	} //종성 (받침)이 있는지 없는지 확인하는 메소드 
	
	public void clearChar(){
		for(int i=0;i<eachCharIndex.length;i++){
			eachCharIndex[i]=-1;
		}
		for(int i=0;i<eachChar.length;i++){
			eachChar[i]=' ';
		}
		realChar = ' ';
	}//저장되어있는 값을 모두 지우는 메소드 

	
	public void setChosung(char index){
		eachChar[0] = index;
	}
	public void setJung(char index){
		eachChar[1] = index;
	}
	public void setChong(char index){
		System.out.println(index+"종성 ");
		eachChar[2] = index;
	}
	//각각 자음 모음 받침을 저장하는 메소드들 
	
	public char getChong(){
		char temp = eachChar[2];
		eachChar[2] = ' ';
		eachCharIndex[2] = 0;
		return temp;
	}//웁 글자 다음에 모음 (ㅏ) 가 있으면 웁ㅏ 가 아니라 우 바 로 인식해야 하기 때문에 글자의 받침을 제거한다  
	
    public int compareCHO(char input){
    	for(int i =0; i<CHOSUNG_LIST.length;i++){
    		if(CHOSUNG_LIST[i] == input)
    			return i;
    	}
    	return -1;
    }//자음인지 모음인지 종성인지 확인하는 메소드 그리고 char에 맞는 유니코드 값을 리턴 
    public int compareJUNG(char input){
    	for(int i =0; i<JUNGSUNG_LIST.length;i++){
    		if(JUNGSUNG_LIST[i] == input)
    			return i;
    	}
    	return -1;
    }//자음인지 모음인지 종성인지 확인하는 메소드 그리고 char에 맞는 유니코드 값을 리턴 
    public int compareJONG(char input){
    	for(int i =0; i<JONGSUNG_LIST.length;i++){
    		if(JONGSUNG_LIST[i] == input)
    			return i;
    	}
    	return -1;
    }//자음인지 모음인지 종성인지 확인하는 메소드 그리고 char에 맞는 유니코드 값을 리턴 
    
	public void printInform(){
		System.out.println("글자 : " + realChar);
		for(int i =0;i<3;i++){
			System.out.println("자소 : " +eachChar[i]+ "인덱스 : " + eachCharIndex[i]);
		} //그냥 객체의 멤버 변수가 잘 들어와있나 확인해보려고만든 메소드
	}
}
