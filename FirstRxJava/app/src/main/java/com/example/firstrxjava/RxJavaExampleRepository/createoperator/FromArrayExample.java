package com.example.firstrxjava.RxJavaExampleRepository.createoperator;

import android.util.Log;

import com.example.firstrxjava.RxJavaExampleRepository.model.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FromArrayExample {

    public void test(){
        Task[] list = new Task[5];
        list[0] = new Task("Take out Trash",true , 3);
        list[1] = new Task("Walk the dog",false,2);
        list[2] = new Task("Make my bed",true,1);
        list[3] = new Task("Unload the dishwasher",false,0);
        list[4] = new Task("Make dinner",true,5);

        // FromArray operator는 list를 input으로 받고 output를 Observable로 준다.
        // 바로 메소드가 수행되지 않고, 메소드가 subscriber들이 subscribed했을 떄 수행된다.
        Observable<Task> taskObservable = Observable
                .fromArray(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.i("FromArrayExample",task.getDescription());
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
