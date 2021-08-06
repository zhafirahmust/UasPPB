package com.example.uas_ppb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DashboardFragment extends Fragment {
    private static final int TEXT_REQUEST = 1;
    private EditText mWebsiteEditText, mCallingEditText, mShareTextEditText;
    public static final String EXTRA_MESSAGE = "com.e.android.twoactivities.extra.MESSAGE";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 1;
    Button share_text_button,open_website_button,call_button;
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mWebsiteEditText = (EditText) view.findViewById(R.id.website_edittext);
        mCallingEditText = (EditText) view.findViewById(R.id.calling_edittext);
        mShareTextEditText = (EditText) view.findViewById(R.id.share_edittext);
        share_text_button = view.findViewById(R.id.share_text_button);
        open_website_button = view.findViewById(R.id.open_website_button);
        call_button = view.findViewById(R.id.call_button);

        share_text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = mShareTextEditText.getText().toString();
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(getActivity())
                        .setType(mimeType)
                        .setChooserTitle("Share this text with: ")
                        .setText(txt)
                        .startChooser();
            }
        });
        open_website_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mWebsiteEditText.getText().toString();
                Uri webpage = Uri.parse(url);

                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(intent);
                }else {
                    Log.d("Implicit Intents", "Cant't handle this!");
                }
            }
        });
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = mCallingEditText.getText().toString();
                Uri phone = Uri.parse("tel:" + phoneNumber);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL, phone);
                    if (callIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(callIntent);
                    }else {
                        Log.d("Implicit Intents", "Cant't handle this!");
                    }
                }
            }
        });
        return view;
    }
}