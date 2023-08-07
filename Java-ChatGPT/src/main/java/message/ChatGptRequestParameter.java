package message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 厚积薄发
 * @create 2023-06-06-16:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptRequestParameter {

    String model = "gpt-3.5-turbo";

    List<ChatGptMessage> messages = new ArrayList<>();

    public void addMessages(ChatGptMessage message) {
        this.messages.add(message);
    }

}
