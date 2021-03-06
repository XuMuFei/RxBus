package com.blankj.rxbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/08/05
 *     desc  :
 * </pre>
 */
public class RxBusTest {

    @Before
    public void setUp() {

    }

    @Test
    public void test() {

        RxBus.getDefault().postSticky((Call<Integer>) integer -> System.out.println(integer));

        RxBus.getDefault().subscribeSticky(this, new RxBus.Callback<Call<Integer>>() {

            @Override
            public void onEvent(Call<Integer> integerCall) {
                integerCall.call(10086);
            }
        });

        RxBus.getDefault().postSticky(10086);

        RxBus.getDefault().subscribeSticky(this, new RxBus.Callback<Integer>() {
            @Override
            public void onEvent(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @After
    public void tearDown() {
        RxBus.getDefault().unregister(this);
    }
}
