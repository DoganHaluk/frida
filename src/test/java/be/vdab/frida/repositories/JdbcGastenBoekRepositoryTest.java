package be.vdab.frida.repositories;

import be.vdab.frida.domain.GastenBoekEntry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDate;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcGastenBoekRepository.class)
@Sql("/insertGastenBoek.sql")
public class JdbcGastenBoekRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GASTENBOEK = "gastenboek";
    private final JdbcGastenBoekRepository repository;

    public JdbcGastenBoekRepositoryTest(JdbcGastenBoekRepository repository) {
        this.repository = repository;
    }

    @Test
    void create() {
        var id = repository.create(new GastenBoekEntry(0, "test3", LocalDate.now(), "test"));
        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(GASTENBOEK, "id = " + id)).isOne();
    }

    @Test
    void findAll() {
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(GASTENBOEK))
                .extracting(GastenBoekEntry::getDatum)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}
