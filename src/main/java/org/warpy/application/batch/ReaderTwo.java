package org.warpy.application.batch;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.warpy.domain.model.DummyInstance;
import org.warpy.infrastructure.repositories.DummyInstanceRepository;

@Dependent
@Named
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Inject }))
public class ReaderTwo extends AbstractItemReader {

    private final DummyInstanceRepository dummyInstanceRepository;
    private List<DummyInstance> dummies;

    private int currentItem;

    @Override
    public DummyInstance readItem() {

        if (dummies.isEmpty() || currentItem >= dummies.size()) {
            log.info("reader two stopping");
            return null;
        }
        log.info("reader two {}", currentItem);
        return dummies.get(currentItem++);
    }

    @Override
    public void open(Serializable checkpoint) {
        currentItem = 0;
        dummies = dummyInstanceRepository.listAll();
    }
}
