package ${package.Controller};

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Entity}.${table.entityName};
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import java.util.List;

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.entityName}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
 <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
 <#else>
@Api(tags = "${table.comment!}相关接口")
public class ${table.controllerName} {
 </#if>
</#if>
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * 新增${table.comment}
     *
     * @param ${table.entityName?uncap_first}
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增${table.comment!}")
    public BaseResponse save(@RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
        return BaseResponse.success(${table.serviceName?uncap_first}.save(${table.entityName?uncap_first}));
    }

    /**
     * 删除${table.comment}
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除${table.comment!}")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(${table.serviceName?uncap_first}.removeById(id));
    }

    /**
     * 修改${table.comment}
     *
     * @param ${table.entityName?uncap_first}
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改${table.comment!}")
    public BaseResponse update(@RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
        return BaseResponse.success(${table.serviceName?uncap_first}.updateById(${table.entityName?uncap_first}));
    }

    /**
     * 查看所有${table.comment}
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有${table.comment!}")
    public BaseResponse list() {
        return BaseResponse.success(${table.serviceName?uncap_first}.list());
    }

    /**
     * 查看单个${table.comment}
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个${table.comment!}")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(${table.serviceName?uncap_first}.getById(id));
    }

    /**
     * 查看单个${table.comment}
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个${table.comment!}")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(${table.serviceName?uncap_first}.getById(id));
    }

    /**
     * 分页查看${table.comment}
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看${table.comment!}")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(${table.serviceName?uncap_first}.page(request));
    }
}