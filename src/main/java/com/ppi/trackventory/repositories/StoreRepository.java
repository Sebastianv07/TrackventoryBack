package com.ppi.trackventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppi.trackventory.models.Store;
import com.ppi.trackventory.models.DTO.StockByStoreDTO;

public interface StoreRepository extends JpaRepository<Store,Long>{

    @Query(value = "SELECT NEW com.ppi.trackventory.models.DTO.StockByStoreDTO(s.reference AS codeStore, st.quantity AS stock, c.name AS nameColor, p.name AS nameProduct, v.code AS codeVariation, v.colorVrt AS colorVrt) FROM stores s JOIN stock st on s.id = st.store_stk JOIN product_variations v on v.code = st.variation_stk JOIN products p on p.reference = v.product_vrt JOIN colors c on c.id_color = v.color_vrt ORDER BY p.reference", nativeQuery = true)
    List<StockByStoreDTO> getStockByStore();

    // @Query("SELECT NEW com.nutresa.backend.roles.backendroles.models.common.dto.jb_userDto(u.usrdesc AS rol, ur.urruser, js.usrid, js.usrdesc, p.perpermit, u.usrstate) FROM jb_user u JOIN jb_permit p ON u.uuid = p.perusrid JOIN jb_resource r ON r.resid = p.perresid JOIN jb_userrolrel ur ON ur.urrrol = u.uuid JOIN jb_user js ON js.uuid = ur.urruser WHERE u.classid='j_r' AND p.perpermit = 1 AND js.usrstate = 1 AND p.perresid =?1 ORDER BY js.usrdesc")
    // List<jb_userDto> getUsersBySubMenus(String idSubmenu);
}
