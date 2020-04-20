package ir.milad.androidexamples.threading;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import ir.milad.androidexamples.Constants;

public class MyThread extends Thread {
    private static final String TAG = "MyThread";

    private Handler mMyThreadHandler = null;
    private Handler mMainThreadHandler = null;
    private boolean isRunning = false;

    public MyThread(Handler mMainThreadHandler) {
        this.mMainThreadHandler = mMainThreadHandler;
        isRunning = true;
    }

    @Override
    public void run() {
        if (isRunning) {
            Looper.prepare();
            mMyThreadHandler = new MyThreadHandler(Looper.myLooper());
            Looper.loop();
        }
    }

    public void quitThread(){
        //memory leak preventing
        isRunning = false;
        mMainThreadHandler = null;
    }

    public void sendMessageToBackgroundThread(Message message) {
        while (true) {
            try {
                mMyThreadHandler.sendMessage(message);
                break;
            } catch (NullPointerException ex) {
                Log.e(TAG, "sendMessageToBackgroundThread : Null Pointer : " + ex.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class MyThreadHandler extends Handler {
        public MyThreadHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.WORD_INSERT_NEW: {
                    Log.d(TAG, "handleMessage: saving word on thread : " + Thread.currentThread().getName());
                    //send message to main activity callback (for ui updates)
//                    Message message = Message.obtain(mMainThreadHandler,Constants.WORD_INSERT_NEW);
//                    message.sendToTarget();
                    //OR
                    Message message = Message.obtain(null, Constants.WORD_INSERT_NEW);
                    mMainThreadHandler.sendMessage(message);

                    break;
                }
                case Constants.WORD_UPDATE: {
                    Log.d(TAG, "handleMessage: updating word on thread : " + Thread.currentThread().getName());
                    break;
                }
                case Constants.WORD_RETRIEVE: {
                    Log.d(TAG, "handleMessage: retrieving word on thread : " + Thread.currentThread().getName());
                    break;
                }
                case Constants.WORD_DELETE: {
                    Log.d(TAG, "handleMessage: deleting word on thread : " + Thread.currentThread().getName());
                    break;
                }
            }
        }
    }
}
