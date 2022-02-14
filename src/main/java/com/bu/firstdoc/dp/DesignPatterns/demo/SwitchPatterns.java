package com.bu.firstdoc.dp.DesignPatterns.demo;

/**
 * @author haizhuangbu
 * @date 2:52 下午 2022/2/14
 * @mark SwitchPatterns
 */
public class SwitchPatterns {

    private static String PARENT_MODE;

    static {
        System.out.println("静态{}");
    }
    /**
     * @author haizhuang.bu
     * @date 2:52 下午 2022/2/14
     * @param num1 参数1
     * @param num2 参数2
     * @mark 默认*
     */
    public static Integer simpleFactory(Integer num1,Integer num2){
        switch (SwitchPatterns.PARENT_MODE){
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            case "-":
                return num1 - num2;
            case "+":
                return num1 + num2;
        }
        return num1 * num2;
    }

    public  static  String setParentMode(String parentMode){
        SwitchPatterns.PARENT_MODE = parentMode;
        return parentMode;
    }

    public static void main(String[] args) {
        SwitchPatterns.setParentMode("*");
        System.out.println(SwitchPatterns.simpleFactory(2, 3));
        SwitchPatterns.setParentMode("-");
        System.out.println(SwitchPatterns.simpleFactory(2, 3));
    }

}
