package u028;

import com.design.u028.FindSpecificNum;
import org.junit.Assert;
import org.junit.Test;

public class FindSpecificNumTest {

    @Test
    public void findNumCorrect() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int specificNum = 4;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(3, firstNum.intValue());
    }

    @Test
    public void findNumCorrect2() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int specificNum = 1;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(0, firstNum.intValue());
    }

    @Test
    public void findNumCorrect3() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int specificNum = 5;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(4, firstNum.intValue());
    }

    @Test
    public void findNumCorrect4() {
        int[] nums = new int[]{};
        int specificNum = 4;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(-1, firstNum.intValue());
    }

    @Test
    public void findNumCorrect5() {
        int[] nums = null;
        int specificNum = 4;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(-1, firstNum.intValue());
    }

    @Test
    public void findNumCorrect6() {
        int[] nums = new int[]{1, 2, 3, 4, 4, 4, 4, 4, 5};
        int specificNum = 4;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(3, firstNum.intValue());
    }

    @Test
    public void findNumCorrect7() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int specificNum = 9;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(-1, firstNum.intValue());
    }

    @Test
    public void findNumCorrect8() {
        int[] nums = new int[]{6, 24, 3, 42, 5, 5};
        int specificNum = 5;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(4, firstNum.intValue());
    }

    @Test
    public void findNumCorrect9() {
        int[] nums = new int[]{-6, 24, -3, 42, 5, -5};
        int specificNum = -5;
        Integer firstNum = FindSpecificNum.findSpecificNum(nums, specificNum);
        Assert.assertEquals(5, firstNum.intValue());
    }
}
