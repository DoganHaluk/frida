package be.vdab.frida.repositories;

import be.vdab.frida.domain.Snack;
import be.vdab.frida.exceptions.SnackNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JdbcSnackRepository implements SnackRepository {
    private final JdbcTemplate template;

    JdbcSnackRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Snack> snackRowMapper = (result, rowNum) -> new Snack(result.getLong("id"), result.getString("naam"), result.getBigDecimal("prijs"));

    @Override
    public Optional<Snack> findById(long id) {
        try {
            var sql = "SELECT id, naam, prijs FROM snacks WHERE id =?";
            return Optional.of(template.queryForObject(sql, snackRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Snack snack) {
        var sql = "UPDATE snacks SET naam=?, prijs=? WHERE id=?";
        if (template.update(sql, snack.getNaam(), snack.getPrijs(), snack.getId()) == 0) {
            throw new SnackNietGevondenException();
        }
    }

    @Override
    public List<Snack> findByBeginNaam(String beginNaam) {
        var sql = "SELECT id, naam, prijs FROM snacks WHERE naam LIKE ?";
        return template.query(sql, snackRowMapper, beginNaam + '%');
    }
}
