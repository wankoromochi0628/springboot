package com.example.demo.common.argument;

/*
 * @Dataアノテーション
 * コンパイル時 (classファイル生成時) に、各フィールドの「seter/geter」「toStringメソッド」「equalsメソッド」「hashCodeメソッド」が生成される。
 *
 * そのため、ソースコードから冗長な部分なくなり、スッキリする。
 *　final修飾子がついてる場合は、setterは生成されない。
 */
public class Argument {
    private final int a;

    public Argument(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    private final int b;
}
