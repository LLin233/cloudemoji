package org.ktachibana.cloudemoji.net;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.ktachibana.cloudemoji.BaseHttpClient;
import org.ktachibana.cloudemoji.Constants;

/**
 * Check for update
 */
public class UpdateChecker extends BaseHttpClient implements Constants {
    public void checkForLatestVersionCode(@NonNull final IntCallback callback) {
        mClient.get(UPDATE_CHECKER_URL, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.finish(false, 0);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                JsonObject object = (JsonObject) new JsonParser().parse(responseString);
                int versionCode = object.get("version").getAsInt();
                callback.finish(true, versionCode);
            }
        });
    }
}
