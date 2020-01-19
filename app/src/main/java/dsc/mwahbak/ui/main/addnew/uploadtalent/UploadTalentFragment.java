package dsc.mwahbak.ui.main.addnew.uploadtalent;


import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;


import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;
import dsc.mwahbak.R;
import dsc.mwahbak.base.BaseApplication;
import dsc.mwahbak.data.DataManager;
import dsc.mwahbak.models.User;
import dsc.mwahbak.network.ApiResponse;
import dsc.mwahbak.network.Data;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadTalentFragment extends Fragment {
    public static final String TAG = "UploadTalentFragment";

    File file;
    String filePath;

    ImageView add_media;

    DataManager dataManager ;

    User user ;

    String FLAG;
    String[] permissions = {
            "android.permission.RECORD_AUDIO",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.CAMERA"

    };

    public UploadTalentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataManager = ((BaseApplication) getActivity().getApplication()).getDataManager();

        Gson gson = new Gson();
        String json =  dataManager.getUser();
        user = gson.fromJson(json, User.class);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_talent, container, false);






        add_media = (ImageView) view.findViewById(R.id.add_media);
        FLAG = getArguments().getString("MediaType");

        if (checkPermissions()) {


            pick_media(FLAG);


        } else {
            requestPermissions(permissions, 100);
        }


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1000) {
            Log.d(TAG, "onActivityResult: ");

            if (resultCode == RESULT_OK) {


                file = new File(filePath);

                upload_file(user.getApiToken() , file);

                Log.d(TAG, "onActivityResult: " + file.getAbsolutePath());


                MediaPlayer mp = new MediaPlayer();

                try {
                    mp.setDataSource(file.getPath() + File.separator + "");
                    mp.prepare();
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (resultCode == RESULT_CANCELED) {
                // Oops! User has canceled the recording
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onActivityResult: ");
            }
        } else if (requestCode == 2000) {

            file = new File(Objects.requireNonNull(getPathFromUri(getActivity(), data.getData())));


            upload_file(user.getApiToken() , file);


        } else if (requestCode == 3000) {


            file = new File(Objects.requireNonNull(getPathFromUri(getActivity(), data.getData())));

            if (file.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());


                add_media.setImageBitmap(myBitmap);

            }

            Log.d(TAG, "onActivityResult: " + user.getApiToken());
            Log.d(TAG, "onActivityResult: " + file.getPath());


            upload_file(user.getApiToken() , file);


        }
    }

    boolean checkPermissions() {
        int RECORD_AUDIO = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        int CAMERA = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);

        return RECORD_AUDIO == PackageManager.PERMISSION_GRANTED && WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED && READ_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED&& CAMERA == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(getActivity(), "onRequestPermissionsResult", Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case 100:


        }
    }

    public void pick_media(String s) {
        switch (s) {
            case "Video":


           /*  Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
             fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
             Toast.makeText(getApplicationContext(), fileUri.toString(), Toast.LENGTH_LONG).show();
             intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

              long maxVideoSize = 10*1024*1024; // 10 MB
             intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, maxVideoSize);

             intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
             startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);*/

                Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, 0);
                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 5);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                startActivityForResult(intent, 2000);

                break;
            case "Image":


                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 3000);
                break;
            case "Record":
                filePath = Environment.getExternalStorageDirectory() + "/recorded_audio.wav";
                int color = getResources().getColor(R.color.colorPrimaryDark);
                int requestCode = 1;
                AndroidAudioRecorder.with(this)
                        // Required
                        .setFilePath(filePath)
                        .setColor(color)
                        .setRequestCode(1000)

                        // Optional
                        .setSource(AudioSource.MIC)
                        .setChannel(AudioChannel.STEREO)
                        .setSampleRate(AudioSampleRate.HZ_48000)
                        .setAutoStart(true)
                        .setKeepDisplayOn(true)

                        // Start recording
                        .recordFromFragment();
                break;
        }

    }


    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    KProgressHUD blg ;

    void upload_file(String id , File file) {
        blg = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();


        AndroidNetworking.upload("http://talent.promo-sys.com/api/upload")
                .addMultipartFile("file", file)
                .addMultipartParameter("api_token", id)
                .addMultipartParameter("cat_id", "2")
                .addMultipartParameter("description", "description2")

                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                        float f = ((float) bytesUploaded / (float) totalBytes) * 100;
                        int test = Math.round(f);
                        //    blg.setLabel(String.valueOf(test) + "%");

                        Log.i("Uploading ... =", String.valueOf(test) + "%");

                        blg.setLabel(String.valueOf(test) + "%");

                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new Gson();
                        ApiResponse apiResponse = gson.fromJson(response.toString(), ApiResponse.class);

                        if (apiResponse.getStatus()) {

                            blg.dismiss();
                            Log.i("Uploading ... =", "Successful");

                        } else {
                            blg.dismiss();
                        }


                    }

                    @Override
                    public void onError(ANError error) {

                        blg.dismiss();

                        Log.d(TAG, "onError: " + error.getErrorBody());
                    }
                });

    }


}
