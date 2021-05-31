package be.vdab.frida.services;

import be.vdab.frida.domain.Snack;
import be.vdab.frida.repositories.SnackRepository;

import java.util.List;
import java.util.Optional;

public class DefaultSnackService implements SnackService {
    private final SnackRepository snackRepository;

    DefaultSnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    @Override
    public Optional<Snack> read(long id) {
        return Optional.empty();
    }

    @Override
    public void update(Snack snack) {
        snackRepository.update(snack);
    }

    @Override
    public List<Snack> findByBeginNaam(String beginNaam) {
        return null;
    }
}
