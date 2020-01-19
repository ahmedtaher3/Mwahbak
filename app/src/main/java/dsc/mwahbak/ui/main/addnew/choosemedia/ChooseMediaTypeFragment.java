package dsc.mwahbak.ui.main.addnew.choosemedia;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dsc.mwahbak.R;
import dsc.mwahbak.models.MediaTypeObject;
import dsc.mwahbak.ui.main.addnew.uploadtalent.UploadTalentFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMediaTypeFragment extends Fragment implements ChooseMediaAdapter.AdapterClick {
    public static final String TAG = "ChooseMediaTypeFragment";
    private String mediaType;
    ArrayList<MediaTypeObject> mediaTypeObjects;
    ChooseMediaAdapter chooseTalentAdapter;

    public ChooseMediaTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaTypeObjects = new ArrayList<>();

        mediaTypeObjects.add(new MediaTypeObject("https://res.cloudinary.com/dcpvhccpi/image/upload/v1578400734/ic_camera.png", 1, "Video"));
        mediaTypeObjects.add(new MediaTypeObject("https://res.cloudinary.com/dcpvhccpi/image/upload/v1578400734/ic_gallery.png", 2, "Image"));
        mediaTypeObjects.add(new MediaTypeObject("https://res.cloudinary.com/dcpvhccpi/image/upload/v1578400734/ic_mic.png", 3, "Record"));
        chooseTalentAdapter = new ChooseMediaAdapter(getActivity(), mediaTypeObjects, this::itemClick);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_media_type, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mediaTypeRecycler);
        Button next = (Button) view.findViewById(R.id.next_btn);
        Button back = (Button) view.findViewById(R.id.back_btn);


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(chooseTalentAdapter);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragmentWithAnimation(new UploadTalentFragment(), UploadTalentFragment.TAG, mediaType , getArguments().getInt("TalentType"));

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

    public void replaceFragmentWithAnimation(Fragment fragment, String tag, String mediaType, int catID) {
        Bundle bundle = new Bundle();
        bundle.putString("MediaType", mediaType);
        bundle.putInt("TalentType", catID);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.Container, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void itemClick(String mediaType) {
        this.mediaType = mediaType;
    }
}
