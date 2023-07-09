package sg.edu.nus.iss.day27_lecture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    
    private String user;
    private Integer rating;
    private String cText;
    private Integer gid;
    
    public Comment(Integer gid) {
        this.gid = gid;
    }

}

// public record Comment(String user, Integer rating, String cText, Integer gid) {

//     public Comment() {
//         this(null, Integer.MIN_VALUE, null, Integer.MIN_VALUE);
//     }

//     public String user() {
//         return user;
//     }

//     public Integer rating() {
//         return rating;
//     }

//     public String cText() {
//         return cText;
//     }

//     public Integer gid() {
//         return gid;
//     }

//     @Override
//     public String toString() {
//         return "Comment []";
//     }
    
// }
