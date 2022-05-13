package title.btree;

import lombok.Data;

/**
 * @author jad
 * @Description: 二叉树结点
 * @date 2022/5/13 下午7:22
 */
@Data
public class BinTreeNode {

    private Object data;

    private BinTreeNode left;

    private BinTreeNode right;


}
