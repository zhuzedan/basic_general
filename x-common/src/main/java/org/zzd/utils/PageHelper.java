package org.zzd.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @apiNote 分页结果封装
 * @author zzd
 * @date 2023/5/9 13:57
 */
@Data
@NoArgsConstructor
public class PageHelper<T> {
    //总条数
    private Long  totalCount;
    //总页数
    private Long totalPage;
    //当前页数
    private Long pageNum;
    //页面大小
    private Long pageSize;
    //返回数据
    private List<T> list;

    public static <T> PageHelper<T> restPage(IPage<T> pageResult) {
        PageHelper<T> result = new PageHelper<>();
        result.setPageNum(pageResult.getCurrent());
        result.setPageSize(pageResult.getSize());
        result.setTotalCount(pageResult.getTotal());
        result.setTotalPage(pageResult.getTotal()%pageResult.getSize()==0?pageResult.getTotal()/pageResult.getSize():pageResult.getTotal()/pageResult.getSize()+1);
        result.setList(pageResult.getRecords());
        return result;
    }
}
