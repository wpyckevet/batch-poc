package org.warpy.application.batch;

import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Dependent
@Named
@Transactional
@Slf4j
public class ReaderOne extends AbstractItemReader {

    private static final int MAX = 1000;

    private Integer currentCount;

    @Override
    public void open(Serializable checkpoint) {
        currentCount = 0;
        if (checkpoint != null && (Integer) checkpoint > currentCount) {
            currentCount = (Integer) checkpoint;
        }
    }

    @Override
    public Integer readItem() {
        if (currentCount > MAX) {
            log.info("reader two stopping");
            return null;
        }
        log.info("reader one {}", currentCount);
        return currentCount++;
    }
}
