package org.ktachibana.cloudemoji.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.ktachibana.cloudemoji.R;
import org.ktachibana.cloudemoji.events.EmoticonCopiedEvent;
import org.ktachibana.cloudemoji.models.Favorite;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class FavoriteFragment extends Fragment {
    @InjectView(R.id.favoriteListView)
    ListView mFavoriteListView;

    @InjectView(R.id.favoriteEmptyView)
    RelativeLayout mFavoriteEmptyView;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Setup views
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.inject(this, rootView);

        // Setup contents
        mFavoriteListView.setEmptyView(mFavoriteEmptyView);
        List<Favorite> favorites = Favorite.listAll(Favorite.class);
        final List<String> strings = new ArrayList<String>();
        for (Favorite favorite : favorites) {
            strings.add(favorite.getEmoticon() + " " + favorite.getDescription());
        }
        mFavoriteListView
                .setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings));
        mFavoriteListView
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        EventBus.getDefault().post(new EmoticonCopiedEvent(strings.get(i)));
                    }
                });
        return rootView;
    }


}