package bgps.dao;

import bgps.model.Mark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MarkJdbc {
    private final JdbcTemplate jdbcTemplate;

    public MarkJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Mark get(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM mark WHERE id = ?", this::mapMark, id);
    }

    private Mark mapMark(ResultSet resultSet, int i) throws SQLException {
        Mark mark = new Mark(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("value")
        );
        return mark;
    }

    public Mark search(String markName) {
        return jdbcTemplate.queryForObject("SELECT * FROM mark WHERE name = ?", Mark.class, markName);
    }
}
