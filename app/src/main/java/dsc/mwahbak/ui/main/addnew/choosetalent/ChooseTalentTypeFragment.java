package dsc.mwahbak.ui.main.addnew.choosetalent;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dsc.mwahbak.R;
import dsc.mwahbak.ui.main.addnew.choosemedia.ChooseMediaTypeFragment;
import dsc.mwahbak.ui.main.addnew.uploadtalent.TalentTypeModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseTalentTypeFragment extends Fragment {
    public static final String TAG = "ChooseTalentTypeFragment";

    ArrayList<TalentTypeModel> talentTypeModels ;
    ChooseTalentAdapter chooseTalentAdapter;

    public ChooseTalentTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        talentTypeModels = new ArrayList<>();

        talentTypeModels.add(new TalentTypeModel("1","1" , "1"));
        talentTypeModels.add(new TalentTypeModel("2","2" , "2"));
        talentTypeModels.add(new TalentTypeModel("3","3" , "3"));
        talentTypeModels.add(new TalentTypeModel("1","1" , "1"));
        talentTypeModels.add(new TalentTypeModel("2","2" , "2"));
        talentTypeModels.add(new TalentTypeModel("9","9" , "9"));
        talentTypeModels.add(new TalentTypeModel("5","5" , "5"));
        chooseTalentAdapter = new ChooseTalentAdapter(getActivity() , talentTypeModels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_choose_talent_type, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.talentTypeRecycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(chooseTalentAdapter);

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
