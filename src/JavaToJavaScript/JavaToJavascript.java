package JavaToJavaScript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaToJavascript {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException 
	{
	    ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
	    ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");
	    
	    if(jsEngine == null) {
	            System.err.println("No script engine found for JavaScript");
	            System.exit(1);
	    }
	    
	    System.out.println("함수 정의");
	    defineScriptFunction(jsEngine);
	    
	    // Java에서 JavaScript 실행
//	    System.out.println("Calling invokeHellosSript...");
	    invokeHelloScript(jsEngine);
	    
	    
	    // Java에서 JavaScript 함수 정의
	    System.out.println("함수 정의");
	    defineScriptFunction(jsEngine);
	    
	    
	    // 이전 메소드에서 정의한 JavaScript 함수 호출
	    System.out.println("\nCalling invokeScriptFunctionFromEngine...");
	    invokeScriptFunctionFromEngine(jsEngine);
	    
	    
	    // Invocable Interface를 사용한 JavaScript 함수 호출
	    System.out.println("\nCalling invokeScriptFunctionFromJava...");
	    invokeScriptFunctionFromJava(jsEngine);
	    
	    
	    // JavaScript에서 Java함수 호출
	    System.out.println("\nCalling invokeJavaFromScriptFunction...");
	    invokeJavaFromScriptFunction(jsEngine);
	}
	
	
	/**
	* Java에서 JavaScript문법을 수행한다.
	* @param jsEngine
	* @throws ScriptException
	*/
	private static void invokeHelloScript (ScriptEngine jsEngine) throws ScriptException 
	{
	    jsEngine.eval("print('Hello from JavaScript')");
	    String indexJComb1 = "var indexJComb1 = ['ㄳ','ㄵ','ㄶ','ㄺ','ㄻ','ㄼ','ㄽ','ㄾ','ㄿ','ㅀ','ㅄ'];";
	    String indexJComb2 = "var indexJComb2 = ['ㄱㅅ','ㄴㅈ','ㄴㅎ','ㄹㄱ','ㄹㅁ','ㄹㅂ','ㄹㅅ','ㄹㅌ','ㄹㅍ','ㄹㅎ','ㅄ'];";
	    String indexMComb1 = "var indexMComb1 = ['ㅘ','ㅙ','ㅚ','ㅝ','ㅞ','ㅟ','ㅢ'];";
	    String indexMComb2 = "var indexMComb2 = ['ㅗㅏ','ㅗㅐ','ㅗㅣ','ㅜㅓ','ㅜㅔ','ㅜㅣ','ㅡㅣ'];";
	    String codJaMo = "var jaCode = 'ㄱ'.charCodeAt(0);\n var jaCodeLast = 'ㅎ'.charCodeAt(0);"+
    "var moCode = 'ㅏ'.charCodeAt(0); \n"+
    "var moCodeLast = 'ㅣ'.charCodeAt(0); \n";
	    String main = "";
	    jsEngine.eval("sayHello('Ung!')");
	    jsEngine.eval(indexJComb1);
	    jsEngine.eval(indexJComb2);
	    jsEngine.eval(indexMComb1);
	    jsEngine.eval(indexMComb2);
	    jsEngine.eval(codJaMo);
	}
	
	
	/**
	* Java에서 JavaScript함수를 정의한다.  
	* @param engine
	* @throws ScriptException
	*/
	private static void defineScriptFunction(ScriptEngine engine) throws ScriptException 
	{
	    // Define a function in the script engine
	    engine.eval(
	                    "function makeChar(i,m,t) {" +
	                    "    var code = ((i * 21) + m) * 28 + t + 0xAC00; "+
	                    "return String.fromCharCode(code);"+
	                    "}"
	    );
	    engine.eval(
                "function iChrIndex(chr) {" +
                "    var index = ((chr.charCodeAt(0) - 0xAC00) / 28) / 21; "+
                "return parseInt(index);"+
                "}"
	    		);
	    engine.eval(
                "function mChrIndex(chr) {" +
                "    var index = ((chr.charCodeAt(0)- 0xAC00) / 28) % 21; "+
                "return parseInt(index);"+
                "}"
	    		);
	    engine.eval(
                "function tChrIndex(chr) {" +
                "    var index = (chr.charCodeAt(0) - 0xAC00) % 28; "+
                "return parseInt(index);"+
                "}"
	    		);
        engine.eval(
                "function sayHello(name) {" +
                "    print('Hello, ' + name)" +
                "}"
);
	    
	}
	
	
	/**
	* Java에서 정의한 JavaScript함수를 호출한다.
	* @param engine
	* @throws ScriptException
	*/
	private static void invokeScriptFunctionFromEngine(ScriptEngine engine) throws ScriptException 
	{
	
	    engine.eval("makeChar(1,2,3)");
	    engine.eval("sayHello('Ung!')");
	}
	
	
	/**
	* Invocable 인터페이스를 사용하여 Script함수를 호출한다.
	* @param engine
	* @throws ScriptException
	* @throws NoSuchMethodException
	*/
	private static void invokeScriptFunctionFromJava(ScriptEngine engine) throws ScriptException, NoSuchMethodException 
	{
	    Invocable invocableEngine = (Invocable)engine;
	    invocableEngine.invokeFunction("makeChar", "from java");
	}
	
	
	/**
	* JavaScript에서 Java함수를 호출한다.
	* @param engine
	* @throws ScriptException
	*/
	private static void invokeJavaFromScriptFunction(ScriptEngine engine) throws ScriptException
	{
	    engine.put("helloScriptingWorld", new HelloScriptingWorld());
	    engine.eval(
	               "print('Invoking getHelloReply method from JavaScript...');" +
	               "var msg = helloScriptingWorld.getHelloReply('vJavaScript');" +
	               "print('java returned: ' + msg)" 
	    );
	}
	
	
	public String getHelloReply(String name)
	{
	    return "Java method getHelloReply says, 'Hello, " + name + "'";
	}
	
	
		
}
