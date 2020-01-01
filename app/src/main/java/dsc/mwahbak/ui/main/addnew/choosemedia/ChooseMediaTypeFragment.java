package dsc.mwahbak.ui.main.addnew.choosemedia;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dsc.mwahbak.R;
import dsc.mwahbak.ui.main.addnew.uploadtalent.UploadTalentFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMediaTypeFragment extends Fragment {
    public static final String TAG = "ChooseMediaTypeFragment";

    public ChooseMediaTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_media_type, container, false);
        TextView textView = (TextView) view.findViewById(R.id.second);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentWithAnimation(new UploadTalentFragment() , UploadTalentFragment.TAG);
            }
        });
        return view;
    }
    public void replaceFragmentWithAnimation(Fragment fragment, String tag){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.Container, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }
}
