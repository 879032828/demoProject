package com.example.administrator.view_test.RxJava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class rxjava2Activity extends BaseActivity {

    private String TAG;
    public Flowable<Integer> upstream;
    public Subscriber<Integer> down;
    public Subscription subscription;

    @BindView(R.id.showRxjava)
    public Button showRxjava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava2;
    }

    @Override
    public void initPresenter() {

    }

    public void initView() {
        syncSubscriber();
    }

    /**
     * 异步订阅
     */
    private void asyncSubscriber() {
        createFlowable();
        createSubscriber();
        upstream.subscribeOn(Schedulers.io())//异步订阅
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(down); //被观察者 subscribe 观察者
    }

    @OnClick(R.id.showRxjava)
    public void subscribe() {
        subscription.request(1);
    }

    /**
     * 背压策略的具体实现者 ---- Flowable
     * <p>
     * 步骤1：创建被观察者
     */
    public void createFlowable() {
        upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                正常发送事件
//                emitter.onNext(1);
//                Log.d(TAG, "Flowable onNext 1");
//                emitter.onNext(2);
//                Log.d(TAG, "Flowable onNext 2");
//                emitter.onNext(3);
//                Log.d(TAG, "Flowable onNext 3");
//                emitter.onComplete();


                for (int i = 0; i < 129; i++) {
                    Log.d(TAG, "发送了事件" + i);
                    emitter.onNext(i);
                }
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR);//需要传入背压参数
    }

    /**
     * 步骤2：创建观察者
     */
    public void createSubscriber() {
        down = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                // 对比Observer传入的Disposable参数，Subscriber此处传入的参数 = Subscription
                // 相同点：Subscription具备Disposable参数的作用，即Disposable.dispose()切断连接, 同样的调用Subscription.cancel()切断连接
                // 不同点：Subscription增加了void request(long n)
                Log.d(TAG, "onSubscribe");
                //s.request(1);//若不是异步订阅，没有这句会导致报错
                //而这一句是放在按钮里执行的，当调用时，才接收事件并进行处理
                // 作用：决定观察者能够接收多少个事件
                subscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "Subscriber onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
    }

    /**
     * 同步订阅
     * <p>
     * 同步订阅不会出现被观察者发送事件速度 > 观察者接收事件速度的情况
     * 但却会出现被观察者发送事件数量 > 观察者接收事件数量的问题
     * <p>
     * 被观察者 通过 FlowableEmitter.requested()获得了观察者自身接收事件能力，
     * 根据该信息控制事件发送速度，从而达到了观察者反向控制被观察者的效果
     */
    public void syncSubscriber() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                // 调用emitter.requested()获取当前观察者需要接收的事件数量
                long n = emitter.requested();
                Log.d(TAG, "观察者可接收的事件数量 ：" + n);
                for (int i = 0; i < n; i++) {
                    Log.d(TAG, "发送了事件" + i);
                    emitter.onNext(i);
                }

            }
        }, BackpressureStrategy.ERROR).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(10);
                subscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "接收到了事件" + integer);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }
}
