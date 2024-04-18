package ru.bsu.yellowguitarbend.infrastructure.persistance.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bsu.yellowguitarbend.infrastructure.persistance.entity.InstrumentEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentJpaRepository extends JpaRepository<InstrumentEntity, Long> {
  Optional<InstrumentEntity> findFirstByCommercialName(String commercialName);
  Optional<InstrumentEntity> findBySeriesNumber(String seriesNumber);
  Page<InstrumentEntity> findAllByCommercialName(String commercialName, Pageable pageable);
  List<InstrumentEntity> findAllBySeriesNumberIn(List<String> seriesNumbers);
}
