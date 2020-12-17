package com.example.firstrxjava.SImpleApp.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstrxjava.R;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

    // ui

    // vars
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialSetting();
    }


    @Override
    protected void onDestroy() {
        // A good place to do this is in the onDestroy() method of an Activity or Fragment
        // Or In the onCleared() method of a ViewModel
        super.onDestroy();
        disposable.clear(); // remove all observer and subscribe with out disabling
    }

    private void initialSetting(){
        Observable<String> simpleObservable =
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                        if (!emitter.isDisposed()) {
                            emitter.onNext("Hello RxJava!!");
                            emitter.onComplete();
                        }
                    }
                });
        simpleObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                TextView exampleTextView = findViewById(R.id.textView);
                exampleTextView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "error : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "is Completed");
            }
        });
    }

}