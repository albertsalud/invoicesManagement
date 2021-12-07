package tk.daudecinc.balance.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class ServicesUtils {

	public int manageYear(Integer year) {
		return year == null ?
				LocalDate.now().getYear() : year.intValue();
	}
}
