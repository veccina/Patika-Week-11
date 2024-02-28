package dev.patika.ecommerce.api;

import dev.patika.ecommerce.business.abstracts.ISupplierService;
import dev.patika.ecommerce.core.config.modelMapper.IModelMapperService;
import dev.patika.ecommerce.core.result.Result;
import dev.patika.ecommerce.core.result.ResultData;
import dev.patika.ecommerce.core.utilies.ResultHelper;
import dev.patika.ecommerce.dto.request.supplier.SupplierSaveRequest;
import dev.patika.ecommerce.dto.request.supplier.SupplierUpdateRequest;
import dev.patika.ecommerce.dto.response.CursorResponse;
import dev.patika.ecommerce.dto.response.supplier.SupplierResponse;
import dev.patika.ecommerce.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController {

    private final ISupplierService supplierService;

    private final IModelMapperService modelmapper;

    public SupplierController(ISupplierService supplierService, IModelMapperService modelmapper) {
        this.supplierService = supplierService;
        this.modelmapper = modelmapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<SupplierResponse> save(@Valid @RequestBody SupplierSaveRequest supplierSaveRequest) {
        Supplier saveSupplier = this.modelmapper.forRequest().map(supplierSaveRequest, Supplier.class);
        this.supplierService.save(saveSupplier);
        return ResultHelper.created(this.modelmapper.forResponse().map(saveSupplier, SupplierResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> update(@Valid @RequestBody SupplierUpdateRequest supplierUpdateRequest) {
        Supplier updateSupplier = this.modelmapper.forRequest().map(supplierUpdateRequest, Supplier.class);
        this.supplierService.update(updateSupplier);
        return ResultHelper.success(this.modelmapper.forResponse().map(updateSupplier, SupplierResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> get(@PathVariable("id") int id) {
        Supplier supplier = this.supplierService.get(id);
        return ResultHelper.success(this.modelmapper.forResponse().map(supplier, SupplierResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<SupplierResponse>> cursor(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize
    ){
        Page<Supplier> supplierPage = this.supplierService.cursor(page, pageSize);
        Page<SupplierResponse> supplierResponsePage = supplierPage
                .map(supplier -> this.modelmapper.forResponse().map(supplier, SupplierResponse.class));
        return ResultHelper.cursor(supplierResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.supplierService.delete(id);
        return ResultHelper.ok();
    }
}
