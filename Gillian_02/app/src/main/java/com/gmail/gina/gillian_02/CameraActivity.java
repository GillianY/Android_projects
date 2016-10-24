package com.gmail.gina.gillian_02;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.*;

public class CameraActivity extends AppCompatActivity {

    private final String tag = "VideoServer";
    private Button start, stop;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
   // private Camera camera;
    private CameraManager mCameraManager;
    private Handler mHandler;

    private CameraDevice.StateCallback DeviceStateCallback;
    private CameraDevice mCameraDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    /*
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // your code using Camera API here - is between 1-20
        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // your code using Camera2 API here - is api 21 or higher
        }
*/
      //  CameraCharacteristics characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        initView();
        initHandler();


    }

    private void initView() {

        start = (Button)findViewById(R.id.btn_start);
        stop = (Button) findViewById(R.id.btn_stop);
        mCameraManager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
        mSurfaceView = (SurfaceView)findViewById(R.id.surfaceView1);

    }
    private void initHandler() {
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initCameraAndPreview();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });

        DeviceStateCallback = new CameraDevice.StateCallback() {

            @Override
            public void onOpened(CameraDevice camera) {
                Log.d("linc","DeviceStateCallback:camera was opend.");
                mCameraOpenCloseLock.release();
                mCameraDevice = camera;
                try {
                    createCameraCaptureSession();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }  @Override
            public void onDisconnected(CameraDevice camera) {

            }

            @Override
            public void onError(CameraDevice camera, int error) {

            }
        };



            start.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                start_camera();
            }
        });

        stop.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View arg0) {
                stop_camera();
            }
        });




    }

    private void createCameraCaptureSession() {
    }

    private void initCameraAndPreview() {
        Log.d("lc","init camera and preview");
        HandlerThread handlerThread = new HandlerThread("Camera2");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
        try {
           String mCameraId = ""+CameraCharacteristics.LENS_FACING_FRONT;
            ImageReader mImageReader = ImageReader.newInstance(mSurfaceView.getWidth(), mSurfaceView.getHeight(),
                    ImageFormat.JPEG,/*maxImages*/7);
            ImageReader.OnImageAvailableListener mOnImageAvailableListener =new ImageReader.OnImageAvailableListener(){
                @Override
                public void onImageAvailable(ImageReader reader) {

                }
            };
            mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mHandler);

            mCameraManager.openCamera(mCameraId, DeviceStateCallback, mHandler);
        } catch (CameraAccessException e) {
            Log.e("linc", "open camera failed." + e.getMessage());
        }
    }




    private void stop_camera() {
    }

    private void start_camera() {
    }




}
