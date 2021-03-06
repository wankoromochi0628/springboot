package com.example.demo.common.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/* 用語の整理
 *
 * Aspect(アスペクト)
 * 複数のクラスにまたがる関心事をモジュール化したもの(まとめたもの)。
 *
 * JoinPoint(ジョインポイント)
 * Advice(下記参照)を挿入する場所。
 * 場所といってもソースの特定の位置というわけではなく、下記のような実行されるタイミングのこと。
 * ・メソッド(やコンストラクタ)の実行前
 * ・メソッド(やコンストラクタ)の実行後
 *
 * Advice(アドバイス)
 * JoinPointで実行される処理(差し込みたい処理)のこと。これには以下の種類がある。
 *  ・Before advice
 *      JoinPointの前に実行されるAdviceのこと。
 *      例外がスローされない限り実行を止めることは出来ない。
 *
 *  ・After (finally) advice
 *      JoinPointの後に実行されるAdviceのこと。
 *      メソッドが正常終了、例外で終了に関わらずメソッドの実行後に実行される。
 *
 *  ・After returning advice
 *      JoinPointの処理が正常終了時に実行されるAdviceのこと。
 *      例外がスローされた場合は無効となる。
 *
 *  ・After throwing advice
 *      JoinPointで例外が発生した直後に実行されるAdviceのこと。
 *
 *  ・Around advice
 *      Joinpointの前後で実行されるAdviceのこと。実行タイミングは自身で定義できる等、最も汎用的なAdvice。
 *
 * PointCut(ポイントカット)
 *  処理がJoinPointに到達した時、Adviceを実行するかどうかを判定するもの。
 *  例えば、メソッド名がgetで始まる時だけ処理する、のような条件を定義したもの。
 *  ＊＊executionを利用する
 *
 *  これらで重要なことは、上記の仕組みは全てクラス本来の機能として用意されているものではなく、
 *  AOPによって外部から組み込まれている機能だということ。
 *
 */
@Aspect
@Component
public class TraceInterceptor {

    /**
     *
     */
    @Before("execution(* com.example.demo.DemoApplication.run(..))")
    public void before (JoinPoint jp) {
        System.out.println("/-----------------------------------------/");
        System.out.println("/----              Before             ----/");
        System.out.println("[Before]signature:" + jp.getSignature());
        System.out.println("/--- Spring Bootを使用した処理を作動します。---/");
        System.out.println("/-----------------------------------------/");
    }

    /**
     *
     */
    @After("execution(* com.example.demo.DemoApplication.run(..))")
    public void after(JoinPoint jp) {
        System.out.println("/-----------------------------------------/");
        System.out.println("/----               After             ----/");
        System.out.println("[After]signature:" + jp.getSignature());
        System.out.println("--- ");
        System.out.println("/-----------------------------------------/");

    }

    /**
     *
     */
    @AfterReturning("execution(* com.example.demo.DemoApplication.run(..))")
    public void afterReturning(JoinPoint jp) {
        System.out.println("\n ---------------------------　afterReturning !! ---------------------------");
    }

    /**
     *
     */
    @Around("execution(* com.example.demo.DemoApplication.run(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("--------------------------- around - before !! ---------------------------\n");   // 前処理
        System.out.println("[Around]ProceedingJoinPoint:" + pjp.proceed());
        System.out.println("\n--------------------------- around - after !! ---------------------------");    // 後処理
    }
}
