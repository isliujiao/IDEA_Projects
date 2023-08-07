package creativity;

import org.junit.Test;

import java.util.*;

/**
 * @author 厚积薄发
 * @create 2023-05-06-9:39
 */
public class myTest {

    @Test
    public void t() {
        System.out.println(letterCombinations("23"));
    }

    StringBuilder sb = new StringBuilder();
    List<String> list = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(digits, 0, numString);
        return list;
    }

    public void dfs(String digits, int index, String[] numString){
        if(digits.length() == index){
            list.add(sb.toString());
            return ;
        }
        String str = numString[digits.charAt(index) - '0'];
        for(int i = 0; i < str.length(); i++){
            sb.append(str.charAt(i));
            dfs(digits,index + 1, numString);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
