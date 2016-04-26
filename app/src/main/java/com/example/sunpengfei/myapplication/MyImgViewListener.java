package com.example.sunpengfei.myapplication;

/**
 * Created by sunpengfei on 16/4/21.
 */
public class MyImgViewListener{

    Listener listener;

    ListenerOne listenerOne;

    ListenerTwo listenerTwo;

    public MyImgViewListener(){
        listener = new Listener() {
            @Override
            public void listener(int position) {

            }
        };
        listenerOne = new ListenerOne() {
            @Override
            public void listenerOne() {

            }
        };
        listenerTwo = new ListenerTwo() {
            @Override
            public void listenerTwo() {

            }
        };
    }
}
