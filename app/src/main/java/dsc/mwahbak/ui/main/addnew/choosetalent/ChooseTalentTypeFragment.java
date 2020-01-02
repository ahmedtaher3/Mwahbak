package dsc.mwahbak.ui.main.addnew.choosetalent;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
public class ChooseTalentTypeFragment extends Fragment implements ChooseTalentAdapter.AdapterClick{
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

        talentTypeModels.add(new TalentTypeModel("https://res.cloudinary.com/dcpvhccpi/image/upload/v1577980508/electric-guitar.png",1 , "Singing"));
        talentTypeModels.add(new TalentTypeModel("https://res.cloudinary.com/dcpvhccpi/image/upload/v1577980508/clapperboard.png",2 , "Acting"));
        talentTypeModels.add(new TalentTypeModel("https://res.cloudinary.com/dcpvhccpi/image/upload/v1577980508/paint.png",3 , "Drawing"));
        talentTypeModels.add(new TalentTypeModel("https://res.cloudinary.com/dcpvhccpi/image/upload/v1577980508/soccer-ball.png",4 , "FootBall"));
        talentTypeModels.add(new TalentTypeModel("https://res.cloudinary.com/dcpvhccpi/image/upload/v1577980508/polaroid.png",5 , "Photographing"));
        talentTypeModels.add(new TalentTypeModel("https://res.cloudinary.com/dcpvhccpi/image/upload/v1577980509/on-air.png",6 , "Other"));
         chooseTalentAdapter = new ChooseTalentAdapter(getActivity() , talentTypeModels , this::itemClick);
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

    public void replaceFragmentWithAnimation(Fragment fragment, String tag , int typeID){
        Bundle bundle=new Bundle();
        bundle.putInt("TalentType",typeID );
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.Container, fragment);
        transaction
                .addToBackStack(tag);

        transaction.commit();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void itemClick(int typeID) {
        Log.d(TAG, "itemClick: done");
       // replaceFragmentWithAnimation(new ChooseMediaTypeFragment() , ChooseMediaTypeFragment.TAG , typeID);

    }
}
