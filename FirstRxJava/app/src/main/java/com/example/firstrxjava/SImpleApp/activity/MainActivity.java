package com.example.firstrxjava.SImpleApp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.firstrxjava.R;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

    // ui
    private androidx.appcompat.widget.SearchView searchView;
    // vars
    // global disposables object
    private CompositeDisposable disposables = new CompositeDisposable();


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchview);

        buttonClick();
        button2Click();
        settingSearchview();

    }

    // throttleFirst를 통해서 버튼을 여러번 누르려고 할떄
    // 만약 한번을 눌러 이벤트가 진행되는 동안 정해놓은 일정 구간에 들어오는 이벤트들을 무시한다.
    // 예제 코드에서는 500ms동안 최대 한번만 발생을 하게 되는 것이다.
    // 즉, 다중터치를 방지하게 된다.
    private void button2Click() {
        RxView.clicks(findViewById(R.id.button2))
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Unit unit) {
                        // do some method
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void settingSearchview() {
        // debounce는 함수를 바르게 연속 이벤트를 처리할 수 있도록 하는 흐름 제어 함수
        // 이벤트를 그룹화하여 특정시간이 지난 후 하나의 이벤트만 발생할 수 있도록 하는 기술이다.
        Observable<String> observableQueryText = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                        // Listen for text input into the SearchView
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                if(!emitter.isDisposed()){
                                    emitter.onNext(newText);
                                }
                                return false;
                            }
                        });
                    }
                })
                .debounce(500,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());

        observableQueryText.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                sendRequestToServer(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void buttonClick() {
        RxView.clicks(findViewById(R.id.button))
                .map(new Function<Unit, Integer>() {
                    @Override
                    public Integer apply(@NonNull Unit unit) throws Exception {
                        return 1;
                    }
                })
                .buffer(4, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d); // add to disposables to you can clear in onDestroy
                    }

                    @Override
                    public void onNext(@NonNull List<Integer> integers) {
                        Log.d(TAG, "onNext: You clicked " + integers.size() + " times in 4 seconds!");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onDestroy() {
        // A good place to do this is in the onDestroy() method of an Activity or Fragment
        // Or In the onCleared() method of a ViewModel
        super.onDestroy();
        disposables.clear(); // remove all observer and subscribe with out disabling
    }

    private void sendRequestToServer(String query){
        Log.d(TAG,"REQUESTING...");
    }

}