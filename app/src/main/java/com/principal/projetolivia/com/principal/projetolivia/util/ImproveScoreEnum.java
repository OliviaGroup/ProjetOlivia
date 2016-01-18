package com.principal.projetolivia.com.principal.projetolivia.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by roosq on 13/01/2016.
 */
public enum ImproveScoreEnum {
    better,
    equal,
    worse;

    public String getLabel(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier("score_" + this.name(), "string", context.getPackageName());
        if (0 != resId) {
            return (res.getString(resId));
        }
        return (name());
    }

    public int getImageArrowId (Context context) {
        Resources res = context.getResources();

        int resId = res.getIdentifier("arrow_" + this.name(), "drawable", context.getPackageName());

        if (0 != resId) {
            return resId;
        }

        return res.getIdentifier("error_button", "drawable", context.getPackageName());
    }
}
