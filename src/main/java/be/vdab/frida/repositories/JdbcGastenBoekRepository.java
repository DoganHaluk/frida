package be.vdab.frida.repositories;

import be.vdab.frida.domain.GastenBoekEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
class JdbcGastenBoekRepository implements GastenBoekRepository {
    private final SimpleJdbcInsert insert;
    private final JdbcTemplate template;

    JdbcGastenBoekRepository(JdbcTemplate template) {
        this.template = template;
        insert = new SimpleJdbcInsert(template).withTableName("gastenboek").usingGeneratedKeyColumns("id");
    }

    @Override
    public long create(GastenBoekEntry entry) {
        return insert.executeAndReturnKey(Map.of("naam", entry.getNaam(), "datum", entry.getDatum(), "bericht", entry.getBericht())).longValue();
    }

    private final RowMapper<GastenBoekEntry> entryRowMapper = (result, rowNum) -> new GastenBoekEntry(result.getLong("id"), result.getString("naam"), result.getObject("datum", LocalDate.class), result.getString("bericht"));

    @Override
    public List<GastenBoekEntry> findAll() {
        var sql = "SELECT id, naam, datum, bericht FROM gastenboek ORDER BY datum DESC";
        return template.query(sql, entryRowMapper);
    }

    @Override
    public void delete(Long[] ids) {
        if (ids.length != 0) {
            var sql = "DELETE FROM gastenboek WHERE id IN (" + "?,".repeat(ids.length - 1) + "?)";
            template.update(sql, ids);
        }
    }
}
