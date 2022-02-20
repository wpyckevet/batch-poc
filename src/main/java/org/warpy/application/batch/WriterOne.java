package org.warpy.application.batch;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.warpy.domain.model.DummyInstance;
import org.warpy.infrastructure.repositories.DummyInstanceRepository;

@Dependent
@Named
@Transactional
@RequiredArgsConstructor(onConstructor = @__({ @Inject }))
public class WriterOne extends AbstractItemWriter {
    private final DummyInstanceRepository dummyInstanceRepository;

    @Override
    public void writeItems(List list) {
        list.stream()
                .filter(Integer.class::isInstance)
                .forEach(o -> store((Integer)o));
    }

    private void store(Integer integer) {
        if (integer != null) {
            var dummyInstance = DummyInstance.builder().value(integer).build();
            dummyInstanceRepository.persistAndFlush(dummyInstance);
        }
    }
}
