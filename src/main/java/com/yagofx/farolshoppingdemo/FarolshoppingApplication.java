package com.yagofx.farolshoppingdemo;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

//import com.yagofx.farolshoppingdemo.entity.enums.Disponibilidade;
//import com.yagofx.farolshoppingdemo.entity.enums.Status;
import com.yagofx.farolshoppingdemo.repository.ItemRepository;

//import com.yagofx.farolshoppingdemo.entity.Item;

@SpringBootApplication
public class FarolshoppingApplication extends SpringBootServletInitializer {

//	private static final Logger log = LoggerFactory.getLogger(FarolshoppingApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(FarolshoppingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FarolshoppingApplication.class);
	}

	@Bean
	public CommandLineRunner demo(ItemRepository repository) {
		return (args) -> {
//			repository.save(new Item("asd123", "legal", "aqui"));
//			repository.save(new Item("asd124", "daora", "laaa"));
//			repository.save(new Item("asd125", "bacana", "acolá"));
//			repository.save(new Item("asd126", "fera", "por ai"));
//			repository.save(new Item("asd127", "show", "acá"));
//			repository.save(new Item("asd128", "incrivel", "embaixo"));
			
//			log.info(repository.findById(9).toString());
//			repository.findByDisponibilidade(Disponibilidade.DISPONIVEL)
//				.forEach(i -> log.info(i.toString()));
//			repository.findByStatus(Status.ATIVO)
//				.forEach(i -> log.info(i.toString()));
			
//			log.info("Itens encontrados com findAll():");
//			log.info("--------------------------------");
//			for (Item item : repository.findAll()) {
//				log.info(item.toString());
//			}
//			log.info("");
//			
//			log.info("Itens encontrados com findByDescription('legal'):");
//			log.info("-------------------------------------------------");
//			repository.findByDescricao("legal").forEach(l -> {
//				log.info(l.toString());
//			});
//			log.info("");
		};
	}
	
}
