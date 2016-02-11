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

    public UserStory() {
        stage = 0;

        medalLevelList = new ArrayList<>();
        for (int i = 0; i < SubjectEnum.values().length; i++) {
            medalLevelList.add(MedalLevelEnum.none);
        }
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
}
