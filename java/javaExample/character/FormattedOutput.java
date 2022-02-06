package character;

import java.util.Scanner;

public class FormattedOutput {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("请输入地名：");
        String location = s.next();
        System.out.print("请输入公司类型：");
        String companyType = s.next();
        System.out.print("请输入公司名字：");
        String companyName = s.next();
        System.out.print("请输入老板名字�?");
        String bossName = s.next();
        System.out.print("请输入金额：");
        double sum = s.nextDouble();
        System.out.print("请输入产品：");
        String product = s.next();
        System.out.print("请输入计量单位：");
        String unit = s.next();

        s.close();

        String str = "%s最大%s%s倒闭了，王八蛋老板%s吃喝嫖赌，欠下了%.1f个亿，" + 
        "带着他的小姨子跑了!我们没有办法，拿着%s抵工姿!原价都是一%s多、两%s多、三%s多的%s，" + 
       "现在全部只卖二十块，统统只要二十块!%s王八蛋，你不是人!我们辛辛苦苦给你干了大半年，" +
       "你不发工资，你还我血汗钱，还我血汗钱!" ;

        System.out.format(str, location, companyType, companyName, 
        bossName, sum, product, unit, unit, unit, product, bossName);
    }
}
