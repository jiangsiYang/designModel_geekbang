package com.design.u064.C;

/**
 * 所有状态类的接口
 */
public interface IMario {
    State getName();

    //以下是定义的事件
    void obtainMushRoom();

    void obtainCape();

    void obtainFireFlower();

    void meetMonster();
}
