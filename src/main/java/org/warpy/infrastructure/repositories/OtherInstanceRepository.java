package org.warpy.infrastructure.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.warpy.domain.model.OtherInstance;

@ApplicationScoped
public class OtherInstanceRepository implements PanacheRepository<OtherInstance> {
}
