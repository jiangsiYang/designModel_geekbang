package com.design.u012.oop;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Repository 层还是贫血模型，是否有必要也进行充血领域建模呢？
 * 不需要。即便它被设计成贫血模型，违反面向对象编程的封装特性，有被任意代码修改数据的风险，但 Entity 的生命周期是有限的。一般来讲，我们把它传递到 Service 层之后，
 * 就会转化成 BO 或者 Domain 来继续后面的业务逻辑。Entity 的生命周期到此就结束了，所以也并不会被到处任意修改。
 */
@Data
public class VirtualWalletEntity {
    private BigDecimal balance;
}
