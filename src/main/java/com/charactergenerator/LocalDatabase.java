package com.charactergenerator;
import com.charactergenerator.entities.actionEntity.ActionEntityRepository;
import com.charactergenerator.entities.professionEntity.ProfessionRepository;
import com.charactergenerator.entities.traitEntity.TraitEntityRepository;
import com.charactergenerator.scrapper.ActionScrapper;
import com.charactergenerator.scrapper.ProfessionScrapper;
import com.charactergenerator.scrapper.TraitScrapper;
import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalDatabase {
    private static final Logger log = LoggerFactory.getLogger(LocalDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TraitEntityRepository traitEntityRepository,
                                   ActionEntityRepository actionEntityRepository,
                                   ProfessionRepository professionRepository){
        return args ->{
            TraitScrapper traitScrapper = new TraitScrapper(traitEntityRepository);
            traitScrapper.saveTraitsToDatabase();
            ActionScrapper actionScrapper = new ActionScrapper(actionEntityRepository);
            actionScrapper.saveActionsToDatabase();
            ProfessionScrapper professionScrapper = new ProfessionScrapper(professionRepository);
            professionScrapper.saveProfessionsToDatabase();
        };
    }

}
