# Final Exam.

🥅🫘

Clone this repository onto your development envrionment with Git. Alternatively,
[Giget](https://github.com/unjs/giget) may be used

```pwsh
# pnpm
pnpm dlx giget@latest gh:gekkotadev/java-finals4

# npm
npx giget@latest gh:gekkotadev/java-finals4
```

Your part will be designing the GUI of the calculator program.

The logic required should be in the `com.mycompany.calculator.eval` namespace and
can be imported and used via the following code.

```diff
+ import com.mycompany.calculator.eval.Eval;

  public class Main {
+   public static void main(String[] args) throws Exception {
+     final String result = Eval.evaluate("1 / 0");
+     System.out.println(result);
    }
}
```

Note that the data type of the result returned by `Eval.evaluate` is a `String`

The algorithm used is a modified Shunting Yard algorithm which disregards the
existence of parentheses due to it not being required in the calculator program,
thus simplifying the implementation. It still successfully converts infix
expressions into Reverse Polish Notation, and evaluated accordingly.

Thanks to Anton Kesy on Stack Overflow for [providing the right direction in
solving this problem](https://stackoverflow.com/a/2969583).

These following resources were used as an additional guide
- https://brilliant.org/wiki/shunting-yard-algorithm/
- https://aquarchitect.github.io/swift-algorithm-club/Shunting%20Yard/
- https://youtu.be/1VjJe1PeExQ

While I could have just imported the `ScriptEngineManager` class and called it a
day, it runs the risk that the runtime environment does not support embedding a
JavaScript engine in the program along with grade deductions for taking the easy
way out.
