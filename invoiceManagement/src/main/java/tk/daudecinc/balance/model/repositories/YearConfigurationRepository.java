package tk.daudecinc.balance.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.balance.model.entities.YearConfiguration;

public interface YearConfigurationRepository extends JpaRepository<YearConfiguration, Integer> {

	List<YearConfiguration> findAllByOrderByYearDesc();

}
