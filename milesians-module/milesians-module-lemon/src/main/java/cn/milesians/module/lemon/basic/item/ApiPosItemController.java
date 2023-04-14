package cn.milesians.module.lemon.basic.item;

import cn.milesians.provider.commons.Response;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCategoryDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCategoryFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCountDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemPageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "乐檬开放平台-商品档案")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lemon/basic/item")
public class ApiPosItemController {

    private final ApiPosItemRpc apiPosItemRpc;

    @GetMapping(value = "/find")
    @Operation(description = "分页查询商品档案（信息）")
    public Response<List<PosItemDTO>> pagePosItem(@Validated PosItemPageDTO posItemPageDTO) {
        return Response.success(apiPosItemRpc.pagePosItemInfo(posItemPageDTO));
    }


    @GetMapping(value = "/basic.positem.info.count")
    @Operation(description = "分页查询商品档案（总数）")
    public Response<Integer> countPosItem(@Validated PosItemCountDTO posItemCountDTO) {
        return Response.success(apiPosItemRpc.countPosItemInfo(posItemCountDTO));
    }

    @GetMapping(value = "/basic.positem.category.find")
    @Operation(description = "查询商品档案类别")
    public Response<List<PosItemCategoryDTO>> findPosItemCategory(
        @Validated PosItemCategoryFindDTO posItemCategoryFindDTO) {

        return Response.success(apiPosItemRpc.findPosItemCategory(posItemCategoryFindDTO));
    }

//    @GetMapping(value ="/basic.positem.label.find")
//    @ApiOperation(value = "查询商品档案标签", response = PosItemLabelDTO.class)
//    public Response findPosItemLabel(@Validated @ParameterConvert PosItemLabelFindDTO posItemLabelFindDTO) {
//
//        return Response.createSuccess(apiPosItemRpc.findPosItemLabel(posItemLabelFindDTO));
//    }


}