package cn.milesians.module.lemon.basic.branch;

import cn.hutool.core.collection.CollUtil;
import cn.milesians.provider.lemon.basic.branch.dto.BranchDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchFindDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchGroupingDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchGroupingFindDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchListV2DTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchReadDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchRegionDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchRegionFindDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchV2DTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.lang.Nullable;

/**
 * 乐檬开放平台基础信息
 * @author nhsoft.shidq
 * @date 2022-06-14
 */
public interface ApiBranchRpc {

    /**
     * 查询门店
     */
    List<BranchDTO> findBranch(BranchFindDTO branchFindDTO);

    /**
     * 查询门店
     */
    List<BranchV2DTO> findBranchV2(BranchListV2DTO branchListV2DTO);

    default List<BranchV2DTO> findAllBranchV2(BranchListV2DTO branchListV2DTO) {
        int page = 1;
        branchListV2DTO.setPageSize(100);

        List<BranchV2DTO> ret = new ArrayList<>();

        while (true) {
            branchListV2DTO.setPageNo(page);
            List<BranchV2DTO> list = findBranchV2(branchListV2DTO);
            if (list == null || list.isEmpty()) {
                break;
            }
            ret.addAll(list);
            page++;
        }
        return ret;
    }

    /**
     * 读取门店
     */
    BranchDTO readBranch(BranchReadDTO branchReadDTO);

    /**
     * 查询门店分组
     */
    List<BranchGroupingDTO> findBranchGroup(BranchGroupingFindDTO branchGroupingFindDTO);

    /**
     * 查询门店区域
     */
    List<BranchRegionDTO> findBranchRegion(BranchRegionFindDTO branchRegionFindDTO);


    /**
     * 查询门店名称
     *
     * @param branchNums 门店编号
     * @param bookCode   账套号
     * @return
     */
    default Map<Integer, String> mapBranchName(Collection<Integer> branchNums, String bookCode) {
        if (CollUtil.isEmpty(branchNums)) {
            return Collections.emptyMap();
        }

        BranchFindDTO findDTO = new BranchFindDTO();
        findDTO.setBranchNums(branchNums.stream().distinct().collect(Collectors.toList()));
        findDTO.setSystemBookCode(bookCode);

        return findBranch(findDTO).stream()
            .collect(Collectors.toMap(BranchDTO::getBranchNum, BranchDTO::getBranchName));
    }

    /**
     * 查询门店名称
     *
     * @param branchNum 门店编号
     * @param bookCode  账套号
     * @return
     */
    @Nullable
    default String fetchBranchName(Integer branchNum, String bookCode) {
        return mapBranchName(Collections.singleton(branchNum), bookCode).get(branchNum);
    }

}
