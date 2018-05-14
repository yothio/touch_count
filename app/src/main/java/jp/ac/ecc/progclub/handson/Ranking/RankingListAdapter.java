package jp.ac.ecc.progclub.handson.Ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.ac.ecc.progclub.handson.R;

public class ListAdapter extends ArrayAdapter<User> {

    private TextView nameText;
    private TextView pointText;

    private LayoutInflater mInflater;

    public ListAdapter(Context context, List<User> users) {
        super(context, 0, users);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.ranklist, null);
        }
        final User user = this.getItem(position);
        if (user != null) {
            nameText = (TextView) convertView.findViewById(R.id.name);
            nameText.setText(user.getName());
            pointText = (TextView) convertView.findViewById(R.id.point);
            pointText.setText(String.valueOf(user.getTappoint()));
        }
        return convertView;
    }

}
