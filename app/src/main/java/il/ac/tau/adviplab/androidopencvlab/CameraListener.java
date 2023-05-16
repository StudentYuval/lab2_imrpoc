package il.ac.tau.adviplab.androidopencvlab;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;

class CameraListener implements CameraBridgeViewBase.CvCameraViewListener2 {

    // Constants:
    static final int VIEW_MODE_DEFAULT   = 0;
    static final int VIEW_MODE_RGBA      = 1;
    static final int VIEW_MODE_GRAYSCALE = 2;

    //Mode selectors:
    private int mViewMode  = VIEW_MODE_DEFAULT;
    private int mColorMode = VIEW_MODE_RGBA   ;

    //members
    private Mat mImToProcess;

    //Getters and setters

    // not used
    /*
    int getColorMode() {
        return mColorMode;
    }
    */

    void setColorMode(int colorMode) {
        mColorMode = colorMode;
    }

    // not used
    /*
    int getViewMode() {
        return mViewMode;
    }
    */

    void setViewMode(int viewMode) {
        mViewMode = viewMode;
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mImToProcess = new Mat();
    }

    @Override
    public void onCameraViewStopped() {
        mImToProcess.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        switch (mColorMode) {
            case VIEW_MODE_RGBA:
                mImToProcess = inputFrame.rgba();
                break;

            case VIEW_MODE_GRAYSCALE:
                mImToProcess = inputFrame.gray();
                break;
        }

        switch (mViewMode) {
            case VIEW_MODE_DEFAULT:
                break;
        }
        return mImToProcess;
    }
}

