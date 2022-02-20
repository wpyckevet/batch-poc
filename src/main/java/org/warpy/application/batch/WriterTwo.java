package org.warpy.application.batch;

import java.util.List;
import java.util.UUID;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.warpy.domain.model.DummyInstance;
import org.warpy.domain.model.OtherInstance;
import org.warpy.infrastructure.repositories.OtherInstanceRepository;

@Dependent
@Named
@Transactional
@RequiredArgsConstructor(onConstructor = @__({ @Inject }))
public class WriterTwo extends AbstractItemWriter {
    private final OtherInstanceRepository otherInstanceRepository;

    @Override
    public void writeItems(List list) {
        list.stream()
                .filter(DummyInstance.class::isInstance)
                .forEach(o -> store((DummyInstance)o));
    }

    private void store(DummyInstance dummyInstance) {
        if (dummyInstance != null) {
            var otherInstance = OtherInstance.builder().name(UUID.randomUUID().toString()).value(dummyInstance.getValue()).build();
            otherInstanceRepository.persistAndFlush(otherInstance);
        }
    }
}
