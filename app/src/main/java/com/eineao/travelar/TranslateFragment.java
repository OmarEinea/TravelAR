package com.eineao.travelar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateFragment extends Fragment {

    private ArrayAdapter<String> mLanguagesAdapter, mAnnouncementAdapter;
    private String[] mLanguages, mAnnouncement, mTimes;
    private ListView mAnnouncementsList;
    private Spinner mSpinner;
    private View mFragment;

    public TranslateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragment = inflater.inflate(R.layout.fragment_translate, container, false);

        mLanguages = new String[]{
                "English", "简体中文", "Français", "Deutsch", "日本語", "Español", "العربية",
                "Български", "Català", "Hrvatski", "Čeština", "Suomi", "Ελληνικά", "עברית",
                "Magyar", "Bahasa Indonesia", "Italiano", "Polski", "Português", "Română",
                "Русский", "Svenska", "ภาษาไทย", "Türkçe", "Tiếng Việt"
        };
        mAnnouncement = new String[]{
                "Good morning. This is an announcement for all passengers travelling on the 10:25" +
                "flight TOM1223 to Rome. This flight is delayed by two hours because of bad weather.",
                "This is an announcement for passengers travelling to Rome on flight TOM1223." +
                "The gate of your flight was changed to gate 14.", "Would all passengers" +
                "travelling to Rome on flight TOM1223 please have your boarding passes and" +
                "passports ready for boarding. Flight TOM1223 now boarding at gate 14.",
                "This is an announcement for passengers travelling to Rome on flight TOM1223." +
                "Will all passengers with express boarding tickets and passengers travelling" +
                "with young children please go to gate 14 for boarding.",
                "This is the final boarding call for passengers travelling to Rome on flight TOM1223.",
                "all passengers travelling to Rome on flight TOM1223 please go to gate 14" +
                "immediately. The doors of the plane will close in five minutes."
        };
        mTimes = new String[]{"9:57 AM", "11:32 AM", "11:38 AM", "11:51 AM", "12:15 PM", "12:23 PM"};

        mLanguagesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mLanguages);
        mLanguagesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner = (Spinner) mFragment.findViewById(R.id.spinner);
        mSpinner.setAdapter(mLanguagesAdapter);

        mAnnouncementAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_view, R.id.title, mAnnouncement) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.title);
                TextView text2 = (TextView) view.findViewById(R.id.subtitle);
                text1.setText(mAnnouncement[position]);
                text2.setText(mTimes[position]);
                return view;
            }
        };

        mAnnouncementsList = (ListView) mFragment.findViewById(R.id.announcements);
        mAnnouncementsList.setAdapter(mAnnouncementAdapter);
        mAnnouncementsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Playing Announcement", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return mFragment;
    }

}
