package ir.milad.androidexamples;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {

    private final IBinder binder = new LocalBinder();

    private final Random mGenerator = new Random();

    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    public int getRandomNumber() {
        Toast.makeText(this, Thread.currentThread()+"", Toast.LENGTH_SHORT).show();
        return mGenerator.nextInt(100);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
