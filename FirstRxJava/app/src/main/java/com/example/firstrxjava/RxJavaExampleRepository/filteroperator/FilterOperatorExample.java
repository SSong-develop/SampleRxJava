package com.example.firstrxjava.RxJavaExampleRepository.filteroperator;

import android.util.Log;

import com.example.firstrxjava.RxJavaExampleRepository.model.DataSource;
import com.example.firstrxjava.RxJavaExampleRepository.model.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FilterOperatorExample {
    // filter의 의미 그대로 observable 스트림에서 나오는 데이터들을 filter에 의해 걸러진다 라고 생각하면 편하다
    public void stringFilterTest(){
        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(@NonNull Task task) throws Exception {
                        return task.getDescription().equals("Walk the dog");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d("hello", "onNext: This task matches the description: " + task.getDescription());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void booleanFilterTest(){
        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(@NonNull Task task) throws Exception {
                        return task.isComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d("hello", "onNext: This is a completed task: " + task.getDescription());
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
