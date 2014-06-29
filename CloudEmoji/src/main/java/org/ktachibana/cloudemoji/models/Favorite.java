package org.ktachibana.cloudemoji.models;

import android.content.Context;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import org.ktachibana.cloudemoji.Constants;

import java.util.List;

/**
 * POJO class holding an entry with arrangingOrder
 */
public class Favorite extends SugarRecord<Favorite> implements Constants {
    private String emoticon;
    private String description;

    public Favorite(Context context) {
        super(context);
    }

    public Favorite(Context context, String emoticon, String description) {
        super(context);
        this.emoticon = emoticon;
        this.description = description;
    }

    public static List<Favorite> queryByEmoticon(String queriedEmoticon) {
        return Favorite.find(
                Favorite.class,
                FIND_BY_EMOTICON_SQL_CLAUSE,
                new String[]{queriedEmoticon});
    }

    public String getEmoticon() {
        return emoticon;
    }

    public String getDescription() {
        return description;
    }

}
