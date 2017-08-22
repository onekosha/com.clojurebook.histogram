package com.clojurebook.histogram;

/**
 *
 * @author Sandy Corn
 */
import clojure.lang.IFn;
import clojure.lang.Keyword;
import clojure.lang.RT;
import clojure.lang.Symbol;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    private static final IFn requireFn = RT.var("clojure.core", "require").fn();
    private static final IFn randomIntFn = RT.var("clojure.core", "rand-int").fn();
    static{
            requireFn.invoke(Symbol.intern("com.clojurebook.histogram"));
    }
    
    private static final IFn frequencies = RT.var("clojure.core", "frequencies").fn();
    private static Object keywords = RT.var("com.clojurebook.histogram", "keywords").deref();

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) throws Exception {
        Map<Keyword, Integer> sampleHistogram = (Map<Keyword, Integer>)frequencies.invoke(keywords);
        System.out.println("Number of :a keywords in sample histogram: " + sampleHistogram.get(Keyword.intern("a")));
        System.out.println("Cpmplete sample histogram: " + sampleHistogram);
        System.out.println("");
        System.out.println("Histogram of chars in 'I left my heart in San Francisco': " + 
                frequencies.invoke("I left my heart in San Francisco".toLowerCase()));
        System.out.println("Build OK!");
        
        ArrayList randomInts = new ArrayList();
        for(int i = 0; i < 500; i++)
            randomInts.add(randomIntFn.invoke(10));
        System.out.println("Histogram of 500 random ints [0, 10): " + frequencies.invoke(randomInts));
    }
}
