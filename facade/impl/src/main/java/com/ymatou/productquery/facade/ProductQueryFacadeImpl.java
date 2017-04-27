package com.ymatou.productquery.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymatou.productquery.domain.service.ListQueryService;
import com.ymatou.productquery.model.req.*;
import com.ymatou.productquery.model.res.BaseResponseNetAdapter;
import com.ymatou.productquery.model.res.ProductInCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyong on 2017/4/10.
 */
@Service(protocol = {"rest", "dubbo"})
@Component
@Path("")
public class ProductQueryFacadeImpl implements ProductQueryFacade {

    @Autowired
    private ListQueryService listQueryService;

    /**
     * 购物车中商品列表（普通购物车中用）
     *
     * @param request
     * @return
     */
    @Override
    @POST
    @Path("/{api:(?i:api)}/{Product:(?i:Product)}/{GetCatalogListByCatalogIdList:(?i:GetCatalogListByCatalogIdList)}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponseNetAdapter getCatalogListByCatalogIdList(GetCatalogListByCatalogIdListRequest request) {
        List<ProductInCartDto> result = listQueryService.getProductListFromShoppingCart(request.getCatalogIdList(), false);

        Map<String, Object> productList = new HashMap<>();
        productList.put("ProductList", result);
        return BaseResponseNetAdapter.newSuccessInstance(productList);
    }

    /**
     * 购物车中商品列表（交易隔离中用）
     *
     * @param request
     * @return
     */
    @Override
    @POST
    @Path("/{api:(?i:api)}/{Product:(?i:Product)}/{GetCatalogListByTradeIsolation:(?i:GetCatalogListByTradeIsolation)}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponseNetAdapter getCatalogListByTradeIsolation(GetCatalogListByTradeIsolationRequest request) {
        List<ProductInCartDto> result = listQueryService.getProductListFromShoppingCart(request.getCatalogIdList(), true);

        Map<String, Object> productList = new HashMap<>();
        productList.put("ProductList", result);
        return BaseResponseNetAdapter.newSuccessInstance(productList);
    }

    @Override
    @POST
    @Path("/{api:(?i:api)}/{Product:(?i:Product)}/{GetProductDetailListByProductIdList:(?i:GetProductDetailListByProductIdList)}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseResponseNetAdapter getProductDetailListByProductIdList(GetProductDetailListByProductIdListRequest request) {
        return null;
    }

    /**
     * 根据商品id获取商品详情
     * @param request
     * @return
     */
    @Override
    @GET
    @Path("/{api:(?i:api)}/{Product:(?i:Product)}/{GetProductInfoByProductId:(?i:GetProductInfoByProductId)}")
    @Produces({MediaType.APPLICATION_JSON})
    public BaseResponseNetAdapter getProductDetailByProductId(@BeanParam GetProductInfoByProductIdRequest request) {
        return null;
    }
}
