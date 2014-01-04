package org.ktachibana.cloudemoji;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Adapter that holds a list of simple_list_item_2 text views
 */
public class DoubleItemListAdapter implements ListAdapter {

    private LayoutInflater inflater;
    private RepoXmlParser.Category cat;

    public DoubleItemListAdapter(Context context, RepoXmlParser.Category cat) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cat = cat;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return cat.entries.size();
    }

    @Override
    public Object getItem(int position) {
        return cat.entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }
        TextView lineOne = (TextView) view.findViewById(android.R.id.text1);
        TextView lineTwo = (TextView) view.findViewById(android.R.id.text2);
        RepoXmlParser.Entry entry = cat.entries.get(position);
        lineOne.setText(entry.string);
        lineTwo.setText(entry.note);
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return cat.entries.isEmpty();
    }
}