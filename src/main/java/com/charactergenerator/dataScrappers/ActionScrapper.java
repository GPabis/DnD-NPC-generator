package com.charactergenerator.dataScrappers;
import com.charactergenerator.entities.actionEntity.ActionEntity;
import com.charactergenerator.entities.actionEntity.ActionEntityRepository;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class ActionScrapper {
    private static final String url = "https://7esl.com/english-verbs/";
    private final ActionEntityRepository actionEntityRepository;
    private Document dom;

    public ActionScrapper(ActionEntityRepository actionEntityRepository) throws IOException {
        this.actionEntityRepository = actionEntityRepository;
        this.dom = Jsoup.connect(url).get();
    }

    private Elements getActionLists(){
        Elements actionLists = getDom().getElementsByClass("thecontent")
                .get(0).getElementsByTag("ul");
        Element a = new Element("<p>");
        actionLists.subList(4, 23).stream().map(Element::children).forEach(l -> a.append(l.html()));
        return setEncodedNameAsAClass(a.children());
    }

   private Elements setEncodedNameAsAClass(Elements actionList){
       actionList.forEach(action -> action.addClass(action.text().toLowerCase()
               .replaceAll("[^a-zA-Z0-9]","_")));
       return actionList;
    }

    private boolean isSameActionInDatabase(Stream<ActionEntity> ActionFromDB, String actionName){
        if(ActionFromDB != null) return ActionFromDB.anyMatch(actionFromDB->
                actionFromDB.getActionName().equals(actionName));
        else return false;
    }

    public void saveActionsToDatabase(){
        List<ActionEntity> actionEntityList = new ArrayList<>();
        List<ActionEntity> actionEntityFromDB = getActionEntityRepository().findAll();
        Elements actionList = getActionLists();
        actionList.forEach(action -> {
            ActionEntity newAction = new ActionEntity(action.text(), action.className());
            if(!isSameActionInDatabase(actionEntityFromDB.stream(), newAction.getActionName()))
                actionEntityList.add(newAction);
        });
        getActionEntityRepository().saveAll(actionEntityList);
    }

}
