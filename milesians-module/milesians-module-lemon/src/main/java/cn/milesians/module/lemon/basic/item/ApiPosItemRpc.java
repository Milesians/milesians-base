package cn.milesians.module.lemon.basic.item;


import cn.milesians.provider.lemon.basic.item.dto.PosItemCategoryDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCategoryFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCountDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemFindAllDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemImageSaveDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemLabelDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemLabelFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemListDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemPageDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemSaveDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUnitDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUnitFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUpdateDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierQueryDTO;
import java.util.List;

/**
 * 乐檬开放平台基础信息
 * @author nhsoft.shidq
 * @date 2022-06-14
 */
public interface ApiPosItemRpc {

    /**
     * 分页查询商品档案（信息）
     */
    List<PosItemDTO> pagePosItemInfo(PosItemPageDTO posItemPageDTO);

    /**
     * 分页查询商品档案（总数）
     */
    Integer countPosItemInfo(PosItemCountDTO posItemCountDTO);

    /**
     * 查询商品档案信息
     */
    List<PosItemDTO> findPosItemInfo(PosItemFindDTO posItemFindDTO);

    /**
     * 查询商品档案信息
     */
    List<PosItemDTO> findPosItemInfo(PosItemListDTO posItemListDTO);


    /**
     * 查询所有商品档案信息
     */
    List<PosItemDTO> findAllPosItemInfo(PosItemFindDTO posItemFindAllDTO);


    /**
     * 查询所有商品档案信息
     */
    List<PosItemDTO> findAllPosItemInfo(PosItemFindAllDTO posItemFindAllDTO);

    /**
     * 新增商品档案
     */
    PosItemDTO savePosItemInfo(PosItemSaveDTO posItemSaveDTO);

    /**
     * 更新商品档案
     */
    PosItemDTO updatePosItemInfo(PosItemUpdateDTO posItemUpdateDTO);

    /**
     * 新增商品图片
     */
//    void savePosItemImage(PosItemImageSaveDTO posItemImageSaveDTO);

    /**
     * 查询商品档案类别
     */
    List<PosItemCategoryDTO> findPosItemCategory(PosItemCategoryFindDTO posItemCategoryFindDTO);

    /**
     * 查询商品档案标签
     */
//    List<PosItemLabelDTO> findPosItemLabel(PosItemLabelFindDTO posItemLabelFindDTO);


    /**
     * 查询商品计量单位查询
     */
    List<PosItemUnitDTO> findPosItemUnit(PosItemUnitFindDTO posItemUnitFindDTO);


    /**
     * 分页查询供货关系
     */
    List<StoreItemSupplierDTO> findStoreItemSupplier(StoreItemSupplierQueryDTO storeItemSupplierQueryDTO);
    /**
     * 分页查询供货关系
     */
    List<StoreItemSupplierDTO> findStoreItemSupplier(StoreItemSupplierFindDTO storeItemSupplierFindDTO);
}
