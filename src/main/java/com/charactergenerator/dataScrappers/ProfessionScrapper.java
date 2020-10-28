package com.charactergenerator.dataScrappers;
import com.charactergenerator.entities.professionEntity.ProfessionEntity;
import com.charactergenerator.entities.professionEntity.ProfessionRepository;
import com.charactergenerator.LocalDatabase;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class ProfessionScrapper {
    private static final Logger log = LoggerFactory.getLogger(LocalDatabase.class);
    private static final String url =
            "https://midnight-wolf.fandom.com/wiki/Fantasy/Medieval_Character_Jobs_or_Occupations";
    private final ProfessionRepository professionRepository;
    private Document dom;

    public ProfessionScrapper (ProfessionRepository professionRepository) throws IOException {
        this.professionRepository = professionRepository;
        this.dom = Jsoup.connect(url).get();
    }

    private Elements getProfessionList(){
        Elements professionList = getDom().getElementsByClass("article-table").select("td");
        return formatProfessionList(professionList);
    }

    private Elements formatProfessionList(Elements professionList){
        professionList.forEach(element -> {
            element.text(element.text().strip());
            element.addClass(
                    element.text().toLowerCase().replaceAll("[^a-zA-Z0-9]","_"));
        });
        return professionList;
    }

    private boolean isProfessionInDB(Stream<ProfessionEntity> professionsFromDB, String professionName){
        if(professionsFromDB != null) return professionsFromDB.anyMatch(professionFromDB -> professionFromDB.getProfessionName().equals(professionName));
        else return false;
    }

    public void saveProfessionsToDatabase(){
        List<ProfessionEntity> professionEntities = new ArrayList<>();
        List<ProfessionEntity> professionEntitiesFromDB = getProfessionRepository().findAll();
        Elements professionList = getProfessionList();
        professionList.forEach(profession -> {
            ProfessionEntity professionEntity = new ProfessionEntity(profession.text(), profession.className());
            if(!isProfessionInDB(professionEntitiesFromDB.stream(),
                    professionEntity.getProfessionName())){
                professionEntities.add(professionEntity);
            }
        });
        getProfessionRepository().saveAll(professionEntities);
    }


}
