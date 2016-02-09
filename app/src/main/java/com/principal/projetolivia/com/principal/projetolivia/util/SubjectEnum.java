package com.principal.projetolivia.com.principal.projetolivia.util;

import android.content.Context;
import android.content.res.Resources;

import com.principal.projetolivia.main.MainActivity;

/**
 * Created by roosq on 03/12/2015.
 */
public enum SubjectEnum {
    biology,
    mathematics,
    physics,
    informatics,
    chemistry,
    astronomy;

    public String getLabel(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(this.name(), "string", context.getPackageName());
        if (0 != resId) {
            return (res.getString(resId));
        }
        return (name());
    }

    public int getImageResourceId (Context context) {
        Resources res = context.getResources();

        int resId = res.getIdentifier(this.name() + "_button", "drawable", context.getPackageName());

        if (0 != resId) {
            return resId;
        }

        return res.getIdentifier("error_button", "drawable", context.getPackageName());
    }

    public int getImageQuestionId (Context context) {
        Resources res = context.getResources();

        int resId = res.getIdentifier("fond_" + this.name() + "_questions", "drawable", context.getPackageName());

        if (0 != resId) {
            return resId;
        }

        return res.getIdentifier("error_button", "drawable", context.getPackageName());
    }

    public int getImageOliviaId (Context context) {
        Resources res = context.getResources();

        int resId = res.getIdentifier("olivia_"+this.name(), "drawable", context.getPackageName());

        if (0 != resId) {
            return resId;
        }

        return res.getIdentifier("olivia_simple", "drawable", context.getPackageName());
    }

}
