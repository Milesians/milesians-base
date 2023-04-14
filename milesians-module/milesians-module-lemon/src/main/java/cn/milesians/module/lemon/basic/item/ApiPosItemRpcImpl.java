package cn.milesians.module.lemon.basic.item;


import cn.hutool.core.util.StrUtil;
import cn.milesians.provider.lemon.LemonProviderException;
import cn.milesians.provider.lemon.basic.item.PosItemFeign;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCategoryDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCategoryFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemCountDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemFindAllDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemListDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemPageDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemSaveDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUnitDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUnitFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.PosItemUpdateDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierFindDTO;
import cn.milesians.provider.lemon.basic.item.dto.StoreItemSupplierQueryDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiPosItemRpcImpl implements ApiPosItemRpc {

    private final PosItemFeign posItemFeign;

    @Override
    public List<PosItemDTO> pagePosItemInfo(PosItemPageDTO posItemPageDTO) {

        try {
            return posItemFeign.pagePosItem(
                posItemPageDTO.getSystemBookCode(),
                posItemPageDTO.getLastDownloadTime(),
                posItemPageDTO.getKeyword(),
                posItemPageDTO.getItemCategoryCode(),
                posItemPageDTO.getManageTemplateNum(),
                posItemPageDTO.getPageNo(),
                posItemPageDTO.getPageSize());
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public Integer countPosItemInfo(PosItemCountDTO posItemCountDTO) {

        try {
            return posItemFeign.countPosItem(
                posItemCountDTO.getSystemBookCode(),
                posItemCountDTO.getLastDownloadTime(),
                posItemCountDTO.getKeyword(),
                posItemCountDTO.getItemCategoryCode(),
                posItemCountDTO.getManageTemplateNum()
            );
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }


    @Override
    public List<PosItemDTO> findPosItemInfo(PosItemFindDTO posItemFindDTO) {

        try {
            return posItemFeign.pagePosItem(
                posItemFindDTO.getSystemBookCode(),
                posItemFindDTO.getLastDownloadTime(),
                posItemFindDTO.getKeyword(),
                posItemFindDTO.getItemCategoryCode(),
                posItemFindDTO.getManageTemplateNum(),
                posItemFindDTO.getPageNo(),
                posItemFindDTO.getPageSize());
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public List<PosItemDTO> findPosItemInfo(PosItemListDTO posItemListDTO) {

        try {
            List<PosItemDTO> posItemDTOS = new ArrayList<>();
            if (posItemListDTO.getItemNums() != null && !posItemListDTO.getItemNums().isEmpty()) {
                for (int i = 0; i < posItemListDTO.getItemNums().size(); i = i + 100) {
                    int j = i + 100;
                    if (j > posItemListDTO.getItemNums().size()) {
                        j = posItemListDTO.getItemNums().size();
                    }
                    List<Integer> innerItemNums = posItemListDTO.getItemNums().subList(i, j);
                    String innerItemNumsStr = innerItemNums.stream().map(String::valueOf)
                        .collect(Collectors.joining(","));
                    List<PosItemDTO> innerPosItemDTOS = posItemFeign.listPosItem(posItemListDTO.getSystemBookCode(),
                        innerItemNumsStr, null);
                    posItemDTOS.addAll(innerPosItemDTOS);
                }
            } else {
                for (int i = 0; i < posItemListDTO.getItemCodes().size(); i = i + 100) {
                    int j = i + 100;
                    if (j > posItemListDTO.getItemCodes().size()) {
                        j = posItemListDTO.getItemCodes().size();
                    }
                    List<String> innerItemCodes = posItemListDTO.getItemCodes().subList(i, j);
                    String innerItemCodesStr = innerItemCodes.stream().map(String::valueOf)
                        .collect(Collectors.joining(","));
                    List<PosItemDTO> innerPosItemDTOS = posItemFeign.listPosItem(posItemListDTO.getSystemBookCode(),
                        null, innerItemCodesStr);
                    posItemDTOS.addAll(innerPosItemDTOS);
                }
            }
            return posItemDTOS;
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public List<PosItemDTO> findAllPosItemInfo(PosItemFindDTO posItemFindDTO) {
        int pageNo = 1;
        posItemFindDTO.setPageSize(100);
        List<PosItemDTO> posItemDTOS = new ArrayList<>();

        while (true) {
            posItemFindDTO.setPageNo(pageNo);
            List<PosItemDTO> dtos = this.findPosItemInfo(posItemFindDTO);
            if (dtos == null || dtos.isEmpty()) {
                break;
            }
            posItemDTOS.addAll(dtos);
            pageNo++;
        }

        return posItemDTOS;
    }

    @Override
    public List<PosItemDTO> findAllPosItemInfo(PosItemFindAllDTO posItemFindAllDTO) {
        PosItemFindDTO posItemFindDTO = new PosItemFindDTO();
        posItemFindDTO.setSystemBookCode(posItemFindAllDTO.getSystemBookCode());
        posItemFindDTO.setLastDownloadTime("2000-01-01 00:00:00");
        if (StrUtil.isNotEmpty(posItemFindAllDTO.getLastDownloadTime())) {
            posItemFindDTO.setLastDownloadTime(posItemFindAllDTO.getLastDownloadTime());
        }
        int pageNo = 1;
        posItemFindDTO.setPageSize(100);
        List<PosItemDTO> posItemDTOS = new ArrayList<>();
        while (true) {
            posItemFindDTO.setPageNo(pageNo);
            List<PosItemDTO> dtos = this.findPosItemInfo(posItemFindDTO);
            if (dtos == null || dtos.isEmpty()) {
                break;
            }
            posItemDTOS.addAll(dtos);
            pageNo++;
        }
        if (posItemFindAllDTO.getFilterDelItem() != null && posItemFindAllDTO.getFilterDelItem()) {
            posItemDTOS = posItemDTOS.stream().filter(p -> (p.getItemDelTag() == null || p.getItemDelTag() == 0))
                .collect(Collectors.toList());
        }

        return posItemDTOS;
    }

    @Override
    public PosItemDTO savePosItemInfo(PosItemSaveDTO posItemSaveDTO) {

        try {
            return posItemFeign.savePosItem(posItemSaveDTO.getSystemBookCode(), posItemSaveDTO);
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public PosItemDTO updatePosItemInfo(PosItemUpdateDTO posItemUpdateDTO) {

        try {
            return posItemFeign.updatePosItem(posItemUpdateDTO.getSystemBookCode(), posItemUpdateDTO);
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }


    @Override
    public List<PosItemCategoryDTO> findPosItemCategory(PosItemCategoryFindDTO posItemCategoryFindDTO) {

        try {
            List<PosItemCategoryDTO> posItemCategoryDTOS = posItemFeign.findPosItemCategory(
                posItemCategoryFindDTO.getSystemBookCode());
            if (StrUtil.isNotEmpty(posItemCategoryFindDTO.getKeyword())) {
                posItemCategoryDTOS = posItemCategoryDTOS.stream()
                    .filter(dto -> dto.getCategoryName().contains(posItemCategoryFindDTO.getKeyword())
                        || dto.getCategoryCode().contains(posItemCategoryFindDTO.getKeyword()))
                    .collect(Collectors.toList());
            }
            return posItemCategoryDTOS;
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }


    }


    @Override
    public List<PosItemUnitDTO> findPosItemUnit(PosItemUnitFindDTO posItemUnitFindDTO) {
        try {
            return posItemFeign.findPosItemUnit(posItemUnitFindDTO.getSystemBookCode());
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public List<StoreItemSupplierDTO> findStoreItemSupplier(StoreItemSupplierQueryDTO storeItemSupplierQueryDTO) {
        try {
            return posItemFeign.findStoreItemSupplier(storeItemSupplierQueryDTO.getSystemBookCode(),
                storeItemSupplierQueryDTO);
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }

    @Override
    public List<StoreItemSupplierDTO> findStoreItemSupplier(StoreItemSupplierFindDTO storeItemSupplierFindDTO) {
        try {
            return posItemFeign.findStoreItemSupplier(storeItemSupplierFindDTO.getSystemBookCode(),
                storeItemSupplierFindDTO);
        } catch (Exception e) {
            throw new LemonProviderException(e.getMessage());
        }
    }
}
