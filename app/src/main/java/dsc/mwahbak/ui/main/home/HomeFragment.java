package dsc.mwahbak.ui.main.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dsc.mwahbak.R;
import dsc.mwahbak.base.BaseApplication;
import dsc.mwahbak.data.DataManager;
import dsc.mwahbak.models.MediaModel;
import dsc.mwahbak.models.User;
import dsc.mwahbak.network.ApiResponse;
import dsc.mwahbak.network.GetDataService;
import dsc.mwahbak.network.RetrofitClientInstance;
import dsc.mwahbak.ui.main.home.adapter.MediaRecyclerAdapter;
import dsc.mwahbak.util.DividerItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnPreparedListener {
    private static final String TAG = "HomeFragment";

    private GetDataService dataService;
    private DataManager dataManager;
    private User user;

    private RecyclerView mRecyclerView;

    private ArrayList<MediaModel> mediaObjectList = new ArrayList<>();
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;

    private View view = null;
    private Context mContext;
    private VideoView videoView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        dataManager = ((BaseApplication) getActivity().getApplication()).getDataManager();

        Gson gson = new Gson();
        String json =  dataManager.getUser();
        user = gson.fromJson(json, User.class);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.exoPlayerRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        prepareVideoList();




        return view;
    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }



    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
         if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onPrepared() {
        videoView.start();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void prepareVideoList() {

        Map<String, String> parms = new HashMap<>();
        parms.put("user_id", String.valueOf(user.getId()));
        parms.put("cat_id", "2");
        Call<ApiResponse> data = dataService.get_posts(parms);
        data.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.w("gson => ", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                mediaObjectList.addAll(response.body().getMediaModels());


                //set data object

                mAdapter = new MediaRecyclerAdapter(getActivity() , mediaObjectList, initGlide());

                //Set Adapter
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        });



    }



}
