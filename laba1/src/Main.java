import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Evaluatable f1 = new Function1();
        Evaluatable f2 = new Function2();

        Evaluatable f1Proxy = (Evaluatable) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new ProfilingHandler(f1)
        );

        Evaluatable f2Proxy = (Evaluatable) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new ProfilingHandler(f2)
        );

        double x = 1.0;

        System.out.println("F1: " + f1Proxy.eval(x));
        System.out.println("F2: " + f2Proxy.eval(x));
    }
}
