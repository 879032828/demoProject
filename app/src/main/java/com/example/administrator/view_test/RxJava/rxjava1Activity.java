package com.example.administrator.view_test.RxJava;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action3;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class rxjava1Activity extends BaseActivity {

    @BindView(R.id.showRxjava)
    public Button mShowRxjava;

    @BindView(R.id.showRxjavaAction)
    public Button mShowRxjavaAction;

    @BindView(R.id.setFlatMap)
    public Button mSetFlatMap;

    private Observer<String> observer;
    private Subscriber<String> subscriber;
    private Observable observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initObserver();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava1;
    }

    @Override
    public void initPresenter() {

    }

    private void initObserver() {
        createObserver();
        createSubscriber();
        createObservable();
    }

    //创建观察者
    private void createObserver() {
        observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(MyApplication.RXJAVA_DEBUG, "onCompleted is done");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(MyApplication.RXJAVA_DEBUG, "onError is done");
            }

            @Override
            public void onNext(String s) {
                Log.d(MyApplication.RXJAVA_DEBUG, "onNext is done");
                Toast.makeText(rxjava1Activity.this, s + "! Observer is done", Toast.LENGTH_SHORT).show();
            }
        };
    }

    //第一步：创建观察者
    private void createSubscriber() {
        subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.d(MyApplication.RXJAVA_DEBUG, "onStart is done");
            }

            @Override
            public void onCompleted() {
                Log.d(MyApplication.RXJAVA_DEBUG, "onCompleted is done");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(MyApplication.RXJAVA_DEBUG, "onError is done");
            }

            @Override
            public void onNext(String s) {
                Log.d(MyApplication.RXJAVA_DEBUG, "onNext is done");
            }
        };

    }

    //第二步：创建被观察者
    private void createObservable() {
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("哈哈");
                subscriber.onNext("呵呵");
                subscriber.onCompleted();
            }
        });

        //这种在 subscribe() 之前写上两句 subscribeOn(Scheduler.io()) 和 observeOn(AndroidSchedulers.mainThread())
        // 的使用方式非常常见，它适用于多数的 『后台线程取数据，主线程显示』的程序策略。
        observable.subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread());// 指定 Subscriber 的回调发生在主线程
    }

    //第三步：创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了。
    @OnClick(R.id.showRxjava)
    public void subscribe() {
        Subscription subscription = observable.subscribe(observer);
    }

    //通过
    // Action0 无参无返回的接口
    // Action1 单参无返回的接口
    // 定义观察者observer的三个方法
    // 并将这三个方法作为订阅subscribe的参数传入
    public void createObserverByAction() {
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };

        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {

            }
        };

        Action1<Throwable> onErrorAcion = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };

        Action3<String, Integer, Boolean> onThreeAction = new Action3<String, Integer, Boolean>() {
            @Override
            public void call(String s, Integer integer, Boolean aBoolean) {

            }
        };

        observable.subscribe(onNextAction, onErrorAcion, onCompletedAction);
    }


    //RxJava 提供了对事件序列进行变换的支持，这是它的核心功能之一，
    // 也是大多数人说『RxJava 真是太好用了』的最大原因。
    // 所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列
    //
    public void setMap() {
        //map() 方法将参数中的 String 对象转换成一个 Integer 对象后返回，
        // 而在经过 map() 方法后，事件的参数类型也由 String 转为了 Integer。
        observable
                .just("123")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                });
    }


    @OnClick(R.id.setFlatMap)
    public void setFlatMap() {
        Student[] list = setStudentData();
        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d(MyApplication.RXJAVA_DEBUG, course.getSoursename());
            }
        };

        Observable.from(list)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getList());
                    }
                })
                .subscribe(subscriber);

    }

    public Student[] setStudentData() {
        Student a = new Student("李", new Course[]{new Course(1, "语文"),new Course(2, "数学")});
        Student b = new Student("陈", new Course[]{new Course(1, "英语"),new Course(2, "物理")});
        return new Student[]{a, b};
    }

    public class Student {

        public String name;
        public Course[] list;

        public Student(String name, Course[] list) {
            this.name = name;
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Course[] getList() {
            return list;
        }

        public void setList(Course[] list) {
            this.list = list;
        }
    }

    public class Course {

        public int id;
        public String soursename;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Course(int id, String soursename) {
            this.id = id;
            this.soursename = soursename;
        }

        public String getSoursename() {
            return soursename;
        }

        public void setSoursename(String soursename) {
            this.soursename = soursename;
        }

    }
}
