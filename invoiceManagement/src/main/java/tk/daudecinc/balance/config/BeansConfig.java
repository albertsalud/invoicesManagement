package tk.daudecinc.balance.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import tk.daudecinc.balance.utils.YearHolder;

@Configuration
public class BeansConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Value("${dd5.web.url}")
	private String webUrl;
	
	@Autowired
	private ServletContext sc;
	
	@PostConstruct
	public void initGlobalParameters() {
		sc.setAttribute("webURL", webUrl);
	}
	
	@Bean
	@SessionScope
	public YearHolder yearHolder() {
		return new YearHolder();
	}
	
}
