package com.charactergenerator;
import com.charactergenerator.entities.abilityScores.AbilityGeneratorMethods;
import com.charactergenerator.entities.abilityScores.AbilityScore;
import com.charactergenerator.entities.abilityScores.CharacterAbilityScore;
import com.charactergenerator.entities.abilityScores.Strength;
import com.charactergenerator.entities.actionEntity.ActionEntityRepository;
import com.charactergenerator.entities.professionEntity.ProfessionRepository;
import com.charactergenerator.entities.traitEntity.TraitEntityRepository;
import com.charactergenerator.dataScrappers.ActionScrapper;
import com.charactergenerator.dataScrappers.ProfessionScrapper;
import com.charactergenerator.dataScrappers.TraitScrapper;
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
//            TraitScrapper traitScrapper = new TraitScrapper(traitEntityRepository);
//            traitScrapper.saveTraitsToDatabase();
//            ActionScrapper actionScrapper = new ActionScrapper(actionEntityRepository);
//            actionScrapper.saveActionsToDatabase();
//            ProfessionScrapper professionScrapper = new ProfessionScrapper(professionRepository);
//            professionScrapper.saveProfessionsToDatabase();
            CharacterAbilityScore a = new CharacterAbilityScore(AbilityGeneratorMethods.THREE_DICE_SIX);
            log.info(a.getStrength().toString());
        };
    }

}
