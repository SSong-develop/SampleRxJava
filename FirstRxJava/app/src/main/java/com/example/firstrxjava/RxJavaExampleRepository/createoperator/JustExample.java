package com.example.firstrxjava.RxJavaExampleRepository.createoperator;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JustExample {
    // 단 한번만 진행할 떄
    public void test(){
        Observable.just("one","two","three","four")
                .subscribeOn(Schedulers.io()) // io 쓰레드에서 데이터 가공 작업을 진행
                .observeOn(AndroidSchedulers.mainThread()) // 가공된 데이터는 mainThread로 가져와 작업할 수 있도록 한다.
                .subscribe(new Observer<String>() { // String Type의 Observer에게 이 데이터를 넘겨줌으로써 작업을 진행한다.
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        // UI 갱신 , JSON 파싱 등 이 메소드에서 작업할 수 있다.
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
