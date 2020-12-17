package com.example.firstrxjava.RxJavaExampleRepository.createoperator;

import com.example.firstrxjava.RxJavaExampleRepository.model.DataSource;
import com.example.firstrxjava.RxJavaExampleRepository.model.Task;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateListExample {
    final Task task = new Task("Walk the dog", false, 4);

    Observable<Task> listTaskObservable = Observable.create(new ObservableOnSubscribe<Task>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<Task> emitter) throws Exception {

            // DataSource의 CreateTasksList함수를 호출해 task 변수에 넣어준다. 이를 emitter를 통해 onNext로 넘겨준다.
            for(Task task : DataSource.createTasksList()){
                if(!emitter.isDisposed())
                    emitter.onNext(task);
            }

            // Once the loop is complete, call the onComplete() method
            // 위에서 한번 반복이 끝난 다음에는 emitter를 끝내줘야만 한다.
            if(!emitter.isDisposed())
                emitter.onComplete();
        }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    public void test(){
        listTaskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                // In this Method , can use task instance , in case UI update or Parse JSON Data
                // 이 메소드에서 emitter가 보내주었던 task객체를 사용할 수 있고 , UI업데이트나 JSON데이터를 파싱하는 작업이 가능하다.
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
