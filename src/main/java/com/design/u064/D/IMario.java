package com.design.u064.D;

/**
 * 所有状态类的接口
 * 状态类实现成单例模式之后，MarioStateMachine 就无法通过构造函数来传递了，只能改造成函数参数传递进去。
 */
public interface IMario {
    State getName();

    //以下是定义的事件
    void obtainMushRoom(MarioStateMachine stateMachine);

    void obtainCape(MarioStateMachine stateMachine);

    void obtainFireFlower(MarioStateMachine stateMachine);

    void meetMonster(MarioStateMachine stateMachine);
}
