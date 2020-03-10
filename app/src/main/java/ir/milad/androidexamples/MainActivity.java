package ir.milad.androidexamples;


import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ir.milad.androidexamples.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
                The Old view Model usage
         */
//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        LoginViewModelOld loginViewModelOld = new LoginViewModelOld();
//        binding.setLoginViewModel(loginViewModelOld);
//
//        loginViewModelOld.getUser().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(@Nullable User user) {
//                if (user.getEmail().length() > 5 || user.getPassword().length() > 0)
//                    Toast.makeText(getApplicationContext(), "email : " + user.getEmail() + " password "
//                            + user.getPassword(), Toast.LENGTH_SHORT).show();
//            }
//        });


        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user.getEmail().length() > 0 || user.getPassword().length() > 0)
                    Toast.makeText(getApplicationContext(), "email : " + user.getEmail() + " password " + user.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
