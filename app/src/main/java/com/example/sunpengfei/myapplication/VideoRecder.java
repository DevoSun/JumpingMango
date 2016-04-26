
package com.example.sunpengfei.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;

import com.yixia.camera.MediaRecorderFilter;
import com.yixia.camera.model.MediaObject;
import com.yixia.camera.view.CameraNdkView;
import com.yixia.camera.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sunpengfei on 15/12/4.
 */
public class VideoRecder{

    private static final String TAG = "VideoRecder";

//    private MediaRecorder mediaRecorder;

    private CameraNdkView surfaceView;

    private MediaRecorderFilter mediaRecorder;

    private MediaObject mMediaObject;

    private String videoPath;

    private Context context;

    private int cameraPosition = 1;// 0 前置摄像头 1 后置摄像头

    private static final int CHANGE_CAMERA_STATUS = 1000;

    private Camera.Size videoSize;

    public static String DIR_APP_NAME;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
//                case CHANGE_CAMERA_STATUS:
//                    int cameraId = msg.arg1;
//                    try {
//                        camera = Camera.open(cameraId);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        freeCameraResource();
//                    }
//                    if (camera == null)
//                        return;
//
//                    setCameraParams();
//                    camera.setDisplayOrientation(90);
//                    try {
//                        camera.setPreviewDisplay(surfaceHolder);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    camera.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上
//                    camera.startPreview();
//                    camera.unlock();
//                    break;
            }
        }
    };

    public VideoRecder(CameraNdkView surfaceView, Context context) {
        this.surfaceView = surfaceView;
        this.context = context;
//        surfaceHolder = surfaceView.getHolder();
//        surfaceHolder.setKeepScreenOn(true);
//        surfaceHolder.addCallback(this);
        // File f = android.os.Environment.getExternalStorageDirectory();
        // DIR_APP_NAME = f.getAbsolutePath() + "/" + "Toon";
        initVideoPath();
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    private void initMediaRecord() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mediaRecorder == null) {
                        mediaRecorder = new MediaRecorderFilter();
                    } else {
                        mediaRecorder.setSurfaceHolder(surfaceView.getHolder());
                        mediaRecorder.prepare();
                    }
                    mediaRecorder.setOnErrorListener(new com.yixia.camera.MediaRecorder.OnErrorListener() {
                        @Override
                        public void onVideoError(int i, int i1) {

                        }

                        @Override
                        public void onAudioError(int i, String s) {

                        }
                    });

                    // Step 1: Unlock and set camera to MediaRecorder
                    mediaRecorder.setVideoBitRate(MediaRecorder.VIDEO_BITRATE_MEDIUM);

                    // Step 2: Set sources
                    mediaRecorder.setSurfaceView(surfaceView);// before
                    // setOutputFormat()
                    String key = String.valueOf(System.currentTimeMillis());
                    mMediaObject = mediaRecorder.setOutputDirectory(key, videoPath + key);
                    if (mMediaObject != null) {
                        mediaRecorder.prepare();
                        mediaRecorder.setCameraFilter(MediaRecorderFilter.CAMERA_FILTER_NO);
                    } else {
                        ((Activity)context).finish();
                    }
