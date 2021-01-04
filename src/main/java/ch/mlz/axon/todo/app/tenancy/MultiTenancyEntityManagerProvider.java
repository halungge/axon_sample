package ch.mlz.axon.todo.app.tenancy;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;

import javax.persistence.EntityManager;

@Slf4j
public class MultiTenancyEntityManagerProvider {

    //@Bean
    public EntityManagerProvider entityManagerProvider() {
        log.info("using custom entityManagerProvider!!!!");
        final ContainerManagedEntityManagerProvider containerManagedEntityManagerProvider = new ContainerManagedEntityManagerProvider();
        final EntityManager entityManager = containerManagedEntityManagerProvider.getEntityManager();
        return containerManagedEntityManagerProvider;
    }
}
