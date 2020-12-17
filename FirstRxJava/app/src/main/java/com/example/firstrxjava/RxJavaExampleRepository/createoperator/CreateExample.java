package com.example.firstrxjava.RxJavaExampleRepository.createoperator;

import com.example.firstrxjava.RxJavaExampleRepository.model.Task;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateExample {
    final Task task = new Task("Walk the dog", false, 4);

    // subscribeOn() -> 데이터를 처리할 쓰레드를 설정
    // observeOn() -> 데이터를 받은 쓰레드를 어디로 설정할 것인지
    // subscribe() -> 다음에 해야할 작업을 알려줌

    Observable<Task> singleTaskObservable = Observable.create(new ObservableOnSubscribe<Task>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<Task> emitter) throws Exception {
            if (!emitter.isDisposed()) { // emitter가 dispose 되지 않았다면
                emitter.onNext(task); // task객체를 들고 Next를 진행
                emitter.onComplete(); // emitter를 complete 시켜준다.
            }
        }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    // -> io쓰레드에서 데이터를 처리한 후, mainThread로 처리된 데이터를 받는다.

    public void test(){
        singleTaskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                // 여기서 emitter가 가져왔던 task 객체를 처리한다.
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
