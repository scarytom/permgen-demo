package com.scarytom.sandpit.permgen;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public final class Main {

    public interface IFoo {
        static String foodata = "1234567890qwertytasdasdlkgflkjgfkljgfkjlcvxsfdwfJSDLKJVACOIWREJFopfhjaodsjfzmco8wjfc28430-024tjuspf87gyh5fj6u53k8f7hosy847ghfa8743a387nbopcay97yf9348y987530-q4wfpadfjhsjnczkjlxvljr98uf02-3203840329842384siffhsdjjjjjjjjjjjjjjjjvzocuihvpfdaohgpoasrhgpomncvoroncapohpac94hewpowuahedrcuwrpoacyupaowuyrea834ue8r2qcuyrporowfhhfucphpuehpufcaphufaphuiuhifcrhipufrhpuifahapufhhpfdajhkjdfkljhvdhjl#2";
        void foo();
    }

    private static class FooInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    private static Map<ClassLoader, IFoo> leakedClassLoaders = new HashMap<ClassLoader, IFoo>();

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100000; i++) {
                URLClassLoader newClassLoader = new URLClassLoader(new URL[0]);
                IFoo f = (IFoo) Proxy.newProxyInstance(newClassLoader, new Class<?>[] { IFoo.class }, new FooInvocationHandler());
                leakedClassLoaders.put(newClassLoader, f);
            }
        } 
        catch (Throwable any) {
            any.printStackTrace();
            throw any;
        }
    }

}
