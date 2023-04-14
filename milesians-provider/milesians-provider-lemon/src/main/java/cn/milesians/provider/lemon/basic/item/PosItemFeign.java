package cn.milesians.provider.lemon.basic.item;


import cn.milesians.provider.lemon.basic.item.dto.PosItemCategoryDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemImageDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemImageSaveDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemLabelDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemSaveDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUnitDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUpdateDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierQueryDTO;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;

/**
 * 获取乐檬商品信息
 * @author soliddq
 * @date 2022-06-14
 */
public interface PosItemFeign {

    /**
     * 分页查询商品
     */
    @RequestLine("GET /nhsoft.amazon.basic.item.find?last_download_time={lastDownloadTime}&keyword={keyword}&item_category_code={itemCategoryCode}&manage_template_num={manageTemplateNum}&page_no={pageNo}&page_size={pageSize}")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<PosItemDTO> pagePosItem(@Param("systemBookCode") String systemBookCode,
                                 @Param("lastDownloadTime") String lastDownloadTime,
                                 @Param("keyword") String keyword,
                                 @Param("itemCategoryCode") String itemCategoryCode,
                                 @Param("manageTemplateNum") Integer manageTemplateNum,
                                 @Param("pageNo") Integer pageNo,
                                 @Param("pageSize") Integer pageSize);

    /**
     * 分页统计商品数量
     */
    @RequestLine("GET /nhsoft.amazon.basic.item.count?last_download_time={lastDownloadTime}&keyword={keyword}&item_category_code={itemCategoryCode}&manage_template_num={manageTemplateNum}")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    Integer countPosItem(@Param("systemBookCode") String systemBookCode,
                         @Param("lastDownloadTime") String lastDownloadTime,
                         @Param("keyword") String keyword,
                         @Param("itemCategoryCode") String itemCategoryCode,
                         @Param("manageTemplateNum") Integer manageTemplateNum);


    /**
     * 查询商品列表
     */
    @RequestLine("GET /nhsoft.amazon.basic.item.find?item_nums={itemNums}&item_codes={itemCodes}&page_no=1&page_size=100")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<PosItemDTO> listPosItem(@Param("systemBookCode") String systemBookCode,
                                 @Param("itemNums") String itemNums,
                                 @Param("itemCodes") String itemCodes);

    /**
     * 获取所有商品类别
     */
    @RequestLine("GET /nhsoft.amazon.basic.itemcategory.find")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<PosItemCategoryDTO> findPosItemCategory(@Param("systemBookCode") String systemBookCode);

    /**
     * 查询商品标签
     */
    @RequestLine("GET /nhsoft.amazon.basic.itemlabel.find?label_name={labelName}")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<PosItemLabelDTO> findPosItemLabel(@Param("systemBookCode") String systemBookCode,
                                           @Param("labelName") String labelName);

    /**
     * 查询商品图片
     */
    @RequestLine("GET /nhsoft.amazon.basic.itemimage.find?item_num={itemNum}")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<PosItemImageDTO> findPosItemImage(@Param("systemBookCode") String systemBookCode,
                                           @Param("itemNum") Integer itemNum);


    /**
     * 保存商品档案
     */
    @RequestLine("POST /nhsoft.amazon.basic.item.save")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    PosItemDTO savePosItem(@Param("systemBookCode") String systemBookCode,
        PosItemSaveDTO posItemSaveDTO);

    /**
     * 更新商品档案
     */
    @RequestLine("POST /nhsoft.amazon.basic.item.update")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    PosItemDTO updatePosItem(@Param("systemBookCode") String systemBookCode,
                           PosItemUpdateDTO posItemUpdateDTO);

    /**
     * 保存商品图片
     */
    @RequestLine("POST /nhsoft.amazon.basic.itemimage.save")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    Object savePosItemImage(@Param("systemBookCode") String systemBookCode,
        PosItemImageSaveDTO posItemImageSaveDTO);

    /**
     * 商品计量单位查询
     */
    @RequestLine("GET /nhsoft.amazon.basic.item.unit.find")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<PosItemUnitDTO> findPosItemUnit(@Param("systemBookCode") String systemBookCode);
    /**
     * 商品计量单位查询
     */
    @RequestLine("GET /nhsoft.amazon.store.item.find")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<StoreItemSupplierDTO> findStoreItemSupplier(@Param("systemBookCode") String systemBookCode, @QueryMap StoreItemSupplierFindDTO storeItemSupplierFindDTO);

    /**
     * 分页查询供货关系
     */
    @RequestLine("POST /nhsoft.amazon.store.item.query")
    @Headers({"Content-Type:application/json", "Book-Code:{systemBookCode}"})
    List<StoreItemSupplierDTO> findStoreItemSupplier(@Param("systemBookCode") String systemBookCode, StoreItemSupplierQueryDTO storeItemSupplierQueryDTO);

}
