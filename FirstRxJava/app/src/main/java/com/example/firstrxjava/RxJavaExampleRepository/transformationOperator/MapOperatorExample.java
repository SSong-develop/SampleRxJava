package com.example.firstrxjava.RxJavaExampleRepository.transformationOperator;

import android.util.Log;

import com.example.firstrxjava.RxJavaExampleRepository.model.DataSource;
import com.example.firstrxjava.RxJavaExampleRepository.model.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MapOperatorExample {
    // 특정 함수를 통해 observable의 emit되는 데이터를 변화시키는 연산자
    public void test(){
        Function<Task,String> extractDescriptionFunction = new Function<Task, String>() {
            @Override
            public String apply(@NonNull Task task) throws Exception {
                Log.d("hello","apply: doing work on thread: " + Thread.currentThread().getName());
                return task.getDescription();
            }
        };

        Observable<String> extractDescriptionObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .map(extractDescriptionFunction)
                .observeOn(AndroidSchedulers.mainThread());

        extractDescriptionObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("hello", "onNext: extracted description: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void test1(){
        Function<Task,Task> completeTaskFunction = new Function<Task, Task>() {
            @Override
            public Task apply(@NonNull Task task) throws Exception {
                Log.d("hello","apply: doing work on thread: "+ Thread.currentThread().getName());
                task.setComplete(true);
                return task;
            }
        };

        Observable<Task> completeObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .map(completeTaskFunction)
                .observeOn(AndroidSchedulers.mainThread());

        completeObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d("hello", "onNext: is this task complete? " + task.isComplete());
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
