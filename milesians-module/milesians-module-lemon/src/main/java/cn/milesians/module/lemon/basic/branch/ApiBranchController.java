package cn.milesians.module.lemon.basic.branch;


import cn.milesians.provider.lemon.basic.branch.dto.BranchDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchFindDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchGroupingDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchGroupingFindDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchRegionDTO;
import cn.milesians.provider.lemon.basic.branch.dto.BranchRegionFindDTO;
import cn.milesians.provider.commons.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "乐檬开放平台-门店档案")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lemon/basic/branch")
public class ApiBranchController {


    private final ApiBranchRpc apiBranchRpc;

    @GetMapping(value = "/group/find")
    @Operation(description = "查询门店分组")
    public Response<List<BranchGroupingDTO>> findBranchGroup(@Validated BranchGroupingFindDTO branchGroupingFindDTO) {

        return Response.success(apiBranchRpc.findBranchGroup(branchGroupingFindDTO));
    }

    @GetMapping(value = "/find")
    @Operation(description = "查询门店信息")
    public Response<List<BranchDTO>> findBranchInfo(@Validated BranchFindDTO branchFindDTO) {

        return Response.success(apiBranchRpc.findBranch(branchFindDTO));
    }

    @GetMapping(value = "/region/find")
    @Operation(description = "查询门店区域")
    public Response<List<BranchRegionDTO>> findBranchInfo(@Validated BranchRegionFindDTO branchRegionFindDTO) {
        return Response.success(apiBranchRpc.findBranchRegion(branchRegionFindDTO));
    }
}
