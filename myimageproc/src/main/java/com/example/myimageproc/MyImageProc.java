package com.example.myimageproc;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MyImageProc {

    private static void sobelFilter(Mat inputImage, Mat outputImage) {
        //Applies Sobel filter to image
        Mat grad_x = new Mat();
        Mat grad_y = new Mat();
        int ddepth = CvType.CV_16SC1;
        Imgproc.Sobel(inputImage, grad_x, ddepth, 1, 0);
        Imgproc.Sobel(inputImage, grad_y, ddepth, 0, 1);
        Core.convertScaleAbs(grad_x, grad_x, 10, 0);
        Core.convertScaleAbs(grad_y, grad_y, 10, 0);
        Core.addWeighted(grad_x, 0.5, grad_y, 0.5, 0, outputImage);
        grad_x.release();
        grad_y.release();
    }

    private static void displayFilter(Mat displayImage, Mat filteredImage,
                                      int[] window) {
        Mat displayInnerWindow = displayImage.submat(window[0], window[1],
                window[2], window[3]);
        Mat filteredInnerWindow = filteredImage.submat(window[0],
                window[1], window[2], window[3]);
        if (displayImage.channels() > 1) {
            Imgproc.cvtColor(filteredInnerWindow, displayInnerWindow,
                    Imgproc.COLOR_GRAY2BGRA, 4);
        } else {
            filteredInnerWindow.copyTo(displayInnerWindow);
        }
        displayInnerWindow.release();
        filteredInnerWindow.release();
    }

    private static int[] setWindow(Mat image) {
        int width = image.width();
        int height = image.height();
        int minDistance = 10;

        // Calculate the window borders
        int left = (int) Math.max(width / 20, minDistance);
        int right = (int) Math.min((19 * width) / 20, width - minDistance);
        int top = (int) Math.max(height / 20, minDistance);
        int bottom = (int) Math.min((19 * height) / 20, height - minDistance);

        // Adjust the borders to ensure divisibility by 16
        left = (left + 15) / 16 * 16;
        right = (right / 16) * 16;
        top = (top + 15) / 16 * 16;
        bottom = (bottom / 16) * 16;

        return new int[]{top, bottom, left, right};
    }

    public static void sobelCalcDisplay(Mat displayImage, Mat inputImage,
                                        Mat filteredImage) {
        //The function applies the Sobel filter
        //and returns the result in a format suitable for display.
        sobelFilter(inputImage, filteredImage);
        int[] window = setWindow(displayImage);
        displayFilter(displayImage, filteredImage, window);
    }

}
