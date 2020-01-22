package dsc.mwahbak.ui.main.home.adapter;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import dsc.mwahbak.R;
import dsc.mwahbak.models.MediaModel;

/**
 * Created on : May 24, 2019
 * Author     : AndroidWave
 */
public class PlayerViewHolder extends RecyclerView.ViewHolder {
  private static final String TAG = "PlayerViewHolder";
  /**
   * below view have public modifier because
   * we have access PlayerViewHolder inside the ExoPlayerRecyclerView
   */
  public FrameLayout mediaContainer;
  public ImageView mediaCoverImage, volumeControl;
  public ProgressBar progressBar;
  public RequestManager requestManager;
  private TextView title, userHandle;
  private View parent;
  private PlayerView videoSurfaceView;
  private SimpleExoPlayer videoPlayer;


  public PlayerViewHolder(@NonNull View itemView) {
    super(itemView);
    parent = itemView;
    videoSurfaceView = itemView.findViewById(R.id.ep_video_view);
    mediaContainer = itemView.findViewById(R.id.mediaContainer);
    mediaCoverImage = itemView.findViewById(R.id.ivMediaCoverImage);
    title = itemView.findViewById(R.id.tvTitle);
    userHandle = itemView.findViewById(R.id.tvUserHandle);
    progressBar = itemView.findViewById(R.id.progressBar);
    volumeControl = itemView.findViewById(R.id.ivVolumeControl);
  }

  void onBind(MediaModel mediaObject, RequestManager requestManager , Context context) {



    this.requestManager = requestManager;
    parent.setTag(this);
    title.setText(mediaObject.getDescription());
    userHandle.setText(mediaObject.getUserId());
    this.requestManager
        .load(mediaObject.getUser().getImage())
        .into(mediaCoverImage);


    videoSurfaceView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    TrackSelection.Factory videoTrackSelectionFactory =
            new AdaptiveTrackSelection.Factory(bandwidthMeter);
    TrackSelector trackSelector =
            new DefaultTrackSelector(videoTrackSelectionFactory);

    //Create the player using ExoPlayerFactory
    videoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
    // Disable Player Control
    videoSurfaceView.setUseController(true);
    // Bind the player to the view.
    videoSurfaceView.setPlayer(videoPlayer);

    DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
            context, Util.getUserAgent(context, "Mwahbak"));

    MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(mediaObject.getPath()));
    videoPlayer.prepare(videoSource);
    videoPlayer.setPlayWhenReady(true);

    videoPlayer.addListener(new Player.EventListener() {
      @Override
      public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

      }

      @Override
      public void onTracksChanged(TrackGroupArray trackGroups,
                                  TrackSelectionArray trackSelections) {

      }

      @Override
      public void onLoadingChanged(boolean isLoading) {

      }

      @Override
      public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {

          case Player.STATE_BUFFERING:
            Log.e(TAG, "onPlayerStateChanged: Buffering video.");
            if (progressBar != null) {
              progressBar.setVisibility(View.VISIBLE);
            }

            break;
          case Player.STATE_ENDED:
            Log.d(TAG, "onPlayerStateChanged: Video ended.");
            videoPlayer.seekTo(0);
            break;
          case Player.STATE_IDLE:

            break;
          case Player.STATE_READY:
            Log.e(TAG, "onPlayerStateChanged: Ready to play.");
            if (progressBar != null) {
              progressBar.setVisibility(View.GONE);
            }

            break;
          default:
            break;
        }
      }

      @Override
      public void onRepeatModeChanged(int repeatMode) {

      }

      @Override
      public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

      }

      @Override
      public void onPlayerError(ExoPlaybackException error) {

      }

      @Override
      public void onPositionDiscontinuity(int reason) {

      }

      @Override
      public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

      }

      @Override
      public void onSeekProcessed() {

      }
    });

  }
}

