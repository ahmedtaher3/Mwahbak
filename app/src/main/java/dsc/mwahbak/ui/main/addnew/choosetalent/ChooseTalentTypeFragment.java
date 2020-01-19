package dsc.mwahbak.ui.main.addnew.choosetalent;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import dsc.mwahbak.R;
import dsc.mwahbak.network.ApiResponse;
import dsc.mwahbak.network.GetDataService;
import dsc.mwahbak.network.RetrofitClientInstance;
import dsc.mwahbak.ui.main.addnew.choosemedia.ChooseMediaTypeFragment;
import dsc.mwahbak.models.TalentTypeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseTalentTypeFragment extends Fragment implements ChooseTalentAdapter.AdapterClick{
    public static final String TAG = "ChooseTalentTypeFragment";
    private int typeID = 0;
    ArrayList<TalentTypeModel> talentTypeModels ;
    ChooseTalentAdapter chooseTalentAdapter;
    GetDataService dataService;
    public ChooseTalentTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        talentTypeModels = new ArrayList<>();
         chooseTalentAdapter = new ChooseTalentAdapter(getActivity() , talentTypeModels , this::itemClick);
        dataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_choose_talent_type, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.talentTypeRecycler);
        Button next = (Button) view.findViewById(R.id.next_btn);
        Button back = (Button) view.findViewById(R.id.back_btn);


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(chooseTalentAdapter);
        get_item_family();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (typeID != 0) {
                    replaceFragmentWithAnimation(new ChooseMediaTypeFragment(), ChooseMediaTypeFragment.TAG, typeID);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

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
        this.typeID = typeID;

    }



    public void get_item_family() {

        Call<ApiResponse> data = dataService.get_cats();
        data.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.w("gson => ",new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                chooseTalentAdapter.setMy_data(response.body().getCatModels());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
             }
        });


    }
}
