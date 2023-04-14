package cn.milesians.provider.lemon.basic.branch;


import cn.milesians.provider.lemon.basic.branch.dto.BranchDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchGroupingDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchListV2DTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchRegionDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchV2DTO;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;


/**
 * 获取乐檬供应商信息
 * @author soliddq
 * @date 2022-06-14
 */
public interface BranchFeign {

    /**
     * 查询所有门店
     *
     * @return
     */
    @RequestLine("GET /nhsoft.amazon.branch.list?branch_nums={branchNums}")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<BranchDTO> findBranch(@Param("systemBookCode") String systemBookCode,
        @Param("branchNums") String branchNums);


    /**
     * 查询所有门店
     *
     * @return
     */
    @RequestLine("GET /nhsoft.amazon.branch.list/v2")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<BranchV2DTO> findBranchV2(@Param("systemBookCode") String systemBookCode,
            @QueryMap BranchListV2DTO branchListV2DTO);


    /**
     * 查询门店分组
     *
     * @return
     */
    @RequestLine("GET /nhsoft.amazon.basic.branchgrouping.find?keyword={keyword}")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<BranchGroupingDTO> findBranchGroup(@Param("systemBookCode") String systemBookCode,
        @Param("keyword") String keyword);

    /**
     * 查询门店区域
     * @return
     */
    @RequestLine("GET /nhsoft.amazon.basic.region.find")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<BranchRegionDTO> findBranchRegion(@Param("systemBookCode") String systemBookCode);


}
