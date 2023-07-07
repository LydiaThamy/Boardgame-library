package sg.edu.nus.iss.day27_lecture.model;

public record Game(Integer gameId,
        String name,
        Integer year,
        Integer ranking,
        Integer usersRated,
        String url,
        String image) {
}
