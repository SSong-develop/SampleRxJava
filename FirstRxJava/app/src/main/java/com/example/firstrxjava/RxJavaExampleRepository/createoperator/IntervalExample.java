package com.example.firstrxjava.RxJavaExampleRepository.createoperator;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class IntervalExample {
    // 기존 안드로이드에서 '몇초간 특정 메소드를 진행해라' 라는 기능을 수행할 때에는 handler와 runnable 객체롤 사용했었습니다.
    // 백그라운드 쓰레드에서 value를 사용해 이를 가공하고 handler에게 보내 이를 UI에 보여주도록 하는 일련의 순서로 작업을 했습니다.
    // 하지만 RXJava에서는 Interval Operator를 통해 이 작업을 수행할 수 있습니다.

    Observable<Long> intervalObservable = Observable // 이 객체는 activity가 뒤지기 전까자는 계속 살아서 진행하는데(infinite) , 그래서 limits을 줘야할 때가 있다.
            .interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .takeWhile(new Predicate<Long>() { // limit을 주는 방법 , emitted되어 오는 value를 확인하기 위해 takeWhile operator를 사용
                @Override
                public boolean test(@NonNull Long aLong) throws Exception {
                    return aLong <= 5; // if value more than 5 , observable stops emitting results
                }
            }).observeOn(AndroidSchedulers.mainThread());

    public void test(){
        intervalObservable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Long aLong) {
                // We can check aLong value in this method
                Log.i("IntervalOperator",aLong + Thread.currentThread().toString());
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
