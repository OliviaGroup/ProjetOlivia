package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roosq on 08/02/2016.
 */
public class UserStory implements Serializable {
    private int stage;
    private List<MedalLevelEnum> medalLevelList;
    private List<MarginOlivia> marginOliviaList;

    public UserStory() {
        stage = 0;

        medalLevelList = new ArrayList<>();
        marginOliviaList = new ArrayList<>();
        for (int i = 0; i < SubjectEnum.values().length; i++) {
            medalLevelList.add(MedalLevelEnum.none);
        }

        marginOliviaList.add(new MarginOlivia(380, 430));
        marginOliviaList.add(new MarginOlivia(370, 100));
        marginOliviaList.add(new MarginOlivia(600, 180));
        marginOliviaList.add(new MarginOlivia(890, 130));
        marginOliviaList.add(new MarginOlivia(810, 280));
        marginOliviaList.add(new MarginOlivia(760, 470));
    }


    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public List<MedalLevelEnum> getMedalLevelList() {
        return medalLevelList;
    }

    public void setMedalLevelList(List<MedalLevelEnum> medalLeveList) {
        this.medalLevelList = medalLeveList;
    }

    public List<MarginOlivia> getMarginOliviaList() {
        return marginOliviaList;
    }

    public void setMarginOliviaList(List<MarginOlivia> marginOliviaList) {
        this.marginOliviaList = marginOliviaList;
    }
}
