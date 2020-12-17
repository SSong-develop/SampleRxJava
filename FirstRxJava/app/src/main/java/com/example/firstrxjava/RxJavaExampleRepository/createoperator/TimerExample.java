package com.example.firstrxjava.RxJavaExampleRepository.createoperator;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimerExample {
    // TimerOperator는 emits one particular item after a span of time that you specify
    // 즉 , 단 하나의 single Observable 만 emit하게 된다.
    //
    Observable<Long> timeObservable = Observable
            .timer(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    public void test(){
        timeObservable.subscribe(new Observer<Long>() {
            long time = 0;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                time = System.currentTimeMillis() / 1000;
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                Log.i("TimerOperator",((System.currentTimeMillis() / 1000) - time) + "seconds have elapsed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
