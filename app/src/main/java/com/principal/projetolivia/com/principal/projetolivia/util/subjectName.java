package com.principal.projetolivia.com.principal.projetolivia.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by roosq on 03/12/2015.
 */
public enum subjectName {
    mathematics,
    informatics,
    physics,
    chemistry,
    biology,
    astronomy;

    public String getLabel(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(this.name(), "string", context.getPackageName());
        if (0 != resId) {
            return (res.getString(resId));
        }
        return (name());
    }
}
