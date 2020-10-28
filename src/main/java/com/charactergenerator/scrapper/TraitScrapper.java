package com.charactergenerator.scrapper;
import com.charactergenerator.entities.traitEntity.TraitEntity;
import com.charactergenerator.entities.traitEntity.TraitEntityRepository;
import com.charactergenerator.entities.traitEntity.TraitTypes;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Data
public class TraitScrapper {
    private static final String url = "http://ideonomy.mit.edu/essays/traits.html";
    private final TraitEntityRepository traitEntityRepository;
    private Document dom;

    public TraitScrapper(TraitEntityRepository traitEntityRepository) throws IOException {
        this.traitEntityRepository = traitEntityRepository;
        this.dom = Jsoup.connect(url).get();
    }

    private Elements getTraitLists(){
        return this.dom.getElementsByTag("ol");
    }

    private List<String> getNamesFromEnum(){
        String[] names = Arrays.stream(((Class<? extends Enum<?>>) TraitTypes.class)
                .getEnumConstants()).map(Enum::name).toArray(String[]::new);
        return Arrays.asList(names);
    }

    private Elements setClassesToLists(){
        List<String> traitsType = getNamesFromEnum();
        Elements traitsListWithClass = getTraitLists();
        IntStream.range(0, traitsListWithClass.size())
                .forEach(index ->
                        traitsListWithClass.get(index)
                                .addClass(traitsType.get(index)));
        return traitsListWithClass;
    }

    private Elements getFormattedListName(){
        Elements formattedLists = setClassesToLists();
        formattedLists.forEach(ol -> ol.children().forEach(
                li -> {
                    li.text(li.text().strip());
                    li.addClass(
                            li.text().toLowerCase().replaceAll("[^a-zA-Z0-9]","_"));
                }));
        return formattedLists;
    }

    private boolean isSameInDatabase(Stream<TraitEntity> traitsFromDB, String traitName){
        if(traitsFromDB != null) return traitsFromDB.anyMatch(traitFromDB->
                traitFromDB.getTraitName().equals(traitName));
        else return false;
    }

    public void saveTraitsToDatabase(){
        Elements traitsLists = getFormattedListName();
        List<TraitEntity> traitsFromDB = getTraitEntityRepository().findAll();
        traitsLists.forEach(ol -> ol.children().forEach(
                li -> {
                    TraitEntity trait = new TraitEntity
                        (li.text(), li.className(), TraitTypes.valueOf(li.parent().className()));
                    if(!isSameInDatabase(traitsFromDB.stream(), trait.getTraitName()))
                        getTraitEntityRepository().save(trait);
                }
        ));
    }
}
