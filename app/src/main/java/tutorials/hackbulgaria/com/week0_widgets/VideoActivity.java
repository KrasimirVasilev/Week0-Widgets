package tutorials.hackbulgaria.com.week0_widgets;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;


public class VideoActivity extends Activity implements View.OnClickListener {

    private VideoView videoView;
    private Button prevButton;
    private Button playButton;
    private Button nextButton;

    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = (VideoView) findViewById(R.id.videoView);
        prevButton = (Button) findViewById(R.id.prevButton);
        playButton = (Button) findViewById(R.id.playButton);
        nextButton = (Button) findViewById(R.id.nextButton);
    }

    @Override
    protected void onResume() {
        super.onResume();

        prevButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String videoPath = downloadsDir.getPath() + "/Ronaldo_Dive_Moti.mp4";
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.prevButton:
                videoView.seekTo(videoView.getCurrentPosition() - 3000);
                break;

            case R.id.playButton:
                if (isPlaying) {
                    videoView.pause();
                    playButton.setBackground(getResources().getDrawable(R.drawable.play));
                    isPlaying = false;
                } else {
                    videoView.start();
                    playButton.setBackground(getResources().getDrawable(R.drawable.pause));
                    isPlaying = true;
                }
                break;

            case R.id.nextButton:
                videoView.seekTo(videoView.getCurrentPosition() + 3000);
                break;

            default:
                break;

        }
    }
}
