package dsc.mwahbak.ui.main.addnew.uploadtalent;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dsc.mwahbak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadTalentFragment extends Fragment {
    public static final String TAG = "UploadTalentFragment";

    public UploadTalentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_talent, container, false);
        TextView textView = (TextView) view.findViewById(R.id.third);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }

}
