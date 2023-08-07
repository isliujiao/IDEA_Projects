package com.example.javatest.string;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。

 输入: "abab"
 输出: True
 解释: 可由子字符串 "ab" 重复两次构成。

 输入: "aba"
 输出: False

 输入: "abcabcabcabc"
 输出: True
 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class g重复的子字符串 {

    @Test
    void repeatedSubstringPattern(){

        System.out.println(repeatedSubstringPattern("ababab"));//0 0 1 2 3 4 true
        System.out.println(repeatedSubstringPattern("abcabcabcabc"));//0 0 0 1 2 3 4 5 6 7 8 9 true
        System.out.println(repeatedSubstringPattern("abccba"));//0 0 0 0 0 1 false
    }

    public boolean repeatedSubstringPattern(String str){
        int[] next = new int[str.length()];//前缀表
        next[0] = 0;
        int j = 0;
        int len = str.length();
        // 构造 next 数组过程，j从0开始，i从1开始
        for (int i = 1; i < len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while(j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j - 1];
            }
            // 匹配成功，j往后移
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            // 更新 next 数组的值
            next[i] = j;
        }

        for (int i : next) {
            System.out.print(i + " ");
        }
        //todo 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len - 1] > 0 && len % (len - (next[len - 1])) == 0) {
            return true;
        }
        return false;
    }

    /**
     * ===========前置知识点===============
     * 求文本串是否包含模式串
     * 文本串：a a b a a b a a f
     * 模式串：a a b a a f
     *
     * todo（ 求最长相等前后缀）
     *                  a a b a a f
     * 前缀表 next[] = [0,1,0,1,2,0]
     */
    @Test
    void KMPDemo(){
        String s = "aabaabaaf";
        String str = "aabaaf";
        for (int a : getNext(new int[str.length()],str)) {
            System.out.print(a + " ");
        }
        int[] next = getNext(new int[str.length()], str);
        int j = 1;//模式串的索引
        for (int i = 0; i < s.length(); i++) {
            if(j > 0 && s.charAt(i) != str.charAt(j)){
                j = next[j - 1];
            }
            if(s.charAt(i) != str.charAt(j)){
                j++;
            }
            if(j == str.length()){
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }

    /**
     * 初始化前缀表
     *
     * @param next 前缀表数组
     * @param str 字符串
     * @return 初始化后的前缀表
     */
    public int[] getNext(int[] next,String str){
        int j = 0;//头部
        next[0] = 0;//初始化第一个
        for (int i = 1; i < str.length(); i++) {
            while(j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j - 1]; //如果不匹配则跳到前一位
            }
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            int min = Integer.MIN_VALUE;
            next[i] = j;
        }
        return next;
    }


    @Test
    void t(){
        longestCommonPrefix(new String[]{"cir","car"});
    }
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        for(int i = 1;i < strs.length;i++){
            prefix = f(prefix,strs[i]);
            if(prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }
    public String f(String str1,String str2){
        int index = 0;
        // int length = Math.min(str1.length(),str2.length());
        // while(index < length && str1.charAt(index) == str2.charAt(index)){
        //     index++;
        // }

        for(int i = 0;i < str1.length() && i < str2.length();i++){
            if(str1.charAt(i) == str2.charAt(i)){
                index++;
            }
        }
        return str1.substring(0,index);
    }



}
