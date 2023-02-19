package ru.geekbrains.spring.wnteredshop.endpoints;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.spring.wnteredshop.services.ProductService;
import ru.geekbrains.spring.wnteredshop.soap.GetAllProductsRequest;
import ru.geekbrains.spring.wnteredshop.soap.GetAllProductsResponse;
import ru.geekbrains.spring.wnteredshop.soap.GetProdByIdRequest;
import ru.geekbrains.spring.wnteredshop.soap.GetProdByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://ru.geekbrais.winteredshop/spring/ws/products";

    private final ProductService productService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProdByIdResponse response (@RequestPayload GetProdByIdRequest request) {
        GetProdByIdResponse response = new GetProdByIdResponse();
        response.setProd(productService.getProdById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response =new GetAllProductsResponse();
        productService.getAllProds().forEach(response.getProd()::add);
        return response;
    }

}
