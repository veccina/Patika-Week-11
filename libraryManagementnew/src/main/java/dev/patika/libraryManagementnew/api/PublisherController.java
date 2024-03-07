package dev.patika.libraryManagementnew.api;

import dev.patika.libraryManagementnew.business.abstracts.IPublisherService;
import dev.patika.libraryManagementnew.core.config.modelMapper.IModelMapperService;
import dev.patika.libraryManagementnew.core.result.Result;
import dev.patika.libraryManagementnew.core.result.ResultData;
import dev.patika.libraryManagementnew.core.utilies.ResultHelper;
import dev.patika.libraryManagementnew.dto.request.publisher.PublisherSaveRequest;
import dev.patika.libraryManagementnew.dto.request.publisher.PublisherUpdateRequest;
import dev.patika.libraryManagementnew.dto.response.CursorResponse;
import dev.patika.libraryManagementnew.dto.response.publisher.PublisherResponse;
import dev.patika.libraryManagementnew.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelmapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelmapper) {
        this.publisherService = publisherService;
        this.modelmapper = modelmapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher savePublisher = this.modelmapper.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);
        return ResultHelper.created(this.modelmapper.forResponse().map(savePublisher, PublisherResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id) {
        Publisher publisher = this.publisherService.get(id);
        PublisherResponse publisherResponse = this.modelmapper.forResponse().map(publisher, PublisherResponse.class);
        return ResultHelper.success(publisherResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Page<Publisher> publisherPage = this.publisherService.cursor(page, pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage.map(publisher -> this.modelmapper.forResponse().map(publisher, PublisherResponse.class));
        return ResultHelper.cursor(publisherResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        this.publisherService.get(publisherUpdateRequest.getId());
        Publisher updatePublisher = this.modelmapper.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);
        return ResultHelper.success(this.modelmapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }
}
