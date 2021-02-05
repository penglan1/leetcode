package pers.penglan.algorithm.jan2019;

import pers.penglan.algorithm.other.ListNode;

/**
 * 两数相加
 */
public class TwoAdd {
    public static void main(String[] args) {
        TwoAdd two = new TwoAdd();
        ListNode num1 = two.createNum(9);
        ListNode num2 = two.createNum(4, 6, 5);
        ListNode sum3 = two.addTwoNumbers(num1, num2);
        int sum = two.getNum(sum3);
        System.out.println(sum);

    }

    /**
     * 用链表连表示对应的数值
     * EG: <code>
     *     create(3, 4, 2); // 将返回一个链表，2 -> 4 -> 3, 代表的数据值为 342
     *     create(4, 6, 5); // 将返回一个链表，5 -> 6 -> 4，代表的数据值为 465
     *     </code>
     *
     * @param num 需要用链表表示的数据
     * @return
     */
    private  ListNode createNum(Integer ... num) {
        ListNode head = new ListNode(num[num.length - 1]);
        ListNode p = head;
        for (int i = num.length - 2; i >= 0; i--) {
            p.next = new ListNode(num[i]);
            p = p.next;
        }

        return head;
    }

    /**
     * 获取链表对应的数据值
     * @param num
     * @return
     */
    private int getNum(ListNode num) {
        int data = 0;
        int redix = 1;
        while (num != null) {
            data = num.val * redix + data;
            redix *= 10;
            num = num.next;
        }

        return data;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers01(l1, l2);
    }

    private ListNode addTwoNumbers01(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode p = null;
        ListNode temp = null;
        while (l1 != null && l2 != null) {
            temp = add(l1, l2);
            if (head != null) {
                p.next = temp;
                p = p.next;
            } else {
                head = temp;
                p = head;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode remain = l1 == null ? l2 : l1;
        while (remain != null) {
            p.next = new ListNode(remain.val);
            remain = remain.next;
            p = p.next;
        }

        return head;
    }

    private ListNode add(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int c = sum > 9 ? sum % 10 : sum;  // 当前位的值：如果大于9，则是进位之后的值，否则不用进位
        ListNode current = new ListNode(c);
        if (sum > 9) {  // 处理进位
            if (l1.next == null) {
                l1.next = new ListNode(0);
            }
            addCF(l1.next, sum / 10);
        }
        return current;
    }

    /**
     * 在当前节点增加相应的值num
     * @param l1
     * @param num
     */
    private void addCF(ListNode l1, int num) {
        if (l1.val + num < 10) {
            l1.val = l1.val + num;
            return;
        }
        /* 还需进位 */
        l1.val = l1.val + num - 10;
        if (l1.next == null) {
            l1.next = new ListNode(1);
        } else {
            addCF(l1.next, 1);
        }

    }

}
