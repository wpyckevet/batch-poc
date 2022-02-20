package org.warpy.infrastructure.rest.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.warpy.domain.model.TestItem;
import org.warpy.infrastructure.rest.ItemDTO;

@Mapper(componentModel = "cdi")
public interface ItemDTOMapper {

    Logger LOGGER = LoggerFactory.getLogger(ItemDTOMapper.class);

    TestItem map(ItemDTO source);

    ItemDTO inverseMap(TestItem source);

    default UUID map(String source) {
        if (source == null) {
            return null;
        }
        try {
            return UUID.fromString(source);
        } catch (IllegalArgumentException ex) {
            LOGGER.warn(ex.getMessage());
            return UUID.randomUUID();
        }
    }

    default String map(UUID source) {
        return source.toString();
    }
}
