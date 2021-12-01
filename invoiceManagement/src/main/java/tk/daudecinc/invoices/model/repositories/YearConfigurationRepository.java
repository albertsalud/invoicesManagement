package tk.daudecinc.invoices.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.invoices.model.entities.YearConfiguration;

public interface YearConfigurationRepository extends JpaRepository<YearConfiguration, Integer> {

	List<YearConfiguration> findAllByOrderByYearDesc();

}
