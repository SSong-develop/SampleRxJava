package com.example.firstrxjava.RxJavaExampleRepository.createoperator;

import android.util.Log;

import com.example.firstrxjava.RxJavaExampleRepository.model.Task;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FromIterableExample {
    // fromIterable
    // this will take an iterable of objects as input and output an Observable
    // it will not execute the method immediately. it will only execute the method once a subscriber has subscribed

    // it will use , To emit an arbitrary number of items that are known upfront.
    // Same as fromArray() operator but it's an iterable

    // input : List<T> , ArrayList<T> , Set<T>
    // output : Observable<T>

    public void test(){
        List<Task> list = new ArrayList<Task>();
        list.add(new Task("Take out Trash",true , 3));
        list.add(new Task("Walk the dog",false,2));
        list.add(new Task("Make my bed",true,1));
        list.add(new Task("Unload the dishwasher",false,0));
        list.add(new Task("Make dinner",true,5));

        Observable<Task> taskObservable = Observable
                .fromIterable(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d("hello",task.getDescription());
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
