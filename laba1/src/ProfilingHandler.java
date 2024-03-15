import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Evaluatable {
    double eval(double x);
}

class Function1 implements Evaluatable {
    @Override
    public double eval(double x) {
        return Math.exp(-Math.abs(2.5 * x)) * Math.sin(x);
    }
}

class Function2 implements Evaluatable {
    @Override
    public double eval(double x) {
        return x * x;
    }
}

class ProfilingHandler implements InvocationHandler {
    private final Object target;

    ProfilingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long end = System.nanoTime();

        System.out.println(method.getName() + " took " + (end - start) + " ns");

        if (args != null && args.length > 0) {
            StringBuilder argsString = new StringBuilder("(");
            for (int i = 0; i < args.length; i++) {
                argsString.append(args[i]);
                if (i < args.length - 1)
                    argsString.append(", ");
            }
            argsString.append(")");
            System.out.println(method.getName() + argsString + " = " + result);
        }

        return result;
    }
}

