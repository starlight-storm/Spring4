package sample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import sample.di.business.domain.Product;

@Aspect
@Component
public class MyFirstAspect {

    @Before("execution(* getProduct(String))")
    public void before(JoinPoint jp) {
        // メソッド開始時にWeavingするAdvice
        System.out.println("Hello Before! *** メソッドが呼ばれる前に出てくるよ!");
        Signature sig = jp.getSignature();
        System.out.println("-----> メソッド名を取得するよ：" + sig.getName());
        Object[] o = jp.getArgs();
        System.out.println("-----> 仮引数の値を取得するよ：" + o[0]);
    }

    @After("execution(* getProduct(String))")
    public void after() {
        // メソッド終了後にWeavingするAdvice
        System.out.println("Hello After! *** メソッドが呼ばれた後に出てくるよ!");
    }

    @AfterReturning(value = "execution(* getProduct(String))", returning = "product")
    public void afterReturning(JoinPoint jp, Product product) {
        // メソッド呼出が例外の送出なしに終了した際に呼ばれるAdvice
        System.out.println("Hello AfterReturning! *** メソッドを呼んだ後に出てくるよ");
        // System.out.println("-----> return value = " + ret);
        Signature sig = jp.getSignature();
        System.out.println("-----> メソッド名を取得するよ：" + sig.getName());
        Object[] o = jp.getArgs();
        System.out.println("-----> 仮引数の値を取得するよ：" + o[0]);
    }

    @Around("execution(* getProduct(String))")
    public Product around(ProceedingJoinPoint pjp) throws Throwable {
        // メソッド呼出の前後にWeavingするAdvice
        System.out.println("Hello Around! before *** メソッドを呼ぶ前に出てくるよ!");

        // Signature sig = pjp.getSignature();
        // System.out.println("-----> aop:around メソッド名を取得するよ：" + sig.getName());
        Product p = (Product) pjp.proceed();
        // msg = msg + "：結果に勝手に追加しちゃったほげ！";
        System.out.println("Hello Around! after *** メソッドを呼んだ後に出てくるよ!");
        return p;
    }

    @Around("execution(* hoge())")
    public String around2(ProceedingJoinPoint pjp) throws Throwable {
        // メソッド呼出の前後にWeavingするAdvice
        System.out.println("***pre proceed");

        Signature sig = pjp.getSignature();
        System.out.println("Sig: " + sig.getName());
        String msg = (String) pjp.proceed();
        // msg = msg + "fuga";
        System.out.println("***post proceed");
        return msg;
    }

    @AfterThrowing(value = "execution(* getProduct(String))", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        // メソッド呼出が例外を送出した際に呼ばれるAdvice
        System.out.println("Hello Throwing! *** 例外になったら出てくるよ");
        System.out.println("exception value = " + ex.toString());
    }
}