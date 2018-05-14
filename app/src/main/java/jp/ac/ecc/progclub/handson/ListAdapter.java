package jp.ac.ecc.progclub.handson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jp.ac.ecc.progclub.handson.R;
import jp.ac.ecc.progclub.handson.User;

public class ListAdapter extends ArrayAdapter<User> {
    private TextView nameText;
    private TextView pointText;

    private LayoutInflater mInflater;

    public ListAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.ranklist, null);
        }
        final User item = this.getItem(position);
        if (item != null) {
            nameText = (TextView) convertView.findViewById(R.id.name);
            nameText.setText(item.getName());
            pointText = (TextView) convertView.findViewById(R.id.point);
            pointText.setText(String.valueOf(item.getTappoint()));
        }
        return convertView;
    }

}
