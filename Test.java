import java.util.*;

public class Test {
    public int countWays(int n) {

        // 有个小孩正在上楼梯，楼梯有 n 阶台阶
        // 小孩一次可以上 1 阶、2 阶、3 阶。
        // 请实现一个方法，计算小孩有多少种上楼的方式。
        // 为了防止溢出，请将结果 Mod 1000000007
        // 给定一个正整数 int n，请返回一个数，代表上楼的方式数。
        // 保证 n 小于等于 100000。

        // 测试样例 1：
        // 1
        // 返回：1
        // 测试样例 2：
        // 3
        // 返回：4
        // 测试样例 3：
        // 4
        // 返回：7

        int[] arr = {1, 2, 4};
        if(n <= 0) {
            return 0;
        }else if(n <= 3){
            return arr[n - 1];
        }else {
            for(int i = 4; i <= n; i++){
                // 模运算特点: (a + b) % c = (a % c + b % c) % c
                int tmp = ((arr[0] + arr[1]) % 1000000007 + arr[2]) % 1000000007;
                arr[0] = arr[1];
                arr[1] = arr[2];
                arr[2] = tmp;
            }
        }
        return arr[2];
    }
}

class Main {
    public static void main(String[] args) {

        // 大家应该都会玩 “锤子剪刀布” 的游戏：
        // 现给出两人的交锋记录，请统计双方的胜、平、负次数，并且给出双方分别出什么手势的胜算最大。

        // 输入描述:
        // 输入第 1 行给出正整数 N（ <= 105），即双方交锋的次数。
        // 随后 N 行，每行给出一次交锋的信息
        // 即甲、乙双方同时给出的的手势。
        // C 代表 “锤子”、J 代表 “剪刀”、B 代表 “布”
        // 第 1 个字母代表甲方，第 2 个代表乙方，中间有 1 个空格。
        // 输出描述:
        // 输出第 1、2 行分别给出甲、乙的胜、平、负次数，数字间以 1 个空格分隔。
        // 第 3 行给出两个字母，分别代表甲、乙获胜次数最多的手势，中间有1个空格。
        // 如果解不唯一，则输出按字母序最小的解。

        // 示例:
        // 输入
        // 10
        // C J
        // J B
        // C B
        // B B
        // B C
        // C C
        // C B
        // J B
        // B C
        // J J
        // 输出
        // 5 3 2
        // 2 3 5
        // B B

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            Map<String, Integer> map1 = new HashMap<>(); // 存放甲的胜的手势和次数
            Map<String, Integer> map2 = new HashMap<>(); // 存放乙的胜的手势和次数
            map1.put("B", 0);
            map1.put("C", 0);
            map1.put("J", 0);
            map2.put("B", 0);
            map2.put("C", 0);
            map2.put("J", 0);
            int[] win = {0, 0, 0};
            // 存放甲的 胜、平、负的次数
            // 乙胜的次数与甲正好相反
            for (int i = 0; i < n; i++) {
                String p1 = sc.next(); // 甲
                String p2 = sc.next(); // 乙
                // 判断胜负
                if (p1.equals("C") && p2.equals("J") || p1.equals("J") && p2.equals("B") || p1.equals("B") && p2.equals("C")) {
                    // 锤子 对 剪刀; 剪刀 对 布; 布 对 石头
                    win[0] += 1;
                    map1.put(p1, map1.get(p1) + 1);
                }else if (p1.equals("C") && p2.equals("B") || p1.equals("J") && p2.equals("C") || p1.equals("B") && p2.equals("J")) {
                    // 锤子 对 布; 剪刀 对 锤子; 布 对 剪刀
                    win[2] += 1;
                    map2.put(p2, map2.get(p2) + 1);
                }else {
                    win[1] += 1;
                }
            }
            System.out.println(win[0] + " " + win[1] + " " + win[2]);
            System.out.println(win[2] + " " + win[1] + " " + win[0]);
            int max = 0;
            for (Map.Entry<String, Integer> m1 : map1.entrySet()) {
                // 遍历 map1, 找出胜出最多的手势
                if (m1.getValue() > max) {
                    max = m1.getValue();
                }
            }
            if (max == 0) {
                // 如果甲无胜的记录，则解不唯一，输出 "B".
                System.out.print("B");
            }else {
                for (Map.Entry<String, Integer> m1 : map1.entrySet()) {
                    // 再次遍历 map1, 找出 max 对应的手势
                    if (m1.getValue() == max) {
                        System.out.print(m1.getKey());
                        break;
                        // 找到第一个最大值输出后就结束循环
                    }
                }
            }
            System.out.print(" ");
            max = 0;
            for (Map.Entry<String, Integer> m2 : map2.entrySet()) {
                // 遍历 map2, 找出胜出最多的手势
                if (m2.getValue() > max) {
                    max = m2.getValue();
                }
            }
            if (max == 0) {
                // 如果甲无胜的记录，则解不唯一，输出 "B".
                System.out.print("B");
            }else {
                for (Map.Entry<String, Integer> m2 : map2.entrySet()) {
                    // 再次遍历 map2, 找出 max 对应的手势
                    if (m2.getValue() == max) {
                        System.out.println(m2.getKey());
                        break;
                    }
                }
            }
        }
    }
}