package ir.milad.androidexamples;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class LoginViewModelOld extends BaseObservable {

    private String email;
    private String password;
    private int busy = 8;
    public final ObservableField<String> errorPassword = new ObservableField<>();
    public final ObservableField<String> errorEmail = new ObservableField<>();
    //ObservableFiled isn't lifecycle aware

    private MutableLiveData<User> userMutableLiveData;

    public LoginViewModelOld() {

    }

    LiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    @Bindable
    @NonNull
    public String getEmail() {
        return this.email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    @NonNull
    public String getPassword() {
        return this.password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public int getBusy() {
        return this.busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
        notifyPropertyChanged(BR.busy);
    }

    public void onLoginClicked() {
        setBusy(0); //View.VISIBLE
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = new User(getEmail(), getPassword());

                if (!user.isEmailValid()) {
                    errorEmail.set("Enter a valid email address");
                } else {
                    errorEmail.set(null);
                }

                if (!user.isPasswordLengthGreaterThan5()) {
                    errorPassword.set("Password Length should be greater than 5");
                } else {
                    errorPassword.set(null);
                }

                userMutableLiveData.setValue(user);
                setBusy(8);// 8 == View.GONE
            }
        }, 5000);
    }
}
