package message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 厚积薄发
 * @create 2023-06-06-16:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptMessage {
    String role;
    String content;
}