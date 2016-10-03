package com.eineao.travelar;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlightFragment extends Fragment {

    private ArrayAdapter<String> mAirlinesAdapter;
    private View mFragment, mProgress;
    private boolean mIdle = true;
    private String[] mAirlines;
    private EditText mEditText;
    private Spinner mSpinner;
    private TextView mInfo;

    public FlightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragment = inflater.inflate(R.layout.fragment_flight, container, false);

        mAirlines = new String[]{
                "Air Arabia", "Emirates", "Etihad Airways", "Flydubai", "Rotana Jet",
                "Emirates SkyCargo", "Etihad Cargo", "Maximus Air Cargo", "Unique Air",
                "Abu Dhabi Amiri Flight", "Dubai Royal Air Wing"
        };

        mAirlinesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mAirlines);
        mAirlinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner = (Spinner) mFragment.findViewById(R.id.airlines);
        mSpinner.setAdapter(mAirlinesAdapter);

        mProgress = mFragment.findViewById(R.id.progress);
        mInfo = (TextView) mFragment.findViewById(R.id.info);

        mEditText = (EditText) mFragment.findViewById(R.id.edit_text);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 7) {
                    if (mIdle) {
                        mIdle = false;
                        mProgress.setVisibility(View.VISIBLE);
                        mInfo.setText("Getting your Flight Gate Info...");
                        new CountDownTimer(3000, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                mProgress.setVisibility(View.GONE);
                                mInfo.setText("Your Flight is at Gate " + (new Random().nextInt(26) + 1));
                                mIdle = true;
                            }
                        }.start();
                    }
                } else {
                    mInfo.setText("Make Sure your Ticket Number is Correct");
                    mProgress.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mFragment.findViewById(R.id.gate_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText.getText().length() > 7)
                    Toast.makeText(v.getContext(), "Starting Navigation...", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(v.getContext(), "Please Enter a Valid Ticket Number", Toast.LENGTH_SHORT).show();
            }
        });

        mFragment.findViewById(R.id.checkin_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Starting Navigation...", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return mFragment;
    }

}
