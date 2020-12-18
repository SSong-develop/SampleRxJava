package com.example.firstrxjava.RxJavaExampleRepository.filteroperator;

import android.util.Log;

import com.example.firstrxjava.RxJavaExampleRepository.model.DataSource;
import com.example.firstrxjava.RxJavaExampleRepository.model.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskOperatorExample {
    // if take(3) then emit items number is 3
    public void test(){
        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .take(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d("hello", "onNext: " + task.getDescription());
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