//                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);// before
//
//                    // 设置视频输出的格式和编码
//                    CamcorderProfile mProfile = null;
//                    if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_1080P)) {
//                        mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_1080P);
//                    } else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_720P)) {
//                        mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_720P);
//                    } else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_480P)) {
//                        mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
//                    } else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_HIGH)) {
//                        mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
//                    }
//                    if (mProfile != null) {
//                        mediaRecorder.setProfile(mProfile);
//                    }
//                    mediaRecorder.setVideoSize(640, 480);// after
//                    // setVideoSource(),after
//                    // setOutFormat()
//                    mediaRecorder.setAudioEncodingBitRate(44100);
//                    if (mProfile != null) {
//                        if (mProfile.videoBitRate > 2 * 1024 * 1024)
//                            mediaRecorder.setVideoEncodingBitRate(2 * 1024 * 1024);
//                        else
//                            mediaRecorder.setVideoEncodingBitRate(mProfile.videoBitRate);
//                        mediaRecorder.setVideoFrameRate(mProfile.videoFrameRate);// after
//                    }
//
//                    // Step 4: Set output file
//                    mediaRecorder.setOutputFile(videoPath);
//
//                    // Step 5: Set the preview output
//                    mediaRecorder.setVideoFrameRate(30);
//                    if (cameraPosition == 1) {
//                        mediaRecorder.setOrientationHint(90);// 输出旋转90度，保持竖屏录制
//                    } else {
//                        mediaRecorder.setOrientationHint(270);// 输出旋转90度，保持竖屏录制
//                    }
//
//                    mediaRecorder.prepare();
//                    mediaRecorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    // 切换前后置摄像头
    private void changeCameraStatus(int cameraId) throws IOException {
//        if (camera != null) {
//            freeCameraResource();
//        }
//        Log.e(TAG, "changeCameraStatus");
//        Message message = Message.obtain();
//        message.what = CHANGE_CAMERA_STATUS;
//        message.arg1 = cameraId;
//        handler.sendMessageDelayed(message, 500);
    }

    /**
     * 释放摄像头资源
     */
    private void freeCameraResource() {
//        if (camera != null) {
//            camera.setPreviewCallback(null);
//            camera.stopPreview();
//            camera.lock();
//            camera.release();
//            camera = null;
//        }
    }

    /**
     * 设置摄像头为竖屏
     */
    private void setCameraParams() {
//        if (camera != null) {
//            Camera.Parameters parameters = camera.getParameters();
//            if (cameraPosition == 1) {
//                parameters.setRotation(90);
//            } else {
//                parameters.setRotation(0);
//            }
//            List<String> focus = parameters.getSupportedFocusModes();
//            if (focus != null && focus.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
//                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);// 1连续对焦
//            }
//            getResolutionSize(parameters);
//            // parameters.setPreviewSize(surfaceView.getHeight(),
//            // surfaceView.getWidth());
//            camera.setParameters(parameters);
//        }
    }

//    // 停止录制视频
//    private void stopRecord() {
//        if (mediaRecorder != null) {
//            // 设置后不会崩
//            mediaRecorder.setOnErrorListener(null);
//            mediaRecorder.setPreviewDisplay(null);
//            try {
//                mediaRecorder.stop();
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            } catch (RuntimeException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void startRec() {
        initMediaRecord();
    }

    public void stopRec() throws IOException {
//        stopRecord();
//        initCamera();
    }

    // 切换前后置摄像头
    public void changeCamera() throws IOException {
        // 切换前后摄像头
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();// 得到摄像头的个数

        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);// 得到每一个摄像头的信息
            if (cameraPosition == 1) {
                // 现在是后置，变更为前置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {// 代表摄像头的方位，CAMERA_FACING_FRONT前置
                                                                                 // CAMERA_FACING_BACK后置
                    cameraPosition = 0;
                    changeCameraStatus(i);
                    break;
                }
            } else {
                // 现在是前置， 变更为后置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {// 代表摄像头的方位，CAMERA_FACING_FRONT前置
                                                                                // CAMERA_FACING_BACK后置
                    cameraPosition = 1;
                    changeCameraStatus(i);
                    break;
                }
            }
        }
    }

    /**
     * 返回一个适当的size
     *
     * @param parameters Camera.Parameters
     */
    private void getResolutionSize(Camera.Parameters parameters) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();

        if (supportedPreviewSizes != null && supportedPreviewSizes.size() > 0) {

            // 2.x
            videoSize = supportedPreviewSizes.get(0);

            for (Camera.Size size : supportedPreviewSizes) {
                if (size.height == surfaceView.getWidth()) {
                    videoSize = size;
                    break;
                }
            }

            parameters.setPreviewSize(videoSize.width, videoSize.height);

        }
    }

    private void initVideoPath() {
        try {
            File videoDir = new File(DIR_APP_NAME + "/video");
            File f = new File(videoDir, System.currentTimeMillis() + ".mp4");
            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            videoPath = f.getAbsolutePath();
        } catch (Exception e) {

        }
    }
}
