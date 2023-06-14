package ru.nsu.fit.oop.melnikov.dsl.dto;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.IOException;

public class AbstractConfig {
    
    protected AbstractConfig() {
    }

    protected static void parse(String filePath, Object delegator) throws IOException {
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(GroupsConfig.class.getClassLoader(), new Binding(), cc);
        DelegatingScript script = (DelegatingScript) sh.parse(new File(filePath));
        script.setDelegate(delegator);
        script.run();
    }
    
    protected static void delegate(Object object, Closure<?> closure) {
        closure.setDelegate(object);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
    }
    
}
