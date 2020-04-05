package com.design.u064.C;

/**
 * 利用状态模式来优化。状态模式通过将事件触发的状态转移和动作执行，拆分到不同的状态类中，来避免分支判断逻辑。
 * 其中，IMario 是状态的接口，定义了所有的事件。SmallMario、SuperMario、CapeMario、FireMario 是 IMario 接口的实现类，分别对应状态机中的 4 个状态。
 * 原来所有的状态转移和动作执行的代码逻辑，都集中在 MarioStateMachine 类中，现在，这些代码逻辑被分散到了这 4 个状态类中。
 * 但是这里还有个问题，就是每次转移状态都会new 一个IMario 类，其实可以将状态类设计成单例类，参考D。
 */
public class MarioStateMachine {
    private int score;
    private IMario mario;   // 不再使用枚举来表示状态

    public MarioStateMachine() {
        this.score = 0;
        this.mario = new SmallMario(this);
    }


    public void obtainMushRoom() {
        mario.obtainMushRoom();
    }

    public void obtainCape() {
        mario.obtainCape();
    }

    public void obtainFireFlower() {
        mario.obtainFireFlower();
    }

    public void meetMonster() {
        mario.meetMonster();
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public IMario getMario() {
        return mario;
    }

    public void setMario(IMario mario) {
        this.mario = mario;
    }
}
