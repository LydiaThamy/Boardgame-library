package sg.edu.nus.iss.day27_lecture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
        private Integer gid;
        private String name;
        private Integer year;
        private Integer ranking;
        private Integer usersRated;
        private String url;
        private String image;
}

// public record Game(Integer gid,
//         String name,
//         Integer year,
//         Integer ranking,
//         Integer usersRated,
//         String url,
//         String image) {
// }
