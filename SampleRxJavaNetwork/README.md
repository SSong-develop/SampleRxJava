# FromPublisher

fromPublisher() is used to convert LiveData objects to reactive streams, or from reactive streams to LiveData objects

**fromPublisher() 는 LiveData를 reactive streams로 혹은, reactive streams를 LiveData로 바꿔주는 역할을 한다.**

More than likely you will never want to convert LiveData to a reactive Observable - other than some rare cases when you might want to apply an operator

**LiveData를 reactive Observable로 바꾸는 것은 굉장히 이례적인 케이스로 자주 사용되지는 않는다.**

On the other hand converting an Observable to LiveData is actually very practical

**한편으로는 , Observable을 LiveData로 변환하는 것은 굉장히 효율적이고 자주 사용될 수 있다.**



# FromFuture

이 연산자를 사용하기 위해서는 필요한 것이 더 있다. Executor 인터페이스를 구현한 클래스에 Callable객체를 인자로 넣어 Future객체로 반환되는 과정이 필요하다. 그리고 get() 메소드를 통해 Callable객체에서 구현된 결과가 나올 때까지 기다리게 된다.

그렇다면 각각의 **Executor** , **Callable** , **Future** 이 녀석들은 각각 어떤 역할을 하는 녀석들인지 알아보자.

먼저 Executor는 해석 그대로 실행되어질 녀석이란 뜻으로 어떤 장소에서 이 작업이 행해질 지를 결정합니다. 

예시 코드에서 Executors.newSingleThreadExecutor()를 통해 단일 쓰레드 실행자를 넣어줌으로써 이 쓰레드에서 작업할 것을 명시합니다.

그 다음 Callable() 은 Runnable()과 대조되는 녀석으로 비동기 처리를 위해 사용하는 것입니다

Runnable을 이용해서 background 처리를 진행했었는데 runnable의 단점은 값을 return 받을 수 없다는 구조로 되어있던 것이였습니다.

하지만 Callable은 비동기 작업으로인해 발생한 작업의 값을 변수로 받을 수 있게 해주는 것입니다.

마지막 Future() 은 미래의 즉, 이 작업이 어떻게 마무리 될지에 대해서 결정을 해줍니다.

예시 코드 상에서 보면 cancel , iscancel , isDone , get 메소드를 통해서 기존 Executor와 Callable을 통해 실행된 작업이 잘 성공되면 isDone과 함께 get을 통해서 값을 반환받을 수 있게 되며 , 만일 다양한 이유로 작업이 성공치 못하거나 특정 시간을 초과해버리는 경우 cancel을 통해서 작업이 실패했음을 알려줄 수 있게 됩니다.

