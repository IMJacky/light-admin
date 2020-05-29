package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    /**
     * 分页获取${table.comment!}
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
</#if>