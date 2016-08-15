package JavaToJavaScript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class HelloScriptingWorld { 
        public static void main(String[] args) throws ScriptException, NoSuchMethodException 
        {
                ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
                ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");
                
                if(jsEngine == null) {
                        System.err.println("No script engine found for JavaScript");
                        System.exit(1);
                }
                
                // Java에서 JavaScript 실행
                System.out.println("Calling invokeHellosSript...");
                invokeHelloScript(jsEngine);
                
                
                // Java에서 JavaScript 함수 정의
                System.out.println("\nCalling defineScriptFunction...");
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
        	
                engine.eval("sayHello('world!')");
                
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
                invocableEngine.invokeFunction("sayHello", "from java");
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

