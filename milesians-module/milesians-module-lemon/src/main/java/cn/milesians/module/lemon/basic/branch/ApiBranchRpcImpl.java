package cn.milesians.module.lemon.basic.branch;


import cn.hutool.core.util.StrUtil;
import cn.milesians.provider.commons.exception.ProviderException;
import cn.milesians.provider.lemon.LemonProviderException;
import cn.milesians.provider.lemon.basic.branch.BranchFeign;
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
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiBranchRpcImpl implements ApiBranchRpc {


    private final BranchFeign branchFeign;

    @Override
    public List<BranchDTO> findBranch(BranchFindDTO branchFindDTO) {

        List<BranchDTO> branchDTOS = new ArrayList<>();
        try {
            if (branchFindDTO.getBranchNums() != null && !branchFindDTO.getBranchNums().isEmpty()) {
                for (int i = 0; i < branchFindDTO.getBranchNums().size(); i = i + 100) {
                    int j = i + 100;
                    if (j > branchFindDTO.getBranchNums().size()) {
                        j = branchFindDTO.getBranchNums().size();
                    }
                    List<Integer> innerBranchNums = branchFindDTO.getBranchNums().subList(i, j);
                    String innerBranchNumsStr = innerBranchNums.stream().map(String::valueOf)
                        .collect(Collectors.joining(","));
                    List<BranchDTO> innerBranchDTOS = branchFeign.findBranch(branchFindDTO.getSystemBookCode(),
                        innerBranchNumsStr);
                    branchDTOS.addAll(innerBranchDTOS);
                }
            } else {
                branchDTOS = branchFeign.findBranch(branchFindDTO.getSystemBookCode(), null);
            }

            if (StrUtil.isNotEmpty(branchFindDTO.getKeyword())) {
                branchDTOS = branchDTOS.stream().filter(a -> a.getBranchName().contains(branchFindDTO.getKeyword())
                    || a.getBranchNum().toString().contains(branchFindDTO.getKeyword())
                    || a.getBranchCode().contains(branchFindDTO.getKeyword())).collect(Collectors.toList());
            }

            if (branchFindDTO.getRegionNums() != null && !branchFindDTO.getRegionNums().isEmpty()) {
                branchDTOS = branchDTOS.stream().filter(
                    a -> a.getBranchRegionNum() != null && branchFindDTO.getRegionNums()
                        .contains(a.getBranchRegionNum())).collect(Collectors.toList());
            }
            return branchDTOS;
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public List<BranchV2DTO> findBranchV2(BranchListV2DTO branchListV2DTO) {
        try {
            return branchFeign.findBranchV2(branchListV2DTO.getSystemBookCode(), branchListV2DTO);
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public BranchDTO readBranch(BranchReadDTO branchReadDTO) {

        try {
            List<BranchDTO> branchDTOS = branchFeign.findBranch(branchReadDTO.getSystemBookCode(),
                branchReadDTO.getBranchNum().toString());
            if (branchDTOS == null || branchDTOS.isEmpty()) {
                return null;
            }
            return branchDTOS.get(0);
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public List<BranchGroupingDTO> findBranchGroup(BranchGroupingFindDTO branchGroupingFindDTO) {

        try {
            return branchFeign.findBranchGroup(branchGroupingFindDTO.getSystemBookCode(),
                branchGroupingFindDTO.getKeyword());
        } catch (Exception e) {
            throw new ProviderException(e.getMessage());
        }
    }

    @Override
    public List<BranchRegionDTO> findBranchRegion(BranchRegionFindDTO branchRegionFindDTO) {
        try {
            List<BranchRegionDTO> branchRegionDTOS = branchFeign.findBranchRegion(
                branchRegionFindDTO.getSystemBookCode());
            if (branchRegionFindDTO.getBranchRegionNum() != null) {
                branchRegionDTOS = branchRegionDTOS.stream()
                    .filter(dto -> dto.getBranchRegionNum().equals(branchRegionFindDTO.getBranchRegionNum()))
                    .collect(Collectors.toList());
            }
            if (StrUtil.isNotEmpty(branchRegionFindDTO.getKeyword())) {
                branchRegionDTOS = branchRegionDTOS.stream().filter(
                        dto -> dto.getBranchRegionName().contains(branchRegionFindDTO.getKeyword())
                            || dto.getBranchRegionCode().contains(branchRegionFindDTO.getKeyword()))
                    .collect(Collectors.toList());
            }
            return branchRegionDTOS;
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

}
